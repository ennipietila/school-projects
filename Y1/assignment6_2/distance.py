import math

def calculate_distance_between_points(point1, point2):
    x1 = point1[0]
    y1 = point1[1]
    x2 = point2[0]
    y2 = point2[1]
    d = math.sqrt((x1 - x2) ** 2 + (y1 - y2) ** 2)
    return d


def calculate_total_distance(point_list):
    total = 0
    i = 0
    while i < len(point_list) - 1:
        total += calculate_distance_between_points(point_list[i], point_list[i + 1])
        i += 1
    return total


def main():
    print("Enter the coordinates in the format 'x,y'. Stop with an empty line.")
    li = input()
    list_ = []
    while li != "":
        line = li.split(",")
        coord = []
        for x in line:
            number = float(x)
            coord.append(number)
        if len(coord) != 2:
            print("Enter the coordinates in the format 'x,y'.")
        else:
            list_.append(coord)
        li = input("Enter the coordinates:\n")
    if len(list_) < 2:
        print("Not enough data.")
    else:
        total = calculate_total_distance(list_)
        straight = calculate_distance_between_points(list_[0], list_[-1])
        print("The total distance going through all the points is {:.2f} units long.".format(total))
        print("The straight line from the starting point to the end is {:.2f} units long.".format(straight))


main()
