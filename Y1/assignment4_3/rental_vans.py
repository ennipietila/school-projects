import math




def calculate_cost(consumption, distance, gas_price, rent, time):
    gas = consumption / 100 * distance * gas_price
    rent_cost = math.ceil(time) * rent
    return gas + rent_cost


def main():
    cost = float(input("How much does gas cost (per litre)?\n"))

    rent_big = float(input("Enter the hourly rent for the big van:\n"))

    time_big = float(input("Enter the estimated rental time (hours) for the big van:\n"))

    distance_big = float(input("Enter estimated driving distance (km) for the big van:\n"))

    fuel_big = float(input("Enter fuel consumption (litres / 100 km) for the big van:\n"))

    rent_small = float(input("Enter the hourly rent for the small van:\n"))

    time_small = float(input("Enter the estimated rental time (hours) for the small van:\n"))

    distance_small = float(input("Enter estimated driving distance (km) for the small van:\n"))

    fuel_small = float(input("Enter fuel consumption (litres / 100 km) for the small van:\n"))

    total_big = calculate_cost(fuel_big, distance_big, cost, rent_big, time_big)

    total_small = calculate_cost(fuel_small, distance_small, cost, rent_small, time_small)

    print("Renting the bigger van would cost {:.2f} euros and renting the smaller van would cost {:.2f} euros."
         .format(total_big, total_small))

    if total_small < total_big:
        print("You should rent the small van.")
    else:
        print("You should rent the big van.")


main()

