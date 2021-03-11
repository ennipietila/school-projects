# Y1 AUTUMN 2020
# Basic Course in Programming Y1
# Author: Kerttu Pollari-Malmi

# Test program for class Insurance.

# It would be better to split the long main function to several functions.
# That has not been done it to make it easier for the beginner to follow the
# execution of the program line by line.

import insurance

# function to read a float


def input_float():
    success = False
    while not success:
        try:
            number = float(input())
            success = True
            return number
        except ValueError:
            print("Please, give a decimal number!")


def main():
    name1 = input("Enter the name of the first customer.\n")
    name2 = input("Enter the name of the second customer.\n")
    print("What is the premium of the first insurance policy?")
    fee1 = input_float()
    print("What is the premium of the second insurance policy?")
    fee2 = input_float()
    print("What is the deductible of the first insurance policy?")
    deductible1 = input_float()
    print("What is the deductible of the second insurance policy?")
    deductible2 = input_float()
    print("What is the total maximum of the first insurance policy?")
    cap1 = input_float()
    print("What is the total maximum of the second insurance policy?")
    cap2 = input_float()
    policy1 = insurance.Insurance(name1, fee1, deductible1, cap1)
    policy2 = insurance.Insurance(name2, fee2, deductible2, cap2)

    print("Enter the increase of the bonus of the first insurance policy.")
    bonus1 = input_float()
    policy1.increase_bonus(bonus1)
    print("Enter the increase of the bonus of the second insurance policy.")
    bonus2 = input_float()
    policy2.increase_bonus(bonus2)

    print()
    print("The first insurance policy:")
    print("Owner:", policy1.get_name())
    print("original premium: {:.2f} eur".format(policy1.get_premium()))
    print("bonus: {:.2f} %".format(policy1.get_bonus()))
    print("deductible: {:.2f} eur".format(policy1.get_deductible()))
    print("maximum total payments: {:.2f} eur".format(policy1.get_total_compensation_cap()))
    print("premium with bonus: {:.2f} eur".format(policy1.calculate_real_premium()))
    print("current total payments: {:.2f} eur.".format(policy1.get_total_compensations()))
    if policy1.is_valid():
        print("The insurance policy is valid.")
    else:
        print("The insurance policy is not valid.")

    policy2.set_invalid()
    print()
    print("The second insurance policy:")
    print("Owner:", policy2.get_name())
    print("original premium: {:.2f} eur".format(policy2.get_premium()))
    print("bonus: {:.2f} %".format(policy2.get_bonus()))
    print("deductible: {:.2f} eur".format(policy2.get_deductible()))
    print("maximum total payments: {:.2f} eur".format(policy2.get_total_compensation_cap()))
    print("premium with bonus: {:.2f} eur".format(policy2.calculate_real_premium()))
    print("current total payments: {:.2f} eur.".format(policy2.get_total_compensations()))
    if policy2.is_valid():
        print("The insurance policy is valid.")
    else:
        print("The insurance policy is not valid.")

    print()
    print("Increase bonus again.")
    print("Enter the increase of the bonus of the first insurance policy.")
    bonus1 = input_float()
    policy1.increase_bonus(bonus1)
    print("Enter the increase of the bonus of the second insurance policy.")
    bonus2 = input_float()
    policy2.increase_bonus(bonus2)
    print("Bonus after the increase:")
    print("1. policy: {:.2f} %.".format(policy1.get_bonus()))
    print("2. policy: {:.2f} %.".format(policy2.get_bonus()))

    print()
    answer = input("Do you want to make the second policy valid (y/n)?\n")
    if answer.lower() == "y":
        policy2.set_valid()

    print()
    print("Handling claims.")
    print("Enter the cost of the damage for the first policy.")
    damage1 = input_float()
    compensation1 = policy1.calculate_compensation(damage1)
    print("Compensation {:.2f} eur.".format(compensation1))
    print("Enter the cost of the new damage for the first policy.")
    damage2 = input_float()
    compensation2 = policy1.calculate_compensation(damage2)
    print("Compensation {:.2f} eur.".format(compensation2))
    print()
    print("Enter the cost of the damage for the second policy.")
    damage3 = input_float()
    compensation3 = policy2.calculate_compensation(damage3)
    print("Compensation {:.2f} eur.".format(compensation3))

    print()
    print("Decreasing the bonus of the second policy.")
    print("Enter the amount of the decrease.")
    bonus2 = input_float()
    policy2.decrease_bonus(bonus2)

    print("Updating the premium of the second policy.")
    print("Enter the new premium (eur / year)?")
    new_fee = input_float()
    policy2.set_premium(new_fee)

    print()
    print("The first policy at the end:")
    print("Owner:", policy1.get_name())
    print("original premium: {:.2f} eur".format(policy1.get_premium()))
    print("bonus: {:.2f} %".format(policy1.get_bonus()))
    print("deductible: {:.2f} eur".format(policy1.get_deductible()))
    print("maximum total payments: {:.2f} eur".format(policy1.get_total_compensation_cap()))
    print("premium with bonus: {:.2f} eur".format(policy1.calculate_real_premium()))
    print("current total payments: {:.2f} eur.".format(policy1.get_total_compensations()))
    if policy1.is_valid():
        print("The insurance policy is valid.")
    else:
        print("The insurance policy is not valid.")

    print()
    print("The second policy at the end:")
    print("Owner:", policy2.get_name())
    print("original premium: {:.2f} eur".format(policy2.get_premium()))
    print("bonus: {:.2f} %".format(policy2.get_bonus()))
    print("deductible: {:.2f} eur".format(policy2.get_deductible()))
    print("maximum total payments: {:.2f} eur".format(policy2.get_total_compensation_cap()))
    print("premium with bonus: {:.2f} eur".format(policy2.calculate_real_premium()))
    print("current total payments: {:.2f} eur.".format(policy2.get_total_compensations()))
    if policy2.is_valid():
        print("The insurance policy is valid.")
    else:
        print("The insurance policy is not valid.")

    print()
    print("Information about the first policy provided by __str__-method:")
    print(policy1)
    print()
    print("Information about the second policy provided by __str__-method:")
    print(policy2)


main()
