import sys; input = sys.stdin.readline
import copy
from collections import deque
from itertools import combinations
# -------------------------------------------------------------------------------------------
#                              Input Part
#   people: The list of population used for calculation.
#   nodes : Adjacency list
#  sizemap: sum of population of two groups(A, B).  dont use index 0.

N = int(input())
people = [0] + list(map(int, input().split()))  
nodes = [[] for _ in range(N+1)]
for n in range(1, N+1):
    nodes[n] = list(map(int, input().split()))[1:]

sizemap = [0, 0, 0]
# -------------------------------------------------------------------------------------------
'''
    Step1. 
        dept: 각 구역의 최상위 소속: Like parents node list.
        구역끼리 연결된다면 하나의 최상위 소속에 종속된다.        -> [0, 1, 1, 1, 1]
        연결되지 않는 구역이 존재한다면 N개의 소속 번호가 된다.   -> [0, 1, 1, 3, 3, 5]

        구분된 소속 번호의 종류가 3개 이상이면 2개의 구역으로 구분할 수 없다 -> -1 출력 + 종료

    Step2.
        Step1 과정에서 나온 소속 번호가 2개 나왔다면 이는 무조건 2개의 정해진 구역으로 나뉜다는 의미.
        각각 연결된 두 구역의 인구수 합계를 구한 뒤, 이를 뺀 결과를 출력한다.
'''
# 소속을 확인한다.
def checkGroup(idx: int):
    stk = copy.deepcopy(nodes[idx])
    visit = [False] * (N+1)
    visit[idx] = True
    t = people[idx]
    while stk:
        target = stk.pop()
        if not visit[target]:
            visit[target] = True
            dept[target] = idx
            t += people[target]
            stk.extend(nodes[target])
    return t
        
# 1. 소속을 확인한다. (최대 그룹 수 2까지 허용하며 3이상이 나오면 out(-1) 처리)
dept = [i for i in range(N+1)]
cnt = 0
for idx in range(1, N+1):
    if dept[idx] == idx:
        cnt += 1
        
        if cnt == 3:  # 그룹이 3개 이상이면 구성할 수 없는 상태
            print(-1)
            exit(0)
        
        sizemap[cnt] = checkGroup(idx)

# Step2
if cnt == 2: 
    print(abs(sizemap[2] - sizemap[1]))
    exit(0)
#print(nodes)

# -----------------------------------------------------------------------------------------

# 여기까지 왔다는 것 = 단 1개의 그룹이며 구성할 수 있는 모든 경우에 대해 계산해야 한다.
'''
    sizemap[1]: 이 구역은 단 1개의 최상위 구역을 가진다. -> 모든 구역이 연결되어 있다.
                따라서 1번 그룹에 모든 구역의 인구수 합계가 저장되어 있다.
    
    combNode  : 연결된 노드(구역)의 번호들을 담아 둔 리스트. (부분 집합 구성에 사용)

'''
ret = sizemap[1]  # ret = 만들 수 있는 가장 큰 차이로 초기화
combNode = [i for i in range(1, N+1) if len(nodes[i]) > 0]

# 구역을 구성할 수 있는 nodeset인지 검사
def check(nodeset) -> bool:
    stk = deque([nodeset[0]])
    visited = [False] * (N+1)
    cnt = 0
    while stk:
        node = stk.pop()

        # 구역에 포함될 수 있는 조건을 충족하는 노드
        if node in nodeset and not visited[node]:
            cnt += 1
            visited[node] = True
            stk.extend(nodes[node])

        # 구역을 구성하는 개수를 모두 채웠으면 탈출
        if cnt == len(nodeset): break
    
    # 방문 배열을 검사해서 구역에 필요한 노드가 사용 되었는지 확인 
    for idx in nodeset:
        if not visited[idx]: return False
    
    return True


# 모든 부분 집합 Set을 구성하고, 각각(teamA, teamB)이 실제 조건에 맞는 구역을 만들 수 있는지 검사.
# 구역을 만들 수 있음이 보장된다면 차이 계산 후 업데이트
def checkAble():
    ret = 2**31
    for i in range(1, N//2+1):
        for teamA in combinations(combNode, i):
            teamB = [i for i in range(1, N+1) if i not in teamA]
           
            if check(teamA) and check(teamB):
                totalA = sum([people[i] for i in teamA])
                totalB = sum([people[i] for i in teamB])
                
                ret = min(ret, abs(totalA - totalB))
    
    return ret
                
ret = checkAble()
print(ret)


'''
Test Case (반례용)
5
5 2 3 4 1
1 2
4 1 3 5 4
1 2
1 2
1 2
out: 5

3
1 2 1
1 2
2 1 3
1 2
out: 2


10
1 2 3 4 5 6 7 8 9 10
1 2
1 3
1 4
1 5
1 6
1 7
1 8
1 9
1 10
1 1
out: 1

2
3 4
0
0
out: 1
'''