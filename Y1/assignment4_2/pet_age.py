
def dog_age_in_human_years(dog_age):
    human_age = 0
    if dog_age <= 1:
        human_age = dog_age * 15
    elif dog_age <= 2:
        human_age = 15 + (dog_age - 1) * 9
    else:
        human_age = 24 + (dog_age - 2) * 5
    print("Your dog is {:.1f} years old in human years!".format(human_age))


def cat_age_in_human_years(cat_age):
    human_age = 0
    if cat_age <= 1:
        human_age = cat_age * 15
    elif cat_age <= 2:
        human_age = 15 + (cat_age - 1) * 10
    else:
        human_age = 25 + (cat_age - 2) * 4
    print("Your cat is {:.1f} years old in human years!".format(human_age))


def bunny_age_in_human_years(bunny_age):
    human_age = 0
    if bunny_age <= 0.5:
        human_age = bunny_age * 32
    elif bunny_age <= 1:
        human_age = 16 + (bunny_age - 0.5) / 0.5 * 5
    else:
        human_age = 21 + (bunny_age - 1) * 6
    print("Your bunny is {:.1f} years old in human years!".format(human_age))


def main():
    print("Welcome to the pet age calculator! I will tell your pet's age in human years.")
    choice = 0
    while not 1 <= choice <= 3:
        print("Choose a pet:\n"
              "1: dog\n"
              "2: cat\n"
              "3: bunny")
        choice = int(input())
    age = float(input("How old is your pet?\n"))
    if choice == 1:
        dog_age_in_human_years(age)
    elif choice == 2:
        cat_age_in_human_years(age)
    else:
        bunny_age_in_human_years(age)


main()

