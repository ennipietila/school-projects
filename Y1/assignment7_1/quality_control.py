def main():
    filename = input("Enter the name of the file to be read:\n")
    try:
        product_file = open(filename,'r')
        count = 0
        optimal = 0
        allowed = 0
        faulty = 0

        for line in product_file:
            line = line.rstrip()
            measure = float(line)
            count += 1
            if measure < 4.5 or measure > 4.6:
                faulty += 1
            elif measure < 4.52 or measure > 4.58:
                allowed += 1
            else:
                optimal += 1
        product_file.close()

        print("File read succesfully.")

        if count == 0:
            print("The file didn't contain any data.")
        else:
            print("The batch contained:")
            print("{:d} optimal ({:.1f}%)".format(optimal, 100 * optimal / count))
            print("{:d} allowed ({:.1f}%)".format(allowed, 100 * allowed / count))
            print("{:d} faulty ({:.1f}%).".format(faulty, 100 * faulty / count))
        if faulty / count >= 0.03:
            print("The batch didn't pass the quality inspection.")
        else:
            print("The batch passed the quality inspection.")

    except OSError:
        print("Error in reading the file '{:s}'. Program ends.".format(filename))
    except ValueError:
        print("Incorrect number in the file '{:s}'. Program ends.".format(filename))


main()