

line = input("Enter the amount of the alcoholic drink (l).\n")

amount = float(line)

line = input("Enter the alcohol content of the alcoholic drink (%).\n")

content = float(line)

line = input("How much non-alcoholic drink do you want to add (l)?\n")

amountOfNon = float(line)

totalAmount = amount + amountOfNon

amountOfAlc = amount * content / 100.0

finalContent = amountOfAlc / totalAmount * 100.0

print("Alcohol content of the drink is ", finalContent, " %.")




