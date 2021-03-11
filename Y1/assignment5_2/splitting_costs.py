
def ask_amounts(n):
    _amounts = [0.0] * n
    i = 0
    while i < n:
        paid = float(input("Enter the sum (eur) paid by participant no {:1d}.\n".format(i + 1)))
        _amounts[i] = paid
        i += 1
    return _amounts


def calculate_total(_list):
    i = 0
    for x in _list:
        i += x
    return i


def pays(_list, av):
    i = 1
    for x in _list:
        if x >= av:
            print("Participant no ", i, " should receive {:.2f} eur.".format(x - av))
        else:
            print("Participant no ", i, " should pay {:.2f} eur.".format(av - x))
        i += 1


def main():
    participants = int(input("Enter the number of the participants.\n"))
    while participants < 2:
        print("The number must be at least 2!")
        participants = int(input("Enter the number of the participants.\n"))
    amounts = ask_amounts(participants)
    total = calculate_total(amounts)
    average = total / len(amounts)
    print("Total costs are {:.2f} eur.".format(total))
    pays(amounts, average)


main()

