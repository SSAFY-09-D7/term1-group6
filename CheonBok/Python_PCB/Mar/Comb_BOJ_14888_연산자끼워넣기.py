import sys; input = sys.stdin.readline
from itertools import permutations
from math import inf
N = int(input())
numbers = [*map(int, input().split())]
# +, -, x, //
oper = list(map(int, input().split()))

#print(oper)
minret = float('inf')
maxret = float('-inf')

def comb(total:int, idx:int):
    if idx == N:
        global minret, maxret
        if total > maxret: maxret = total
        if total < minret: minret = total
        return

    # 각 연산자 인덱스에 값이 있으면 그 연산자를 사용.
    # 다시 돌아와 다른 순서에도 사용해보는 백트래킹 적용.
    for d in range(4):
        if oper[d]:
            oper[d] -= 1

            if d == 0: comb(total + numbers[idx], idx+1) 
            if d == 1: comb(total - numbers[idx], idx+1)
            if d == 2: comb(total * numbers[idx], idx+1)
            if d == 3:
                if total < 0:
                    ttmp = abs(total)
                    comb(-(ttmp//numbers[idx]), idx+1)
                else:
                    comb(total//numbers[idx], idx+1)
                    
            oper[d] += 1


visited = [False] * (N-1)
comb(numbers[0], 1)
print(maxret)
print(minret)