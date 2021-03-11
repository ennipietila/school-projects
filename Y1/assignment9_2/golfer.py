# Y1 AUTUMN 2020
# Basic Course in Programming Y1
# Author: Anni Niskanen
# Given class Golfer for Exercise 9.2


class Golfer:

    HOLE_SCORES = {-2 : "Eagle", -1 : "Birdie", 0 : "Par", 1 : "Bogey", 2 : "Double Bogey"}
    HOLE_SCORE_DESCRIPTIONS = ["Eagle", "Birdie", "Par", "Bogey", "Double Bogey"]

    def __init__(self, golfer_name):
        """
        Creates a new Golfer object with the name golfer_name.
        """
        self.__name = golfer_name
        self.__handicap = 28

    def get_name(self):
        """
        Returns the name of the golfer.
        """
        return self.__name

    def get_handicap(self):
        """
        Returns the handicap of the golfer.
        """
        return self.__handicap

    def play_round(self, golf_course, golfer_scores):
        """
        Plays a round of golf in the course golf_course (a GolfCourse object). The results scores of the golfer are
        given in golfer_scores, which is a list of integers (you can assume that the list is of the same length than how
        many holes golf_course has).

        Returns the difference between the player's handicap before the game and after it.
        """
        score_diff = sum(golfer_scores) - sum(golf_course.get_par_scores())
        round_handicap = score_diff * (113 / golf_course.get_slope_rating())
        new_handicap = round((self.__handicap + round_handicap) / 2)
        handicap_diff = self.__handicap - new_handicap
        self.__handicap = new_handicap
        return handicap_diff

    def get_round_statistics(self, golf_course, golfer_scores):
        """
        Returns a string describing the player's game. The course is given in the parameter golf_course (a GolfCourse
        object) and the golfer's scores in golfer_scores, a list of integers.
        """
        hole_score_list = [0] * 5
        holes_in_one = 0
        other = 0
        for i in range(golf_course.get_amount_of_holes()):
            if golfer_scores[i] == 1:
                holes_in_one += 1
            else:
                hole_score = golfer_scores[i] - golf_course.get_par_scores()[i]
                if hole_score in Golfer.HOLE_SCORES:
                    index = Golfer.HOLE_SCORE_DESCRIPTIONS.index(Golfer.HOLE_SCORES[hole_score])
                    hole_score_list[index] += 1
                else:
                    other += 1

        string = ""
        string += "{:6s} |".format("Hole")
        for i in range(golf_course.get_amount_of_holes()):
            string += "{:4d} |".format(i + 1)
        string += "\n{:s}\n".format("-" * (8 + 6 * golf_course.get_amount_of_holes()))
        string += "{:6s} |".format("Par")
        for i in range(golf_course.get_amount_of_holes()):
            string += "{:4d} |".format(golf_course.get_par_scores()[i])
        string += "\n{:6s} |".format("Score")
        for i in range(golf_course.get_amount_of_holes()):
            string += "{:4d} |".format(golfer_scores[i])

        string += "\n\n"
        for i in range(len(Golfer.HOLE_SCORE_DESCRIPTIONS)):
            string += "{:s}s: {:d}\n".format(Golfer.HOLE_SCORE_DESCRIPTIONS[i], hole_score_list[i])
        string += "Holes in one: {:d}\n".format(holes_in_one)
        string += "Other: {:d}".format(other)

        return string

    def compare_scores(self, first_golfer_results, second_golfer_results):
        """
        Compares the result scores of two golfers. To determine who won the round, it is enough to compare the
        sums of the golfers' scores.

        Returns True if the result of the first golfer is better, otherwise returns False.
        """
        return sum(first_golfer_results) < sum(second_golfer_results)

    def compare_golfers(self, other_golfer):
        """
        Compares two golfers' handicaps. The other golfer is given in the parameter other_golfer (a Golfer object).

        Returns True if the first golfer (the one who the method is called for) has a lower handicap than the
        other golfer, otherwise returns False.
        """
        return self.__handicap < other_golfer.__handicap

    def __str__(self):
        """
        Returns a string describing a Golfer object.
        """
        return "{:s}: handicap {:d}".format(self.__name, self.__handicap)
