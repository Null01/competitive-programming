store = []
store_keys = {}
n, m, i = map(int, input().split())
for itx in range(i):
    line = list(map(int, input().split()))
    card_id = line[0]
    exchange = line[2: len(line)]
    exchange.sort()
    store.append({'id': card_id, 'exchange': exchange})

store.sort(key=lambda e: (e['id']))

index = 0
while True:
    if index == len(store):
        break

    if index + 1 < len(store):
        if store[index]['id'] == store[index + 1]['id']:
            ls = store[index]['exchange']
            ls.extend(store[index + 1]['exchange'])
            store[index]['exchange'] = list(set(ls))
            del store[index + 1]
            continue

    key = ''.join(map(str, store[index]['exchange']))
    key_length = len(store[index]['exchange'])
    if key in store_keys:
        if store_keys[key] < key_length:
            store_keys[key] += 1
        else:
            del store[index]
            index -= 1
    else:
        store_keys[key] = 1
    index += 1


def search(with_info: list):
    maximum = 0
    it = 0
    possible = []
    tree = {}
    backtrack = False
    level = 0
    while 0 <= it < len(store):
        if maximum == len(store):
            break

        item = None
        index = 0

        # Check if is necessary go back
        if backtrack:
            # print("{} - {} - {} - {}".format(store[it], it, "backtrack-evaluate", possible))
            last = possible.pop()
            if last == -1:
                it -= 1
                continue
            while index < len(store[it]['exchange']) and last != store[it]['exchange'][index]:
                index += 1

            del tree[last]
            index += 1

        # Evaluate next element
        while index < len(store[it]['exchange']):
            if store[it]['exchange'][index] not in tree:
                item = store[it]['exchange'][index]
                backtrack = False
                break
            index += 1

        # Jump to the next evaluate
        if item is None and it in with_info and (it + 1) < len(store):
            # print("{} - {}".format(store[it], "jump"))
            it += 1
            possible.append(-1)
            continue

        # If not exist valid element than go back to the queue
        if item is None:

            # Search again but info
            if not possible:
                if level in with_info:
                    return maximum
                with_info.append(level)
                # print("{} - ========> {} - {}".format(store[it], "re-search-with-info", with_info))
                return search(with_info)

            # print("{} - ========> {} - {}".format(store[it], "backtrack", possible))
            it -= 1
            backtrack = True
            continue

        # print("===> {} level:{} - {}".format(store[it], it, item))
        it += 1
        level = it
        tree[item] = True
        possible.append(item)

        def count_by(array: list):
            count = 0
            for element in array:
                if element != -1:
                    count += 1
            return count

        maximum = max(maximum, count_by(possible))
        # print("==================> {}".format(maximum))
    return maximum


store.sort(reverse=False, key=lambda e: (len(e['exchange']), e['exchange']))

maxim = search([])
print(maxim)
