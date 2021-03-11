def main():
    days = int(input("How many sales will you input?\n"))
    sales = [0.0] * days
    for i in range(days):
        sales_day = float(input("Enter the next amount.\n"))
        sales[i] = sales_day

    print("Commissions")

    LIMIT = 300             # euros
    NORMAL_COMMISSION = 7.5 # %
    BONUS_COMMISSION = 14   # %

    sum = 0
    for value in sales:
        if value >= LIMIT:
            com = value * BONUS_COMMISSION / 100
            print("{:.2f} eur".format(com))
            sum += com
        else:
            com = value * NORMAL_COMMISSION / 100
            print("{:.2f} eur".format(com))
            sum += com

    print("Total commissions {:.2f} eur.".format(sum))
    # Implement your own code here that goes through the list of
    # sales and calculates and prints the commissions based on those sales.

    # Write then a command here that prints the total of commissions.


main()
