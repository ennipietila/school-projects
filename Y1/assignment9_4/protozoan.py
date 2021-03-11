class Protozoan:
    NUMBER_OF_GENES = 10
    GENE_MIN_VALUE = 0
    GENE_MAX_VALUE = 5

    def __init__(self, given_name, genelist):
        self.__name = given_name
        genes = genelist.copy()
        self.__genes = genes
        if len(genes) != Protozoan.NUMBER_OF_GENES:
            self.__genes = [0] * 10
        else:
            x = True
            i = 0
            while x and i < Protozoan.NUMBER_OF_GENES:
                if genes[i] < Protozoan.GENE_MIN_VALUE or genes[i] > Protozoan.GENE_MAX_VALUE:
                    self.__genes = [0] * 10
                    x = False
                i += 1

    def get_name(self):
        return self.__name

    def get_genes(self):
        return self.__genes

    def mutation(self, gene_no, new_gene):
        if 0 <= gene_no <= 9 and 0 <= new_gene <= 5:
            self.__genes[gene_no] = new_gene
            return True
        else:
            return False

    def clone(self, clone_name):
        new_genes = self.get_genes()
        clone = Protozoan(clone_name, new_genes)
        return clone

    def mate(self, another_protozoan, descendant_name):
        new_genes = [0] * 10
        this_p = self.get_genes()
        another_p = another_protozoan.get_genes()
        i = 0
        while i < 10:
            if i % 2 == 0:
                new_genes[i] = another_p[i]
            else:
                new_genes[i] = this_p[i]
            i += 1
        new_p = Protozoan(descendant_name, new_genes)
        return new_p

    def __str__(self):
        s = "Name: " + self.get_name() + ", genes: " + str(self.get_genes())
        return s


