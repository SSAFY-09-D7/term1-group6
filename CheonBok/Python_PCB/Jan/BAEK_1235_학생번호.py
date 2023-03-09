import sys; input = sys.stdin.readline
c = int(input())
arr = [input().rstrip() for _ in range(c)]
idx = len(arr[0])-1

while True:
    passmap = dict()
    flag = 1

    for tmp in arr:
        if tmp[idx:] not in passmap: passmap[tmp[idx:]] = 1
        else:
            # if the password is in passmap dict, can't make.
            flag = 0
            break

    # print minimum password length.
    if flag:
        print(len(arr[0]) - idx)
        break

    idx -= 1
    
            