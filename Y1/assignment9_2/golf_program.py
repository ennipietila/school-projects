# Y1 AUTUMN 2020
# Basic Course in Programming Y1
# Author: Anni Niskanen
# Template for Exercise 9.2


from golfer import Golfer
from golf_course import GolfCourse


def ask_list_of_integers():
    """
    Asks the user for integers separated by commas. Converts the inputted string into a list of integers and
    returns it.
    """
    string = input()
    list_in_characters = string.split(",")  # creates a list
    list_in_integers = []
    for character in list_in_characters:
        list_in_integers.append(int(character))
    return list_in_integers


def main():
    input("Press enter to continue.\n")

    # Create two golfers and one golf course here
    name1 = input("Name of the first golfer:\n")
    name2 = input("Name of the second golfer:\n")
    course_name = input("Name of the golf course:\n")
    print("Par scores of the golf course (separated by commas):")
    par_scores = ask_list_of_integers()

    slope_rating = int(input("Slope rating of the golf course:\n"))
    golfer1 = Golfer(name1)
    golfer2 = Golfer(name2)
    golf_course = GolfCourse(course_name, par_scores, slope_rating)
    input()

    # Print information about the golfers and the golf course here
    print("The golfers:")
    print("{:s}: handicap {:d}".format(golfer1.get_name(), golfer1.get_handicap()))
    print("{:s}: handicap {:d}\n".format(golfer2.get_name(), golfer2.get_handicap()))
    print("The golf course:")
    print(golf_course)
    input()

    # The golfers play a round of Golf here
    print("{:s} and {:s} are off to play a round of Golf in {:s}!".format(golfer1.get_name(), golfer2.get_name(), golf_course.get_name()))
    print("Enter {:s}'s results (separated by commas):".format(golfer1.get_name()))
    results1 = ask_list_of_integers()
    print("Enter {:s}'s results (separated by commas):".format(golfer2.get_name()))
    results2 = ask_list_of_integers()
    handicap_diff1 = golfer1.play_round(golf_course, results1)
    handicap_diff2 = golfer2.play_round(golf_course, results2)
    input()

    # Print the results of the first golfer here
    print("{:s}'s round looks like this:".format(golfer1.get_name()))
    print(golfer1.get_round_statistics(golf_course, results1))
    input()

    # Print the results of the second golfer here
    print("And {:s}'s round looks like this:".format(golfer2.get_name()))
    print(golfer2.get_round_statistics(golf_course, results2))
    input()

    # Determine and print which golfer won the game here
    first_won = golfer1.compare_scores(results1, results2)
    if first_won:
        print("{:s} won the round!".format(golfer1.get_name()))
    else:
        print("{:s} won the round!".format(golfer2.get_name()))
    input()

    # Print how much the golfers' handicaps lowered here
    print("The golfers have played {:d} holes in {:s} and count their new handicaps.".format(golf_course.get_amount_of_holes(), golf_course.get_name()))
    print("{:s}'s handicap lowered by {:d}.".format(golfer1.get_name(), handicap_diff1))
    print("{:s}'s handicap lowered by {:d}.".format(golfer2.get_name(), handicap_diff2))
    input()

    # Print information about the golfers again here
    print("The golfers again:")
    print("{:s}: handicap {:d}".format(golfer1.get_name(), golfer1.get_handicap()))
    print("{:s}: handicap {:d}".format(golfer2.get_name(), golfer2.get_handicap()))
    input()

    # Compare the golfers with each other and print which one has a lower handicap
    golfer1_lower = golfer1.compare_golfers(golfer2)
    if golfer1_lower:
        print("{:s} has a lower handicap.".format(golfer1.get_name()))
    else:
        print("{:s} has a lower handicap.".format(golfer2.get_name()))


main()
