line = input("How much does the apartment cost?\n")
price = int(line)

line = input("How much is your initial monthly salary?\n")
salary = int(line)

line = input("How many percent does your salary increase per year?\n")
increase = int(line)

line = input("And how many percent of your salary will you save?\n")
save = int(line)

line = input("How much savings do you have?\n")
savings = int(line)

while price <= 0 or salary <= 0 or savings < 0 or increase < 0 or increase > 100 or save < 0 or save > 100:
    print("Enter only positive values and percentages between 0 - 100!")
    line = input("How much does the apartment cost?\n")
    price = int(line)
    line = input("How much is your initial monthly salary?\n")
    salary = int(line)
    line = input("How many percent does your salary increase per year?\n")
    increase = int(line)
    line = input("And how many percent of your salary will you save?\n")
    save = int(line)
    line = input("How much savings do you have?\n")
    savings = int(line)

needed_money = price - savings

saved = 0
total_months = 0
m = 0

while saved < needed_money:
    saved += salary * save / 100
    total_months += 1
    m += 1
    if m >= 12:
        salary = salary * (1 + increase / 100)
        m = 0

print("You need ", needed_money, " euros for the apartment.")
if total_months % 12 == 0:
    print("It will take you exactly ", total_months // 12, " years to save the money for the apartment.")
else:
    print("It will take you ", total_months // 12, " years and ", total_months % 12, "months to save the money for the apartment.")