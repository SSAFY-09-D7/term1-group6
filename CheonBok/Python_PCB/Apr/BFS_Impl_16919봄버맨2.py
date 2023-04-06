import sys;

input = sys.stdin.readline
from collections import deque

R, C, N = map(int, input().split())
# 각 경우의 프레임을 저장할 3차원
frame = [[['O' for _ in range(C)] for _ in range(R)] for _ in range(4)]
q = deque()

for r in range(R):
    for idx, c in enumerate(list(input().rstrip())):
        frame[0][r][idx] = c  # Frame1 구성
        if c == 'O': q.append((r, idx))

def makeMap(n:int):
    while q:
        px, py = q.popleft()
        frame[n][px][py] = '.'

        for dx, dy in [(-1, 0), (1, 0), (0, 1), (0, -1)]:
            nx = dx + px
            ny = dy + py

            if 0 <= nx < R and 0 <= ny < C:
                frame[n][nx][ny] = '.'

# Frame3 구성: 첫 폭탄이 터진 이후
makeMap(2)

# Frame4 구성: 다음 폭탄이 터진 이후
for r in range(R):
    for c in range(C):
        if frame[2][r][c] == 'O': q.append((r, c))
makeMap(3)


def printline(n):
    for r in range(R):
        print(''.join(frame[n][r]))

if N <= 1:
    printline(0)
elif N % 2 == 0:
    printline(1)
elif N % 4 == 1:
    printline(3)
elif N % 4 == 3:
    printline(2)