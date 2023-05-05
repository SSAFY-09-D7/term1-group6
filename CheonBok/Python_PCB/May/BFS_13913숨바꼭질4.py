from collections import deque
N,K=map(int,input().split())

# 초기화 값은 노드가 가능한 범위 (0~100000)에 벗어나는 값으로 명확하게 구분
dist=[-1]*100001   #해당 노드에 도착하기까지의 거리 값 저장
nodes=[-1]*100001  #해당 노드에 도착하기 이전의 노드 값 저장

dist[N]=0  # 첫 시작 노드의 시간은 0
q=deque()
q.append(N)
while q:
    pos= q.popleft()

    if pos==K:
        print(dist[pos])  # 도착하기 까지의 시간
        ret = []
        ret.append(K)
        # 연결 리스트에 저장된 도착지까지의 경로를 뽑아 저장
        for _ in range(dist[pos]):
            ret.append(nodes[K])
            K = nodes[K]

        # 출력은 역순으로 일어나야 한다. (경로를 역추적했기 때문)
        print(' '.join(map(str,ret[::-1])))
        break

    for nx in [(pos-1), (pos+1), (pos*2)]:
        if 0<= nx <= 100000 and dist[nx] == -1:
            dist[nx] = dist[pos]+1
            nodes[nx] = pos
            q.append(nx)
