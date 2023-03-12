import sys; input = sys.stdin.readline
from collections import defaultdict
Tcase = int(input())
for _ in range(Tcase):
    N = int(input())
    cloth = defaultdict(int)

    for _ in range(N):
        a, t = input().rstrip().split()
        cloth[t] += 1 
    
    cntList = list(cloth.values())
    ret = 1
    for c in cntList:
        ret *= c+1  # choose cloth + not wearing
    
    print(ret-1) # not include wearing nothing (-1)