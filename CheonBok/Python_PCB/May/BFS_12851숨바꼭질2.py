from collections import deque
N, M = map(int, input().split())
move = [100001] * 100001
move[N] = 0
q = deque()
q.append(N)
minv = 100001
cnt = 0
while q:
    pos = q.popleft()

    if move[pos] > minv: continue
    print(pos, move[pos])

    if pos == M and minv >= move[pos]:
        minv = move[pos]
        cnt += 1
        continue

    if pos-1 >= 0 and move[pos-1] >= move[pos]+1:
        move[pos-1] = move[pos]+1
        q.append(pos-1)

    if pos+1 <= 100000 and move[pos+1] >= move[pos]+1:
        move[pos+1] = move[pos]+1
        q.append(pos+1)

    if pos*2 <= 100000 and move[pos*2] >= move[pos]+1:
        move[pos*2] = move[pos]+1
        q.append(pos*2)

print(minv)
print(cnt)