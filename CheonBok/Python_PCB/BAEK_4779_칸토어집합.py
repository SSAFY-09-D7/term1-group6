import sys; input = sys.stdin.readline

def ShowLine(start: int, end: int, round: int):
    round //= 3   # size of deletion
    if start == end-1: return # the basis

    # index for separation
    mid = start+round 

    # delete hyphen
    for i in range(mid, mid+round):
        arr[i] = " " 

    # divide the range to do same work
    ShowLine(start, mid, round)
    ShowLine(mid+round, end, round)
    

for i in sys.stdin:
    N = int(i)
    arr = ["-" for _ in range(3**N)]

    # start, end, round
    ShowLine(0, 3**N, 3**N)
    print(''.join(arr))

