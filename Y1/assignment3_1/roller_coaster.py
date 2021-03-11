

line = input("How many heights will be entered?\n")
amount = int(line)
while amount <= 0:
    print("Enter a positive value!")
    line = input("How many heights will be entered?\n")
    amount = int(line)

print("Enter the heights of the children on separate lines.")

allowed = 0
for i in range(amount):
    line = input()
    h = int(line)
    if h >= 140:
        allowed += 1

print("There are ", amount, " children.")
print(allowed, " of the children are allowed and ", amount - allowed, " are not allowed on the roller coaster.")



