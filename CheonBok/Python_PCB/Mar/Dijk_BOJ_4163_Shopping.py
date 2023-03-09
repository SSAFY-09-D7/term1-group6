import sys; input = sys.stdin.readline
from collections import defaultdict
from itertools import permutations
from math import inf
import copy, heapq
T = int(input())

def checkDist(node: int, roads: list) -> list:
    if node != 0:
        roads.remove(node)
        roads.append(0)
    
    dist = [inf] * N
    visited = [False] * N
    q = [[0, node]]
    while q:
        W, n = heapq.heappop(q)

        if n in roads: roads.remove(n)
        if not roads: return dist

        visited[n] = True
        for nextW, nn in nodes[n]:
            if not visited[nn]:
                if dist[nn] > nextW + W:
                    dist[nn] = nextW + W
                    heapq.heappush(q, [dist[nn], nn])


def shopping(rndset: list) -> int:
    # 시작(0) 정점에서 출발
    start = 0; dist = 0
    for node in rndset:
        dist += nodesDist[start][node]
        start = node

    return dist

#if __name__ == '__main__':
for _ in range(T):
    waypoint = []  
    nodesDist = defaultdict(list)
    N, M = map(int, input().split())

    nodes = [[] for _ in range(N)]  #Graph
 
    for _ in range(M):
        x, y, d = map(int, input().split())
        nodes[x].append([d, y])
        nodes[y].append([d, x])
    
    for _ in range(int(input())):
        waypoint.append(int(input()))  #stores list to visit


    # 각 정점에서 시작하는 최단 거리를 기록
    for node in [0] + waypoint:
        nodesDist[node] = checkDist(node, copy.deepcopy(waypoint))


    # 모든 경유지를 돌고서 집으로 다시 들어가는 순열 생성
    # 0 -> 2 -> 3 -> 1 -> 0
    # 0 -> 3 -> 2 -> 1 -> 0  등의 경우들이 생성
    ret = inf
    for rndSet in permutations(waypoint, len(waypoint)):
        total = shopping(list(rndSet)+[0])
        if ret > total: ret = total
    
    print(ret)