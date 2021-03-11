SPECIFIC_HEAT_CAPACITY_ICE = 2.09 # specific heat capacity c for ice, unit kJ / (kg * °C)
SPECIFIC_HEAT_CAPACITY_WATER = 4.1819 # specific heat capacity c for water, unit kJ / (kg * °C)
SPECIFIC_HEAT_OF_FUSION_WATER = 333 # specific heat of fusion s, unit kJ / kg
SPECIFIC_HEAT_OF_VAPORIZATION_WATER = 2260 # specific heat of vaporization, unit kJ / kg


def ice_cube_heating(energy, mass, start_temp, end_temp):
    energy_needed_solid = SPECIFIC_HEAT_CAPACITY_ICE * mass * (end_temp - start_temp)
    energy_needed_fluid = SPECIFIC_HEAT_CAPACITY_WATER * mass *(end_temp - start_temp)
    if start_temp < 0 and end_temp <= 0:
        if energy < energy_needed_solid:
            temp = energy / (SPECIFIC_HEAT_CAPACITY_ICE * mass) + start_temp
            return 0.0, temp
        else:
            return energy - energy_needed_solid, end_temp
    elif start_temp < 0 and end_temp > 0:
        solid = SPECIFIC_HEAT_CAPACITY_ICE * mass * (0.0 - start_temp)
        fluid = SPECIFIC_HEAT_CAPACITY_WATER * mass * (end_temp - 0.0)
        if energy < solid:
            temp = energy / (SPECIFIC_HEAT_CAPACITY_ICE * mass) + start_temp
            return 0.0, temp
        elif energy < solid + fluid:
            temp = (energy - solid) / (SPECIFIC_HEAT_CAPACITY_WATER * mass) + 0.0
            return 0.0, temp
        else:
            return energy - solid - fluid, end_temp
    else:
        if energy < energy_needed_fluid:
            temp = energy / (SPECIFIC_HEAT_CAPACITY_WATER * mass) + start_temp
            return 0.0, temp
        else:
            return energy - energy_needed_fluid, end_temp


def ice_cube_melting(energy, mass):
    energy_melting = SPECIFIC_HEAT_OF_FUSION_WATER * mass
    if energy < energy_melting:
        return 0.0, False
    else:
        return energy - energy_melting, True


def ice_cube_vaporization(energy, mass):
    energy_vaporization = SPECIFIC_HEAT_OF_VAPORIZATION_WATER * mass
    if energy < energy_vaporization:
        return 0.0, False
    else:
        return energy - energy_vaporization, True


def print_heating_result(energy_total, mass, temp_init, temp_end, melted, vaporized):
    print("With {:.2f} kJ, an ice cube weighing {:.2f} kg heats from {:.2f} °C to {:.2f} °C."
          .format(energy_total, mass, temp_init, temp_end))
    if not melted and not vaporized:
        print("The ice cube stays solid.")
    elif melted and not vaporized:
        print("The ice cube has melted into fluid water.")
    else:
        print("The ice cube has vaporized and is now water vapor.")

def main():
    print("Welcome to the ice cube simulator! I will tell you stats about heating your ice cube.")
    mass = float(input("What is the mass of the ice cube (in kg)?\n"))
    while mass <= 0.0:
        print("Mass cannot be zero or negative!")
        mass = float(input("What is the mass of the ice cube?\n"))
    temp_init = float(input("What is the initial temperature of the ice cube (in °C)?\n"))
    while temp_init < -273.15 or temp_init > 0.0:
        print("The ice cube's temperature can't be under the absolute zero or above 0 degrees!")
        temp_init = float(input("What is the initial temperature of the ice cube (in °C)?\n"))
    energy_total = float(input("What is the total energy used for heating the ice cube (in kJ)?\n"))
    while energy_total < 0.0:
        print("Energy cannot be negative!")
        energy_total = float(input("What is the total energy used for heating the ice cube (in kJ)?\n"))

    melted = False
    vaporized = False
    energy_remaining, end_temp = ice_cube_heating(energy_total, mass, temp_init, 0.0)
    if energy_remaining != 0.0:
        energy_remaining, melted = ice_cube_melting(energy_remaining, mass)
        if energy_remaining != 0.0:
            energy_remaining, end_temp = ice_cube_heating(energy_remaining, mass, 0.0, 100.0)
            if energy_remaining != 0.0:
                energy_remaining, vaporized = ice_cube_vaporization(energy_remaining, mass)

    print_heating_result(energy_total, mass, temp_init, end_temp, melted, vaporized)


main()