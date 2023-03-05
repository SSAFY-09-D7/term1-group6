import sys; input = sys.stdin.readline
from collections import deque
N, M = map(int,input().split())
arr = [['0' for _ in range(M)] for _ in range(N)]; sharks = deque()
for r in range(N):
    for c, v in enumerate(input().rstrip().split()):
        if v == '1': sharks.append([r,c,0])
        arr[r][c] = v

delta = [(-1,-1), (-1,1), (1,-1), (1,1), (1,0), (-1,0), (0,-1), (0,1)]
visited = [[False for _ in range(M)] for _ in range(N)]

# 처음 상어 위치가 입력된 값을 바탕으로 BFS
while sharks:
    row, col, d = sharks.popleft()
    if visited[row][col]: continue

    visited[row][col] = True
    for x, y in delta:
        nx = row + x
        ny = col + y
        
        if 0<=nx<N and 0<=ny<M and arr[nx][ny] == '0':
            arr[nx][ny] = str(d+1)
            sharks.append([nx, ny, d+1])

# 결국, 마지막으로 뽑한 노드의 distance가 최종으로 먼 거리가 된다.
print(d)

# print()
# for r in range(N):
#     print(*arr[r])
# print()
