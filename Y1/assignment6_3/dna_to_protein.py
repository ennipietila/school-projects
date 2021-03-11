dna_to_rna_bases = {"G": "C", "C": "G", "A": "U", "T": "A"}
amino_acids = {"UUU": "Phe", "UUC": "Phe", "UUA": "Leu", "UUG": "Leu",
               "CUU": "Leu", "CUC": "Leu", "CUA": "Leu", "CUG": "Leu",
               "AUU": "Ile", "AUC": "Ile", "AUA": "Ile", "AUG": "Met",
               "GUU": "Val", "GUC": "Val", "GUA": "Val", "GUG": "Val",
               "UCU": "Ser", "UCC": "Ser", "UCA": "Ser", "UCG": "Ser",
               "CCU": "Pro", "CCC": "Pro", "CCA": "Pro", "CCG": "Pro",
               "ACU": "Thr", "ACC": "Thr", "ACA": "Thr", "ACG": "Thr",
               "GCU": "Ala", "GCC": "Ala", "GCA": "Ala", "GCG": "Ala",
               "UAU": "Tyr", "UAC": "Tyr", "UAA": "STOP", "UAG": "STOP",
               "CAU": "His", "CAC": "His", "CAA": "Gln", "CAG": "Gln",
               "AAU": "Asn", "AAC": "Asn", "AAA": "Lys", "AAG": "Lys",
               "GAU": "Asp", "GAC": "Asp", "GAA": "Glu", "GAG": "Glu",
               "UGU": "Cys", "UGC": "Cys", "UGA": "STOP", "UGG": "Trp",
               "CGU": "Arg", "CGC": "Arg", "CGA": "Arg", "CGG": "Arg",
               "AGU": "Ser", "AGC": "Ser", "AGA": "Arg", "AGG": "Arg",
               "GGU": "Gly", "GGC": "Gly", "GGA": "Gly", "GGG": "Gly"
               }


def check_dna(dna):
    if len(dna) % 3 != 0:
        return False
    for n in dna:
        if n != "A" and n != "T" and n != "G" and n != "C":
            return False
    return True


def dna_to_rna(dna):
    rna = ""
    for i in dna:
        rna = rna + dna_to_rna_bases[i]
    return rna


def rna_to_protein(rna):
    s = 0
    e = 2
    li = []
    while e < len(rna):
        x = rna[s:e + 1]
        corresponding = amino_acids[x]
        if corresponding == "STOP":
            li.append("*")
        else:
            li.append(corresponding)
        s += 3
        e += 3
    final = "-".join(li)
    return final


def main():
    line = (input("Enter the DNA sequence:\n")).upper()
    if not check_dna(line):
        print("Sorry, {:s} is not a valid DNA sequence for protein synthesis.".format(line))
    else:
        print("DNA: {:s}".format(line))
        r = dna_to_rna(line)
        print("RNA: {:s}".format(r))
        p = rna_to_protein(r)
        print("Protein: {:s}".format(p))
        print("The protein has {:1d} amino acids in total.".format(len(line) // 3))


main()
