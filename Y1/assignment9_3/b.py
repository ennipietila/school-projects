import backpack


def main():
    mybackpack = backpack.Backpack()
    mybackpack.add_item("Towel")
    # mybackpack.append("Brush")
    # mybackpack.__items.append("Towel")
    mybackpack.get_items().append("Brush")
    for item in mybackpack.get_items():
        print(item)


main()