print("Welcome to the darts calculator! I will keep track of your game of darts")

line = input("What is the start score of the game?\n")
ss = int(line)

points = ss
r = 0

while points > 0:
    initialPoints = points
    r += 1
    print("Enter the results of your throws for round ", r, ":")
    line = input("Throw 1: ")
    points -= int(line)
    line = input("Throw 2: ")
    points -= int(line)
    line = input("Throw 3: ")
    points -= int(line)
    if points > 1:
        print("You have ", points, " points remaining.")
    elif points == 0:
        print("You have ", points, " points remaining.")
        print("You have won the game after ", r, " rounds. Congratulations!")
    else:
        print("You have reduced your score to ", points, ". Score resetting to the initial score of the round.")
        points = initialPoints
        print("You have ", points, " points remaining.")

