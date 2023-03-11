import sys; input = sys.stdin.readline
from collections import deque
N, M = map(int,input().split())
move = [0]*101
visited = [False]*101

for _ in range(N): # append ladder
    a, b = map(int, input().split())
    move[a] = b

for _ in range(M): # append snake
    a, b = map(int, input().split())
    move[a] = b


q = deque() 
q.append([1, 0]) # start
visited[1] = True

while q:
    node, cnt = q.popleft()
    
    if move[node] != 0: node = move[node]  # can move case
    if node == 100: ret = cnt; break 

    for d in range(1, 7): # dice number (1~6)
        next = node + d

        if next <= 100 and  not visited[next]:
            visited[next] = True
            q.append([next, cnt+1])

print(ret)
        
    