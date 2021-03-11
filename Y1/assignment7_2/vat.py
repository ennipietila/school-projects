def main():
    filename = input("Enter the name of the file containing selling prices.\n")
    try:
        price_file = open(filename, 'r')
        print("    Price       VAT    Price (incl. VAT)")
        total = 0
        vat = 0.24
        for line in price_file:
            value = float(line.rstrip())
            total += value * vat
            print("{:>9.2f} {:>9.2f} {:>9.2f}".format(value, value * vat, value + value * vat))
        print("------------------------------------------")
        print("Total VAT {:.2f} eur.".format(total))
    except OSError:
        print("Error in reading file {:s}. Closing program.".format(filename))
    except ValueError:
        print("Incorrect line in file {:s}. Closing program.".format(filename))


main()