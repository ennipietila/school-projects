import math

def calculate_gift_tax(gift_value, relative):
    LOWER_LIMITS = [5000, 25000, 55000, 200000, 1000000]
    TAX_PERCENTS_RELATIVES = [0.08, 0.10, 0.12, 0.15, 0.17]
    TAX_AT_LOWER_LIMIT_RELATIVES = [100, 1700, 4700, 22100, 142100]
    TAX_PERCENTS_OTHERS = [0.19, 0.25, 0.29, 0.31, 0.33]
    TAX_AT_LOWER_LIMIT_OTHERS = [100, 3900, 11400, 53450, 301450]
    v = math.floor(gift_value / 100) * 100
    if v < LOWER_LIMITS[0]:
        tax = 0.0
    else:
        i = 0
        x = False
        while not x and i < len(LOWER_LIMITS):
            if v >= LOWER_LIMITS[i] and i != 4:
                i += 1
            elif v < LOWER_LIMITS[i]:
                i -= 1
                x = True
            else:
                x = True
        if not relative:
            tax = TAX_AT_LOWER_LIMIT_OTHERS[i] + (v - LOWER_LIMITS[i]) * TAX_PERCENTS_OTHERS[i]
        else:
            tax = TAX_AT_LOWER_LIMIT_RELATIVES[i] + (v - LOWER_LIMITS[i]) * TAX_PERCENTS_RELATIVES[i]
    return tax


def main():
    value = float(input("Enter the value of the gift:\n"))
    r = input("Is the receiver a close relative (yes/no)?\n")
    if r == "no":
        relative = False
    else:
        relative = True
    t = calculate_gift_tax(value, relative)
    print("Gift tax is {:.2f} euros.\n".format(t))
    if t == 0.0:
        return
    max_ = int(input("How much gift tax are you willing to pay at most?\n"))
    if max_ >= t:
        print("You can give the whole gift in one installment.")
    else:
        n = 2
        total = t
        while total > max_:
            part = value // n
            tax = calculate_gift_tax(part, relative)
            total = tax * n
            n += 1
        print("You would have to part the gift in ", n - 1, " parts ({:.2f} euros per part).".format(part))
        print("Tax would be {:.2f} euros per part and {:.2f} euros in total.".format(tax, total))
        print("It would take you ", (n - 1) * 3 - 3, " years to give away the whole gift.")


main()
