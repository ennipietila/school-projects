# Y1 AUTUMN 2020
# Basic Course in Programming Y1
# Author: Nea Rantanen
# Template for Exercise 6.4

import random

WIN = 60
SINGLE_PIG = ["Side (no dot)", "Side (dot)", "Razorback", "Trotter", "Snouter", "Leaning Jowler"]
PROBS = [0.35, 0.65, 0.87, 0.96, 0.99, 1]
SINGLE_PIG_POINTS = {"Side (no dot)":0, "Side (dot)":0, "Razorback":5, "Trotter":5, "Snouter":10, "Leaning Jowler":15}
BOTH_PIGS = {"Side (no dot)":"Sider", "Side (dot)":"Sider", "Razorback":"Double Razorback", "Trotter":"Double Trotter", "Snouter":"Double Snouter", "Leaning Jowler":"Double Leaning Jowler"}
BOTH_PIGS_POINTS = {"Sider":1, "Double Razorback":20, "Double Trotter":20, "Double Snouter":40, "Double Leaning Jowler":60}


def throw_pig():
    r = random.random()
    a = 0
    while r > PROBS[a]:
        a += 1
    return SINGLE_PIG[a]


def throw_two_pigs():
    first = throw_pig()
    second = throw_pig()
    if first == second:
        name = BOTH_PIGS[first]
        points = BOTH_PIGS_POINTS[name]
        return name, points
    elif (first == SINGLE_PIG[0] and second == SINGLE_PIG[1]) or (first == SINGLE_PIG[1] and second == SINGLE_PIG[0]):
        return "Pig out", 0
    else:
        points1 = SINGLE_PIG_POINTS[first]
        points2 = SINGLE_PIG_POINTS[second]
        name = "Other combo: " + first + " + " + second
        return name, points1 + points2


def player_turn(total):
    round_points = 0
    a = True
    throw = 1
    while a:
        x = throw_two_pigs()
        name = x[0]
        p = x[1]
        round_points += p
        print("{:d}. {:s}, {:d} points".format(throw, name, p))
        if name == "Pig out":
            print("The turn ended. Points from this turn were set to 0.")
            return 0
        elif total + round_points >= 60:
            print("{:d} points gathered this round!".format(round_points))
            print("The total score of {:d} (>= 60) points reached! The turn ends.".format(total + round_points))
            a = False
        else:
            print("{:d} points gathered this round!".format(round_points))
            line = input('Enter "yes" if you want to continue your turn.\n')
            if line == "yes":
                throw += 1
            else:
                a = False
    return round_points


def comp_turn(total):
    round_points = 0
    a = True
    throw = 1
    while a:
        x = throw_two_pigs()
        name = x[0]
        p = x[1]
        round_points += p
        print("{:d}. {:s}, {:d} points".format(throw, name, p))
        if name == "Pig out":
            print("The turn ended. Points from this turn were set to 0.")
            return 0
        elif total + round_points >= 60:
            print("{:d} points gathered this round!".format(round_points))
            print("The total score of {:d} (>= 60) points reached! The turn ends.".format(total + round_points))
            a = False
        elif round_points >= 10:
            print("{:d} points gathered this round!".format(round_points))
            a = False
        else:
            print("{:d} points gathered this round!".format(round_points))
            throw += 1
    return round_points


def main():
    print("Play a game of pass the pigs against the computer!")
    seed = int(input("Set seed:\n"))
    random.seed(seed)
    user_total = 0
    comp_total = 0

    while user_total < WIN and comp_total < WIN:
        print("------------------------------")
        print("It's your turn to pass the pigs!")
        n = player_turn(user_total)
        user_total += n
        input("Press enter to continue.\n")
        if user_total < 60:
            print("------------------------------")
            print("It's computer's turn to pass the pigs!")
            y = comp_turn(comp_total)
            comp_total += y
            input("Press enter to continue.\n")
            print("------------------------------")
            print("Your score : {:d}".format(user_total))
            print("Computer's score: {:d}".format(comp_total))

    if comp_total < user_total:
        print("------------------------------")
        print("It's computer's turn to pass the pigs!")
        points = 0
        throw = 1
        a = True
        while a:
            x = throw_two_pigs()
            name = x[0]
            p = x[1]
            points += p
            print("{:d}. {:s}, {:d} points".format(throw, name, p))
            if name == "Pig out":
                print("The turn ended. Points from this turn were set to 0.")
                points = 0
                a = False
            elif points + comp_total > user_total:
                print("{:d} points gathered this round!".format(points))
                print("The total score of {:d} (>= {:d}) points reached! The turn ends.".format(comp_total + points, user_total + 1))
                a = False
            else:
                print("{:d} points gathered this round!".format(points))
                throw += 1
        comp_total += points
        input("Press enter to continue.\n")
        print("------------------------------")
        print("Your score: {:d}".format(user_total))
        print("Computer's score: {:d}".format(comp_total))

    if comp_total > user_total:
        print("\nComputer won!")
    else:
        print("\nYou won! Congratulations!")


main()
