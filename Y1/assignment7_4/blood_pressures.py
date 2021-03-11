SYSTOLIC_LIMITS = [0, 100, 120, 130, 140, 160, 180]
DIASTOLIC_LIMITS = [0, 60, 80, 85, 90, 100, 110]
BLOOD_PRESSURE_DESCRIPTIONS = ['low', 'optimal', 'normal', 'high normal', 'grade 1 hypertension', 'grade 2 hypertension', 'grade 3 hypertension']





def print_distribution(pressures):
    l = len(pressures)
    high = 0
    _list = [0] * 7
    for n in pressures:
        if n[0] < SYSTOLIC_LIMITS[1] and n[1] < DIASTOLIC_LIMITS[1]:
            _list[0] += 1
        elif n[0] < SYSTOLIC_LIMITS[2] and n[1] < DIASTOLIC_LIMITS[2]:
            _list[1] += 1
        elif n[0] < SYSTOLIC_LIMITS[3] and n[1] < DIASTOLIC_LIMITS[3]:
            _list[2] += 1
        elif n[0] < SYSTOLIC_LIMITS[4] and n[1] < DIASTOLIC_LIMITS[4]:
            _list[3] += 1
        elif n[0] < SYSTOLIC_LIMITS[5] and n[1] < DIASTOLIC_LIMITS[5]:
            _list[4] += 1
            high += 1
        elif n[0] < SYSTOLIC_LIMITS[6] and n[1] < DIASTOLIC_LIMITS[6]:
            _list[5] += 1
            high += 1
        else:
            _list[6] += 1
            high += 1
    p = []
    for x in range(7):
        p.append(100 * _list[x] / l)
    for x in range(7):
        print("{:25s} | {:20s} ({:d}%)".format(BLOOD_PRESSURE_DESCRIPTIONS[x], "#" * round(p[x] / 5), round(p[x])))
    if high > 0:
        print("{:d} ({:d}%) of the patients have high blood pressure!".format(high, round(p[4] + p[5] + p[6])))
    if _list[0] > 0:
        print("{:d} ({:d}%) of the patients have low blood pressure!".format(_list[0], round(p[0])))


def trim(_list):
    new_list = []
    removed = 0
    for x in _list:
        if x[0] < 1 or x[1] < 1 or x[0] > 250 or x[1] > 250:
            removed += 1
        else:
            new_list.append(x)
    if removed > 0:
        print("Removed {:d} measurements from data.".format(removed))
    return new_list


def main():
    filename = input("Enter the name of the data file:\n")
    pressures = []
    line_number = 0
    try:
        file = open(filename, 'r')
        for line in file:
            line_number += 1
            line = line.rstrip()
            try:
                parts = line.split("/")
                if len(parts) == 2:
                    systolic = int(parts[0])
                    diastolic = int(parts[1])
                    if systolic <= diastolic:
                        print("Invalid line # {:d}: {:s}".format(line_number, line))
                    else:
                        mini_list = [systolic, diastolic]
                        pressures.append(mini_list)
                else:
                    print("Invalid line # {:d}: {:s}".format(line_number, line))
            except ValueError:
                print("Invalid line # {:d}: {:s}".format(line_number, line))
        pressures = trim(pressures)

        valid = len(pressures)

        if valid > 0:
            print("\n{:d} valid data points read.".format(valid))
            print("Blood pressure distribution among the patients:")
            print_distribution(pressures)
        else:
            print("\nNot enough valid data.")
    except OSError:
        print("Error in reading the file.")


main()


