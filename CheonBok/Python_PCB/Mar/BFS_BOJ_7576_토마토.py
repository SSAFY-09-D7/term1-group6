import sys; input = sys.stdin.readline
from collections import deque
M, N, H = map(int, input().split())  # N x M x H

# 3-dimension
remain = 0
tomato  = [[[0 for _ in range(M)] for _ in range(N)] for _ in range(H)]
visited = [[[False for _ in range(M)] for _ in range(N)] for _ in range(H)]

q = deque()
for h in range(H):
    for n in range(N):
        for m, v in enumerate(list(input().rstrip().split())):
            if v == '0': remain += 1
            if v == '1': 
                tomato[h][n][m] = 1
                visited[h][n][m] = True
                q.append([h, n, m, 0])  #0 = 차수(일수)
            if v == '-1': tomato[h][n][m] = -1

# 위, 아래, 왼쪽, 오른쪽, 앞, 뒤
delta = [(0,-1,0), (0,1,0), (0,0,-1), (0,0,1), (-1,0,0), (1,0,0)]
while q:
    #3차, 2차, 1차 인덱스, 일수(진행 차수)
    th, tx, ty, td = q.popleft()
    
    for dh, dx, dy in delta:
        nh, nx, ny = th+dh, tx+dx, ty+dy

        if 0<=nh<H and 0<=nx<N and 0<=ny<M and tomato[nh][nx][ny] == 0 and not visited[nh][nx][ny]:
            visited[nh][nx][ny] = 1
            remain -= 1
            q.append([nh, nx, ny, td+1])


# remain(0인 cell)이 있으면 -1
if remain: print(-1)
else: print(td)

    