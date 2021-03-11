# CS-A111 Fall 2020
# A test program for Exercise 9.4
#
# Originally written in Java by TAs in former Java course.
# Updated and translated to python by Kerttu Pollari-Malmi
#
# The program creates Protozoan objects and tests their methods.

import protozoan


# The function reads a line containing integers separated by commas. The
# function creates and returns a list which contains the same integers.

def read_genes():
    line = input()
    list1 = line.split(",")
    genes_input = []
    for element in list1:
        try:
            genes_input.append(int(element))
        except ValueError:
            genes_input.append(protozoan.Protozoan.GENE_MIN_VALUE)
    return genes_input


def main():
    print("Happens in the primeval sea ...")
    print("In the beginning, there were two protozoans, Adam and Eve.")
    print("Enter Adam's genes separated by a comma.")
    gene_info = read_genes()
    adam = protozoan.Protozoan("Adam", gene_info)
    print("Enter Eve's genes separated by a comma.")
    gene_info = read_genes()
    eve = protozoan.Protozoan("Eve", gene_info)
    print()
    print('Adam said: "Oh, you are beautiful."')
    dolly1 = adam.mate(eve, "Dolly1")
    print("After that, the number of the protozoans was three:")
    print(adam)
    print(eve)
    print(dolly1)
    print()
    print("Dolly felt lonely and decided to clone herself.")
    dolly2 = dolly1.clone("Dolly2")
    print("No one could distinguish one Dolly from the other.")
    print(dolly1)
    print(dolly2)
    print()
    print("But then something happened to the latter Dolly:")
    # The following lines test mutation method by giving
    # purposely also invalid gene numbers and values. If your program
    # does not work correctly with this, there is a mistake in your
    # mutation method.
    for i in range(-4, protozoan.Protozoan.NUMBER_OF_GENES + 2, 2):
        dolly2.mutation(i, i - 1)
    print(dolly2)
    print("However, the first Dolly had not changed.")
    print(dolly1)
    # If the the first Dolly did change, there is a mistake in your
    # __init__ or clone method.
    print()
    print("The first Dolly met a stranger from the outer space.")
    print("Enter the stranger's genes.")
    gene_info = read_genes()
    stranger = protozoan.Protozoan("Gregory", gene_info)
    print("And one thing led to another...")
    junior = dolly1.mate(stranger, "DollyJr")
    print(junior)
    print()
    print("And they all lived happily ever after.")
    print(adam)
    print(eve)
    print(dolly1)
    print(stranger)
    print(junior)
    print(dolly2)


main()
