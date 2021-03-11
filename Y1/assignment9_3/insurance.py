class Insurance:
    def __init__(self, owner_name, original_premium, given_deductible, cap):
        self.__name = owner_name
        if original_premium < 0:
            self.__premium = 0.0
        else:
            self.__premium = original_premium
        self.__bonus = 0.0
        if given_deductible < 0:
            self.__deductible = 150.0
        else:
            self.__deductible = given_deductible
        self.__total_compensations = 0.0
        if cap <= 0:
            self.__total_compensation_cap = 15000.0
        else:
            self.__total_compensation_cap = cap
        self.__valid = True

    def get_name(self):
        return self.__name

    def get_bonus(self):
        return self.__bonus

    def get_premium(self):
        return self.__premium

    def get_deductible(self):
        return self.__deductible

    def get_total_compensations(self):
        return self.__total_compensations

    def get_total_compensation_cap(self):
        return self.__total_compensation_cap

    def is_valid(self):
        return self.__valid

    def set_premium(self, new_premium):
        if new_premium >= 0:
            self.__premium = new_premium

    def set_deductible(self, new_deductible):
        if new_deductible >= 0:
            self.__deductible = new_deductible

    def increase_bonus(self, increase):
        if increase > 0:
            self.__bonus += increase
        if self.__bonus > 70.0:
            self.__bonus = 70.0

    def decrease_bonus(self, decrease):
        if decrease > 0:
            if decrease > self.__bonus:
                self.__bonus = 0.0
            else:
                self.__bonus -= decrease

    def set_invalid(self):
        self.__valid = False

    def set_valid(self):
        self.__valid = True

    def calculate_real_premium(self):
        real_premium = self.__premium * (1 - self.__bonus / 100)
        return real_premium

    def calculate_compensation(self, damage):
        comp_to_be_paid = damage - self.__deductible
        comp_left = self.__total_compensation_cap - self.__total_compensations
        if not self.__valid or self.__total_compensations == self.__total_compensation_cap or comp_to_be_paid <= 0.0:
            comp_to_be_paid = 0.0
        else:
            if comp_to_be_paid < comp_left:
                self.__total_compensations += comp_to_be_paid
            else:
                comp_to_be_paid = comp_left
                self.__total_compensations = self.__total_compensation_cap
        if comp_to_be_paid > 0.0:
            self.decrease_bonus(10.0)
        return comp_to_be_paid

    def __str__(self):
        v = ""
        if self.__valid:
            v = "valid"
        else:
            v = "not valid"

        s = "Owner: " + self.get_name() + ", premium " + str(self.get_premium()) + " eur / year, bonus " + \
            str(self.get_bonus()) + " %.\n" + "Deductible " + str(self.get_deductible()) + " eur, total compensation cap " + \
            str(self.get_total_compensation_cap()) + " eur.\nTotal compensations currently " + \
            str(self.get_total_compensations()) + " eur.\nInsurance policy is " + v + "."

        return s

