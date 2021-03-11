def count_smalls_1(lst):
    i = 0
    small_items = 0
    while i < len(lst):
        if lst[i] < 30:
            small_items += 1
        i += 1
        return small_items
    return small_items


def count_smalls_2(lst):
    i = 0
    small_items = 0
    while i < len(lst) and lst[i] < 30:
        small_items += 1
        i += 1
    return small_items


def count_smalls_3(lst):
    i = 0
    small_items = 0
    while i < len(lst):
        if lst[i] < 30:
            small_items += 1
        i += 1
    return small_items


def count_smalls_4(lst):
    i = 0
    small_items = 0
    while i < len(lst):
        if lst[i] < 30:
            small_items += 1
        else:
            return small_items
        i += 1
    return small_items


a = [10,20,50,25,90,30]
b = sorted(a)
print(count_smalls_1(b))
print(count_smalls_2(b))
print(count_smalls_3(b))
print(count_smalls_4(b))
