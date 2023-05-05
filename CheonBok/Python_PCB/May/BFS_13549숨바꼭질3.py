from collections import deque
N,K=map(int,input().split())
dist=[100001]*100001
dist[N]=0
q=deque()
q.append(N)

while q:
    pos = q.popleft()

    if pos==K:
        print(dist[pos])
        break

    if 0<= pos-1 and dist[pos-1] > dist[pos]+1:
        dist[pos-1] = dist[pos]+1
        q.append(pos-1)

    if pos+1 <= 100000 and dist[pos+1] > dist[pos]+1:
        dist[pos+1] = dist[pos]+1
        q.append(pos+1)


    if pos*2 <= 100000 and dist[pos*2] >= dist[pos]:
        dist[pos*2] = dist[pos]
        q.append(pos*2)
