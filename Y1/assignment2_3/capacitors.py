
line = input("How many capacitors are there?\n")
number = int(line)

while number <= 0:
    print("Enter a positive value!")
    line = input("How many capacitors are there?\n")
    number = int(line)

line = input("Are the capacitors connected:\n1. in series\n2. in parallel?\n")
yay = int(line)
while yay != 1 and yay != 2:
    print("Invalid choice!")
    line = input("Are the capacitors connected:\n1. in series\n2. in parallel?\n")
    yay = int(line)

total = 0
i = 0
if yay == 1:
    while i < number:
        line = input("Enter the capacitance for the next capacitor:\n")
        current = float(line)
        total += (1/current)
        i += 1
    print("The total capacitance of the capacitors is ", 1 / total, " F.")
else:
    while i < number:
        line = input("Enter the capacitance for the next capacitor:\n")
        current = float(line)
        total += current
        i += 1
    print("The total capacitance of the capacitors is ", total, " F.")

