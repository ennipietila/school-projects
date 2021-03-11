
def main():
    print("Enter the lengths of the throws (m) separated by commas.")
    line = input()
    if line == "":
        print("No accepted results.")
    else:
        li = line.split(",")
        list_ = []
        for x in li:
            number = float(x)
            list_.append(number)
        m = 0
        for x in list_:
            if x > m:
                m = x
        print("The best result is {:.2f} m.".format(m))


main()
