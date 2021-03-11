# Y1 AUTUMN 2020
# Basic Course in Programming Y1
# Author: Anni Niskanen
# Given class GolfCourse for Exercise 9.2


class GolfCourse:

    def __init__(self, course_name, course_par_scores, slope_rating):
        """
        Creates a new GolfCourse object with the name course_name, par scores listed in course_par_scores (a list
        of integers) and a slope rating of slope_rating (an integer).
        """
        self.__name = course_name
        self.__par_scores = course_par_scores
        self.__slope_rating = slope_rating

    def get_name(self):
        """
        Returns the name of the golf course.
        """
        return self.__name

    def get_par_scores(self):
        """
        Returns the golf course's par scores (a list of integers).
        """
        return self.__par_scores

    def get_amount_of_holes(self):
        """
        Returns the amount of holes in the golf course, determined by the length of list of the par scores.
        """
        return len(self.__par_scores)

    def get_slope_rating(self):
        """
        Returns the slope rating of the golf course (an integer).
        """
        return self.__slope_rating

    def __str__(self):
        """
        Returns a string describing the golf course and its par scores.
        """
        string = "{:s}: {:d} holes, slope rating of {:d}\n".format(self.__name, self.get_amount_of_holes(), self.__slope_rating)
        string += "{:6s} |".format("Hole")
        for i in range(self.get_amount_of_holes()):
            string += "{:4d} |".format(i + 1)
        string += "\n{:s}\n".format("-" * (8 + 6 * self.get_amount_of_holes()))
        string += "{:6s} |".format("Par")
        for i in range(self.get_amount_of_holes()):
            string += "{:4d} |".format(self.get_par_scores()[i])
        return string
