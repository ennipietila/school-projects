line = input("Base number:\n")

base = int(line)

line = input("Upper limit for the powers:\n")

limit = int(line)

print("Powers:")
current = base

while current <= limit:
    print(current)
    current = current * base




