def main():
    filename = input("Enter the name of the file containing the recipe:\n")
    try:
        recipe_file = open(filename, 'r')
        name = recipe_file.readline().rstrip()
        servings = recipe_file.readline().rstrip()
        p = int(servings.split(" ")[0])
        print("This recipe of {:s} makes {:d} servings.".format(name, p))
        line = input("How many servings do you want to make with this recipe?\n")
        o = True
        while o:
            try:
                n = int(line)
                if n < 1:
                    print("The amount needs to be positive!")
                    line = input("How many servings do you want to make with this recipe?\n")
                else:
                    o = False
            except ValueError:
                print("The amount needs to be an integer!")
                line = input("How many servings do you want to make with this recipe?\n")
        new_servings = int(line)
        print("\nFor {:d} servings of {:s} you will need:".format(new_servings, name))
        recipe_file.readline()
        divider = new_servings / p
        for line in recipe_file:
            try:
                line = line.rstrip()
                t = ""
                n = int(line[0])
                v = ""
                for x in line:
                    if x.isdigit() or x == ".":
                        v += x
                    else:
                        t += x

                parts = line.split(".")
                # v = parts[0] + "." + parts[1][0]
                num = float(v) * divider
                # t = parts[1][1:]
                print("{:.1f}{:s}".format(round(num, 1), t))

            except ValueError:
                print(line)

    except OSError:
        print("File could not be read. Terminating program.")


main()