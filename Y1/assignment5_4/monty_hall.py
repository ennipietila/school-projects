import random


def initialize_doors(number_of_doors):
    doors = [False] * number_of_doors
    i = random.randint(0, number_of_doors - 1)
    doors[i] = True
    return doors


def remove_wrong_doors(chosen_door, doors):
    if doors[chosen_door - 1]:
        r = random.randint(1, len(doors))
        return r
    else:
        for i in range(len(doors)):
            if doors[i]:
                return i + 1


def print_doors(doors, dont_open):
    for x in range(len(doors) - 1):
        print(" _ ", end=" ")
    print(" _  ")
    for x in range(len(doors) - 1):
        if x + 1 in dont_open:
            print("| |", end=" ")
        else:
            if doors[x]:
                print("|C|", end=" ")
            else:
                print("|G|", end=" ")
    if len(doors) in dont_open:
        print("| | ")
    else:
        if doors[-1]:
            print("|C| ")
        else:
            print("|G| ")
    for x in range(len(doors) - 1):
        print("|_|", end=" ")
    print("|_| ")
    for x in range(1, len(doors)):
        print("{:^3d}".format(x), end=" ")
    print("{:^3d} ".format(len(doors)))


def main():
    seed = int(input("Set seed:\n"))
    random.seed(seed)
    n = int(input("How many doors?\n"))
    while n < 3 or n > 999:
        print("The number of doors must be between 3-999!")
        n = int(input("How many doors?\n"))
    d = initialize_doors(n)
    b = []
    for i in range(1, len(d) + 1):
        b.append(i)
    print_doors(d, b)
    chosen = int(input("Choose a door 1-{:1d}.\n".format(n)))
    while chosen < 1 or chosen > n:
        chosen = int(input("Choose a door 1-{:1d}.\n".format(n)))
    door_left = remove_wrong_doors(chosen, d)
    dont_open = [chosen, door_left]
    print("You chose the door number {:1d}.".format(chosen))
    print("...")
    print_doors(d, dont_open)
    print("{:1d} certainly wrong doors were opened. The door number {:1d} was left.".format(n - 2, door_left))
    choice = int(input("Choose {:1d} if you want to keep the door you first chose and choose {:1d} if you want to "
                       "change the door.\n".format(chosen, door_left)))
    while choice != chosen and choice != door_left:
        choice = int(
            input("Choose {:1d} if you want to keep the door you first chose and choose {:1d} if you want to change "
                  "the door.\n".format(chosen, door_left)))
    print_doors(d, [])
    if d[choice - 1]:
        print("Congratulations! The car was behind the door you chose!")
    else:
        print("A goat emerged from the door you chose! The car was behind the other door :(")


main()
