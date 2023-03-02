'''
    N:행, M:열, D:사격 범위
    거리가 D 이하인 적 중 가장 가까운 적 - 거리가 같은 적이 여럿이라면 왼쪽의 적을 공격
    3명의 궁수를 배치할 수 있다. -> [열]개의 수 중 3개를 뽑는 조합
'''
import sys; input = sys.stdin.readline
import copy
N,M,D = map(int, input().split())
enemy = []
for r in range(N): # 적군 좌표 값 저장
    for c, type in enumerate(input().rstrip().split()):
        if type == '1': enemy.append((r,c))


# 디펜스 진행.
# 1. 궁수 배치 (N, idx):  N = 마지막 행 아래. idx = 열(column)
# 2. 적군이 있는 모든 좌표 값(enemy) 복사  --> enemycopy
# 3. 적군이 모두 없어질 때까지 (enemycopy에 값이 없을 때까지) 게임 진행
# 4-1. dellist: 해당 라운드에서 삭제될 적군 정보 (성에 도달한 적군 + 궁수에 의해 제거된 적군)
# 4-2. catchSet: 궁수가 적군을 잡았을 때 이곳에 잡은 적군에 대한 정보 저장
#      -> 값은 조건에 따라 값이 변경될 수 있다.
#         1) 잡은 적군 중 거리가 가장 짧은 적군으로 업데이트
#         2) 잡은 적군 중 거리가 같을 때, 더 작은 값의 column을 갖는 적군으로 업데이트
# 
# 5. 궁수가 잡은 적군의 좌표까지 dellist에 들어간다. (set이므로 중복 값은 제거된다.)
# 6. dellist에 있는 적군은 모두 삭제되며 모든 적군이 없어질 때까지 위를 반복한다.
def gameStart():
    arrowSet = []  # 게임 시작하는 궁수 배치
    for idx in arrows:
        arrowSet.append([N, idx])
    enemycopy = copy.deepcopy(enemy)
    catchlen = 0
    #print(f"{arrowSet=}")


    while enemycopy:
        #print(f"현재 enemy = {enemycopy}")
        dellist = set()
        catchSet = set()
        catch = [[] for _ in range(3)]

        for ax, arr in enumerate(arrowSet):
            for ex, ene in enumerate(enemycopy):
                if arr[0] - ene[0] <= 0: 
                    dellist.add(ex)
                    continue
                
                dist = (arr[0]-ene[0]) + abs(arr[1]-ene[1])  # 거리 계산
                if dist <= D: # 1차: 거리가 제한 범위 안에 있다.
                    if not catch[ax]: catch[ax] = (ex, ene[1], dist)
                    else:
                        # 2차: 거리가 더 짧은 경우의 적이라면
                        if catch[ax][2] > dist:
                            catch[ax] = (ex, ene[1], dist)
                            
                        # 3차: 거리가 같을 때, col값이 더 작은지 (왼쪽을 잡아야 하므로)
                        if catch[ax][2] == dist and catch[ax][1] > ene[1]:
                            catch[ax] = (ex, ene[1], dist)
        
            arr[0] -= 1 # 라운드마다 1씩 올림 (적군이 한 행 아래로 내려온 것과 같은 결과)
        #print(f"{catch=}")

        # 제거할 수 있는 적이 있으면 dellist에 반영
        for node in catch:
            if node: 
                dellist.add(node[0])
                catchSet.add(node[0])
        
        #print(f"{dellist=}")
        tmp = []
        for i in range(len(enemycopy)):
            if i not in dellist:
                tmp.append(enemycopy[i])
        enemycopy = tmp
        catchlen += len(catchSet)

    return catchlen

        
def comb(start: int, cnt: int):
    global ret
    if cnt == 3:
        total = gameStart()
        if ret < total: ret = total
        #print(arrows)
        return

    for d in range(start, M):
        arrows[cnt] = d
        comb(d+1, cnt+1)

arrows = [0] * 3
ret = 0
comb(0, 0)
print(ret)