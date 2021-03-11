def main():
    filename = input("Enter the name of the file with the molar masses:\n")
    dictionary = {}
    try:
        file = open(filename, 'r')
        for line in file:
            line = line.rstrip()
            parts = line.split(":")
            if len(parts) != 2:
                print("Invalid line: '{:s}'".format(line))
            else:
                try:
                    molar = float(parts[1])
                    chemical_formula = parts[0]
                    if chemical_formula not in dictionary:
                        dictionary[chemical_formula] = molar
                    else:
                        print("A molar mass for {:s} has already been saved in the dictionary ({:.3f} g/mol).".format(chemical_formula, dictionary[chemical_formula]))
                        replace = input(("Should it be replaced by the value {:.3f} g/mol (y/n)?\n".format(molar)))
                        if replace == "y":
                            dictionary[chemical_formula] = molar
                except ValueError:
                    print("Invalid molar mass on line: '{:s}'".format(line))
        print("Molar masses of {:d} substances were successfully read from the file.".format(len(dictionary)))
        x = True
        while x:
            formula = input("\nEnter the chemical formula of the substance. Stop by entering an empty line.\n")
            if formula == "":
                x = False
            elif formula not in dictionary:
                print("A molar mass for '{:s}' could not be found in the file.".format(formula))
            else:
                o = True
                while o:
                    try:
                        mass = float(input("Enter the mass of the substance (in grams):\n"))
                        if mass < 0:
                            print("The mass needs to be positive or zero!")
                        else:
                            print("{:.3f} grams of {:s} is equal to {:.3f} moles.".format(mass, formula, mass / dictionary[formula]))
                            o = False
                    except ValueError:
                        print("The mass needs to be a number!")

        print("Program terminating.")

    except OSError:
        print("File could not be read.")
        print("Program terminating.")


main()