# 다중 dijk
import sys; input = sys.stdin.readline
output = sys.stdout.write
from collections import defaultdict
import heapq
n,m=map(int, input().split())
#연결 리스트
linked = defaultdict(list)

#출력에 사용할 그래프 (경유하는 첫 노드 정보)
graph = [[[1e8, i] for i in range(n+1)] for _ in range(n+1)]

#입력
for _ in range(m):
    s, e, d = map(int, input().split())
    linked[s].append((e, d))
    linked[e].append((s, d))

for start in range(1, n+1):
    v = [False] * (n+1)
    q = []
    # 시작 경로에서는 인접 노드를 무조건 방문하므로 그 노드를 모(母)로 두는 처리
    for next, w in linked[start]:
        graph[start][next] = [w, next]  #도착하기 까지의 거리, 첫 방문한 노드
        heapq.heappush(q, [w, next])
    v[start] = True

    while q:
        dist, node = heapq.heappop(q)
        for next, w in linked[node]:
            if not v[next] and graph[start][next][0] > dist + w:
                graph[start][next] = [dist+w, graph[start][node][1]]
                heapq.heappush(q, [dist+w, next])
        v[node] = True

#출력
for row in range(1, n+1):
    for col in range(1, n+1):
        if row == col: output("- ")
        else:
            output(f"{graph[row][col][1]}" + " ")
    output("\n")

