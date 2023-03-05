import sys; input = sys.stdin.readline
fishLevel = [0] * 7   # The number of each level fishes
shark = 2             # Shark Level
needs = shark         # need fishes for level up
position = []         # Last Position of Shark
delta = [(-1,0), (0,-1), (1,0), (0,1)]
N = int(input())
arr = [[0 for _ in range(N)] for _ in range(N)]
for r in range(N):
    for c, txt in enumerate(list(map(int, input().split()))):
        if txt == 9: position = [r, c]; arr[r][c] = 0; continue
        if txt in [1,2,3,4,5,6]: fishLevel[txt] += 1
        arr[r][c] = txt


def eatFish(row: int, col: int):
    used = [[False for _ in range(N)] for _ in range(N)]  # visited
    candidate = []  # Edible Fishes list
    queue = []      # Route for BFS
    queue.append([0, row, col])
    flg = False     # True: Can eat,  False: don't eat
    dist = 0        # distance of edible fish

    # p[0]:distance, p[1]:row, p[2]:col
    while (queue):
        p = queue.pop(0)
        if used[p[1]][p[2]]: continue
        used[p[1]][p[2]] = True

        # condition: 1.there is a fish   2. Shark can eat  
        if arr[p[1]][p[2]] != 0 and arr[p[1]][p[2]] < shark :
            candidate.append(p)
            continue

        for d in range(4):
            nx = p[1] + delta[d][0]
            ny = p[2] + delta[d][1]

            if 0<=nx<N and 0<=ny<N and arr[nx][ny] <= shark and not used[nx][ny]:
                queue.append([p[0]+1, nx, ny])  

    # Selecting a fish that is satisfied the condition of the question.
    if candidate:
        # sort: ordering -> distance > row(up first) > column(left first)
        candidate.sort(key = lambda x: [x[0], x[1], x[2]])   
        p = candidate.pop(0)

        fishLevel[arr[p[1]][p[2]]] -= 1
        arr[p[1]][p[2]] = 0
        position[0] = p[1]
        position[1] = p[2]
        dist = p[0]
        flg = True

    return flg, dist

ret = 0
while True:
    flg = False
    if shark < 8:
        for n in range(1, shark):
            if fishLevel[n] != 0: flg = True; break

        if not flg: break         # 먹을 수 있는 물고기 없으면 out

    if sum(fishLevel) == 0: break # 더 이상 물고기가 없다면 out

    rndflg, dist = eatFish(position[0], position[1])
    if not rndflg: break  # 먹을 수 있는 물고기는 있는데, 갈 수 없는 위치라면 못 먹음
    ret += dist
    
    # print()
    # for r in range(N):
    #     print(*arr[r])
    # print(f"마지막 위치 = {position}, 현재까지 이동 거리 = {ret}, 최근 더한 거리 = {dist}")

    needs -= 1
    if needs == 0: 
        shark += 1
        needs = shark

print(ret)