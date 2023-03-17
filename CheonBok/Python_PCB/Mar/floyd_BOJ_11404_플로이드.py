import sys; input = sys.stdin.readline
output = sys.stdout.write
N = int(input())
# 가능한 최댓값을 초기화 값으로 선택. (최단 거리 업데이트 목적)
graph = [[1e9 for _ in range(N+1)] for _ in range(N+1)]

# 자기 자신은 0으로 거리 갱신
for n in range(1, N+1): #init self
    graph[n][n] = 0

# 직접 갈 수 있는 거리 정보 중 가장 짧은 값으로 갱신
for _ in range(int(input())): #init target
    a, b, c = map(int,input().split())
    if graph[a][b] > c: graph[a][b] = c
    

# 직접 갈 수 있는 정보는 갱신되어 있으므로,
# 특정 노드를 경유해서 도달할 수 있을 때, 그 거리가 더 짧은지 비교 후 갱신.
for mid in range(1, N+1): #dist using mid
    for start in range(1, N+1): #dist only a to b
        for end in range(1, N+1):
            graph[start][end] = min(graph[start][end],  graph[start][mid] + graph[mid][end])

# 최종 출력
for r in range(1, N+1):
    for c in range(1, N+1):
        if graph[r][c] == 1e9: output(f"0 ")
        else: output(f"{graph[r][c]} ")
    output(f"\n")