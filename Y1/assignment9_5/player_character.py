# Y1 AUTUMN 2020
# Basic Course in Programming Y1
# Author: Anni Niskanen
# Template for Exercise 9.5


import random


class PlayerCharacter:

    ABILITIES = ["Strength", "Dexterity", "Constitution", "Intelligence", "Wisdom", "Charisma"]

    SKILLS_TO_ABILITIES = {"Acrobatics" : "Dexterity", "Animal Handling" : "Wisdom", "Arcana" : "Intelligence",
                           "Athletics" : "Strength", "Deception" : "Charisma", "History" : "Intelligence",
                           "Insight" : "Wisdom", "Intimidation" : "Charisma", "Investigation" : "Intelligence",
                           "Medicine" : "Wisdom", "Nature" : "Intelligence", "Perception" : "Wisdom",
                           "Performance" : "Charisma", "Persuasion" : "Charisma", "Religion" : "Intelligence",
                           "Sleight of Hand" : "Dexterity", "Stealth" : "Dexterity", "Survival" : "Wisdom"}

    ABILITY_SCORES_TO_MODIFIERS = {1 : -5, 2 : -4, 3 : -4, 4 : -3, 5 : -3, 6 : -2, 7 : -2, 8 : -1, 9 : -1, 10 : 0,
                                   11 : 0, 12 : 1, 13 : 1, 14 : 2, 15 : 2, 16 : 3, 17 : 3, 18 : 4, 19 : 4, 20 : 5}

    def __init__(self, character_name, race, character_class, hit_die, armor_class):
        self.__name = character_name
        self.__race = race
        self.__class = character_class
        self.__hit_die = hit_die
        self.__armor_class = armor_class
        self.__level = 1
        abilities = {}
        i = 0
        for i in range(len(PlayerCharacter.ABILITIES)):
            abilities[PlayerCharacter.ABILITIES[i]] = PlayerCharacter.roll_ability_score()
        self.__ability_scores = abilities
        score_constitution = self.__ability_scores["Constitution"]
        modified = PlayerCharacter.ABILITY_SCORES_TO_MODIFIERS[score_constitution]
        self.__max_hp = self.__hit_die + modified
        self.__hp = self.__max_hp

    def get_name(self):
        return self.__name

    def get_race(self):
        return self.__race

    def get_class(self):
        return self.__class

    def get_level(self):
        return self.__level

    def is_downed(self):
        if self.__hp == 0:
            return True
        else:
            return False

    def get_ability_modifier(self, ability):
        if ability in PlayerCharacter.ABILITIES:
            score = self.__ability_scores[ability]
            modifier = PlayerCharacter.ABILITY_SCORES_TO_MODIFIERS[score]
            return modifier
        else:
            return None

    def get_skill_level(self, skill):
        if skill in PlayerCharacter.SKILLS_TO_ABILITIES:
            ability = PlayerCharacter.SKILLS_TO_ABILITIES[skill]
            modifier = self.get_ability_modifier(ability)
            return modifier
        else:
            return None

    def skill_check(self, skill, result_to_pass):
        roll = self.roll_die(20)
        skill_level = self.get_skill_level(skill)
        result = roll + skill_level
        if result >= result_to_pass:
            return True
        else:
            return False

    def level_up(self):
        self.__level += 1
        result_of_roll = self.roll_die(self.__hit_die)
        self.__max_hp += result_of_roll
        self.__hp += result_of_roll
        return result_of_roll

    def attack(self, other_character):
        result_of_roll = self.roll_die(20)
        die = 0
        if result_of_roll >= other_character.__armor_class:
            if self.__class == "Fighter":
                die = self.roll_die(6)
            elif self.__class == "Ranger":
                die = self.roll_die(8)
            elif self.__class == "Wizard":
                die = self.roll_die(10)
            else:
                die = self.roll_die(4)
        hit_points = other_character.__hp
        if die <= hit_points:
            damage = die
        else:
            damage = hit_points
        other_character.__hp -= damage
        return damage

    def heal(self):
        hp_left = self.__max_hp - self.__hp
        rand = self.roll_die(5)
        if hp_left >= rand:
            healed = rand
            self.__hp += healed
        else:
            healed = hp_left
            self.__hp += healed
        return healed

    def __str__(self):
        s = self.get_name() + ", a level " + str(self.get_level()) + " " + self.get_race() + " " + self.get_class() + \
            "\nHP: " + str(self.__hp) + "/" + str(self.__max_hp) + "\nSTR: {:2d}   DEX: {:2d}   CON: {:2d}   INT: {:2d}   WIS: {:2d}   CHA: {:2d}   "
        i = s.format(self.__ability_scores["Strength"], self.__ability_scores["Dexterity"], self.__ability_scores["Constitution"], self.__ability_scores["Intelligence"], self.__ability_scores["Wisdom"], self.__ability_scores["Charisma"])

        return i

    @staticmethod
    def roll_die(die_sides):
        return random.randint(1, die_sides)

    @staticmethod
    def roll_ability_score():
        roll_results = [PlayerCharacter.roll_die(6) for i in range(4)]  # throwing 4 6-sided dies
        smallest = 1000  # just some very large value
        for result in roll_results:  # choosing 3 largest results
            if result < smallest:
                smallest = result
        roll_results.remove(smallest)  # removing smallest result
        roll_sum = sum(roll_results)  # adding 3 largest results
        return roll_sum
