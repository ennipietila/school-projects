class Backpack:
    def __init__(self):
        self.__items = []

    def add_item(self, item):
        self.__items.append(item)

    def get_items(self):
        return self.__items

