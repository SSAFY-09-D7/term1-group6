import sys; input = sys.stdin.readline
from collections import deque
N, M = map(int, input().split())
# -------------------------------
#   Rs = fee rate per weight
#   Wk = weight of car
#   used = Parking state
#   wait = waiting for parking
# -------------------------------
Rs = []; Wk = [0 for _ in range(M+1)]; used = [0 for _ in range(N)]
wait = deque()

for _ in range(N):
    Rs.append(int(input()))

for idx in range(1, M+1):
    Wk[idx] = int(input())

fee = 0
for _ in range(2*M):
    flg = int(input())

    if flg > 0:
        if 0 not in used:    # full state
            wait.append(flg)
        else:
            used[used.index(0)] = flg
    
    elif flg < 0:
        flg = abs(flg)
        idx = used.index(flg)
        fee += Rs[idx] * Wk[flg]   # pay

        # 
        if (wait):
            used[idx] = wait.popleft()
        else:
            used[idx] = 0
        
print(fee)
        
