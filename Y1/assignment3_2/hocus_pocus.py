
line = input("Enter the first number to be printed.\n")
first = int(line)

while first <= 0:
    print("Enter a positive number!")
    line = input("Enter the first number to be printed.\n")
    first = int(line)

line = input("Enter the last number to be printed.\n")
last = int(line)

while last <= first:
    print("Enter a number that is larger than the first number!")
    line = input("Enter the last number to be printed.\n")
    last = int(line)

print("Hocus pocus between {:d} - {:d}:".format(first, last))

for i in range(first, last + 1):
    if i % 3 != 0 and i % 5 != 0:
        print(i)
    elif i % 3 == 0 and i % 5 == 0:
        print("HOCUS POCUS!")
    elif i % 3 == 0:
        print("hocus")
    else:
        print("pocus")




