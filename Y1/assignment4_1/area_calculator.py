
def calculate_and_print(s, b, h):
    if s == "r":
        area = b * h
    if s == "t":
        area = b * h / 2
    print("The area is ", area, " square meters.")


def main():
    print("Choose a shape:")
    print("r - rectangle")
    print("t - triangle")
    shape = input()
    base = float(input("Enter the length of the base (m):\n"))
    height = float(input("Enter the height (m):\n"))
    calculate_and_print(shape, base, height)


main()
