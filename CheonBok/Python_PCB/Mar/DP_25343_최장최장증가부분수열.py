import sys; input = sys.stdin.readline
N = int(input())
Frame = [[r for r in map(int, input().split())] for _ in range(N)]
dpMap = [[1 for _ in range(N)] for _ in range(N)]

# 시간 초과 극복 -> 가장 단거리로 갈 수 있는 경로만 탐색해야 한다.
# 기준 좌표(tr,tc)에서 행과 열을 넘어가는 범위(tr+1과 tc+1)에 있는 노드는 최단거리가 될 수 없다.
# 최단거리의 구성원이 되는 노드의 값을 전부 확인하며 수열의 구성원이 되는 경우에는 가질 수 있는 최댓값으로 반영한다.
def check(tr, tc):
    for subr in range(tr+1):
        for subc in range(tc+1):
            if Frame[tr][tc] > Frame[subr][subc]:
                dpMap[subr][subc] = max(dpMap[subr][subc], dpMap[tr][tc] + 1)


# 도착 노드(n-1, n-1)부터 시작해 하나씩 기준점으로 잡는다.
for tr in range(N-1, -1, -1):
    for tc in range(N-1, -1, -1):
        check(tr, tc)


# dp Table에서 가장 큰 값을 채택한다.
maxl = 0
for r in range(N):
    maxl = max(max(dpMap[r]), maxl)

print(maxl)