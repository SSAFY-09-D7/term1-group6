'''
1. 같은 학년 구분, 성별 구분해서 방 구성하는 경우의 수  (최소로 만들 수 있도록)
2. K : 한 방에 배정할 수 있는 최대 수

 1 <= N <= 1000
 1 <  K <= 1000
N개의 입력 = 성별(S), 학년(Y)
'''
import sys; input = sys.stdin.readline
N, K = map(int, input().split())
grades = [[0, 0] for _ in range(7)] # 0 인덱스 X

for _ in range(N):
    S, Y = map(int, input().split())
    grades[Y][S] += 1

ret = 0
for g in grades:
    # woman rooms by grades
    wroom, re = divmod(g[0], K)
    if re: ret += wroom+1
    else:  ret += wroom

    # man rooms by grades
    mroom, re = divmod(g[1], K)
    if re: ret += mroom+1
    else:  ret += mroom

print(ret)