def main():
    name = input("Enter a filename:\n")
    laptimes = {}
    try:
        resultfile = open(name, "r")
        for line in resultfile:
            line = line.rstrip()
            parts = line.split(":")
            if len(parts) != 2:
                print("ERROR in line:", line)
            else:
                driver = parts[0]
                try:
                    time = float(parts[1])
                    if driver in laptimes:
                        if time < laptimes[driver]:
                            laptimes[driver] = time
                    else:
                        laptimes[driver] = time
                except ValueError:
                    print("ERROR: incorrect lap time:", parts[1])
        resultfile.close()

        # Output the best lap times.
        if laptimes == {}:
            print("The file did not contain any correct lap times.")
        else:
            print("Results")
            print("Driver                Time (s)")
            drivers = sorted(laptimes)
            for driver in drivers:
                print("{:20s} {:7.3f}".format(driver, float(laptimes[driver])))

    except OSError:
        print("Error in reading file {:s}. Closing program.".format(name))


main()
