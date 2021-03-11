GAS_CONSTANT = 8.31446261815324 # moolinen kaasuvakio R


def main():
    n = int(input("What is the amount of substance of the gas (in moles)?\n"))
    lower_limit_temp = int(input("Enter a lower limit for the temperature (in Kelvins)?\n"))
    higher_limit_temp = int(input("Enter a higher limit for the temperature (in Kelvins)?\n"))
    lower_limit_vol = int(input("Enter a lower limit for the volume (in cubic metres)?\n"))
    higher_limit_vol = int(input("Enter a higher limit for the volume (in cubic metres)?\n"))
    filename = input("Enter the name of the file where the table will be written:\n")
    i = lower_limit_vol
    j = lower_limit_temp
    try:
        file = open(filename, 'w')
        file.write("The pressure of {:d} moles of gas between {:d} - {:d} Kelvins and {:d} - {:d} cubic metres, measured in kilopascals\n".format(n, lower_limit_temp, higher_limit_temp, lower_limit_vol, higher_limit_vol))
        file.write("  p(T, V) |")
        while j <= higher_limit_temp:
            file.write("{:9d}".format(j))
            j += 10
        j = lower_limit_temp
        file.write("\n")
        file.write("-----------")
        while j <= higher_limit_temp:
            file.write("---------")
            j += 10
        j = lower_limit_temp
        file.write("\n")
        while i <= higher_limit_vol:
            file.write("{:9d} |".format(i))
            while j <= higher_limit_temp:
                pressure = n * GAS_CONSTANT * j / i / 1000
                file.write("{:9.2f}".format(pressure))
                if j == higher_limit_temp:
                    file.write("\n")
                j += 10
            j = lower_limit_temp
            i += 1
        file.close()
        file = open(filename, 'r')
        print("The table was written successfully.\n")
        print("The file '{:s}' looks like this:\n".format(filename))
        for line in file:
            print(line)

    except OSError:
        print("Could not write to file.")


main()