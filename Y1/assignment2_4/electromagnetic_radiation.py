

line = input("Enter the wavelength of the electromagnetic radiation in metres:\n")

wl = float(line)

if wl <= 0.0 or wl > 100000 * 10 ** 3:
    print("The wavelength doesn't correspond to any type of radiation.")
elif wl <= 10 * 10 ** (-12):
    print("The radiation is gamma radiation.")
    print("The radiation is highly dangerous!")
elif wl <= 10 * 10 ** (-9):
    print("The radiation is X-radiation.")
elif wl <= 400 * 10 ** (-9):
    print("The radiation is ultraviolet radiation.")
elif wl <= 700 * 10 ** (-9):
    print("The radiation is visible light.")
    if wl <= 450 * 10 ** (-9):
        print("The light is violet.")
    elif wl <= 490 * 10 ** (-9):
        print("The light is blue.")
    elif wl <= 560 * 10 ** (-9):
        print("The light is green.")
    elif wl <= 590 * 10 ** (-9):
        print("The light is yellow.")
    elif wl <= 630 * 10 ** (-9):
        print("The light is orange.")
    else:
        print("The light is red.")
elif wl <= 1 * 10 ** (-3):
    print("The radiation is infrared light.")
elif wl <= 1:
    print("The radiation is microwaves.")
else:
    print("The radiation is radio waves")

