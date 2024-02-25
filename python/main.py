# arr = [2, 6]
# brr = [24, 36]


# arr = [2, 4]
# brr = [16, 32, 96]


# arr = [3, 4]
# brr = [24, 48]

# arr = [1]
# brr = [72, 48]

arr = [5]
brr = [10]


def calc_mod(a, b):
    if a >= b:
        return a % b == 0
    else:
        return b % a == 0


arr = sorted(arr)
brr = sorted(brr)

l = []

for val in range(arr[0], brr[len(brr) - 1] + 1):
    possible = True
    for a in arr:
        if not calc_mod(a, val) or val < a:
            possible = False
        if not possible:
            break
    if possible:
        add = True
        for b in brr:
            if not calc_mod(val, b) or val > b:
                add = False
        if add:
            l.append(val)
print(len(l))
