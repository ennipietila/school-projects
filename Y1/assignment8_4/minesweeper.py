
def print_mine_grid(grid):
    dashes = len(grid[0]) * 2 + 3
    print("-" * dashes)
    for row in grid:
        print("| ", end="")
        for n in row:
            print("{:d} ".format(n), end="")
        print("|")
    print("-" * dashes)


def build_complete_grid(grid):
    rows = len(grid)
    columns = len(grid[0])
    complete = []
    for x in range(rows):
        complete.append([0] * columns)
    i = 0
    while i < rows:
        j = 0
        while j < columns:
            if grid[i][j] == 1:
                complete[i][j] = "*"
                if i > 0:
                    if complete[i - 1][j] != "*":
                        complete[i - 1][j] += 1     # top
                if i > 0 and j > 0:
                    if complete[i-1][j-1] != "*":
                        complete[i-1][j-1] += 1     # top left
                if j > 0:
                    if complete[i][j-1] != "*":
                        complete[i][j-1] += 1       # left
                if i < rows - 1 and j > 0:
                    if complete[i + 1][j - 1] != "*":
                        complete[i + 1][j - 1] += 1 # bottom left
                if i < rows - 1:
                    if complete[i + 1][j] != "*":
                        complete[i + 1][j] += 1     # bottom
                if i < rows - 1 and j < columns - 1:
                    if complete[i + 1][j + 1] != "*":
                        complete[i + 1][j + 1] += 1 # bottom right
                if j < columns - 1:
                    if complete[i][j + 1] != "*":
                        complete[i][j + 1] += 1     # right
                if i > 0 and j < columns - 1:
                    if complete[i - 1][j + 1] != "*":
                        complete[i - 1][j + 1] += 1 # top right
            j += 1
        i += 1
    return complete


def print_complete_grid(grid):
    dashes = len(grid[0]) * 2 + 3
    print("-" * dashes)
    for row in grid:
        print("| ", end="")
        for n in row:
            if n == "*":
                print("{:s} ".format(n), end="")
            else:
                print("{:d} ".format(n), end="")
        print("|")
    print("-" * dashes)


def main():
    filename = input("Enter the name of the file with the locations of the mines:\n")
    mine_grid = []
    mines = 0
    try:
        file = open(filename, 'r')
        for line in file:
            line = line.rstrip()
            parts = line.split(" ")
            row = []
            for n in parts:
                try:
                    num = int(n)
                    row.append(num)
                    if num == 1:
                        mines += 1
                    if num != 0 and num != 1:
                        print("Invalid integer '{:d}' on line: {:s}".format(num, line))
                        print("Program terminating.")
                        return
                except ValueError:
                    print("Invalid value '{:s}' on line: {:s}".format(n, line))
                    print("Program terminating.")
                    return
            mine_grid.append(row)
        if len(mine_grid) == 0:
            print("Program terminating.")
            return 
        print("This {:d}x{:d} grid has {:d} mines.".format(len(mine_grid[0]), len(mine_grid), mines))
        print("Mine grid:")
        print_mine_grid(mine_grid)
        print("\nComplete grid:")
        complete_grid = build_complete_grid(mine_grid)
        print_complete_grid(complete_grid)

    except OSError:
        print("File could not be read.")
        print("Program terminating.")


main()

