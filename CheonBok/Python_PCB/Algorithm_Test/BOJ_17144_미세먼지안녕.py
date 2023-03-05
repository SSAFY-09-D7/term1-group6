'''
공기 청정기는 1열에 있으며 2행을 차지한다.
좌표에 있는 값은 미세먼지의 양

Step1
미세먼지가 있는 모든 칸에서 확산이 일어남 (4방향)
확산되는 양은 값/5 -> 소수점 버리기
확산 후 좌표에 남은 미세먼지양 = 값 - (값/5)*확산된 방향 개수

Step2 바람 불기
'''
import sys; input = sys.stdin.readline
import copy
delta = [(-1,0), (0,-1), (1,0), (0,1)]
R, C, T = map(int, input().split())    #R:행, C:열, T:초
Room = []
for _ in range(R):
    Room.append(list(map(int, input().split())))

# 미세먼지 확산
def Spread():
    for r in range(R):
        for c in range(C):
            if Room[r][c] not in [-1, 0]:
                v = Room[r][c]//5
                cnt = 0

                for d in delta:
                    nx = r + d[0]
                    ny = c + d[1]
                
                    if 0<=nx<R and 0<=ny<C and Room[nx][ny] != -1:
                        cnt += 1
                        dustMap[nx][ny] += v
            
                dustMap[r][c] += Room[r][c] - v*cnt
                # print()
                # for rnd in range(R):
                #     print(*dustMap[rnd])
                # print("-------------------------------------")

def wind():
    queue = []
    #좌측 처리
    for r in range(air[0], -1, -1): queue.append(dustMap[r][0])
    #상단 처리
    queue.extend(dustMap[0][1:C])
    #우측 처리
    for r in range(1,air[0]+1): queue.append(dustMap[r][C-1])
    #하단 처리
    queue.extend(reversed(dustMap[air[0]][1:C-1]))
    # 1칸 이동
    queue.append(queue.pop(0))
  #-----------------------------------------------------------------
    # 이동한 값 넣기
    for r in range(air[0], -1, -1): dustMap[r][0] = queue.pop(0)
    dustMap[0][1:C] = queue[:C-1]
    queue = queue[C-1:]
    for r in range(1, air[0]+1): dustMap[r][C-1] = queue.pop(0)
    dustMap[air[0]][1:C-1] = reversed(queue[:])

    # print()
    # for rnd in range(R):
    #     print(*dustMap[rnd])
    # print()
  #------------------------------------------------------------------
    # 아래쪽 공기 청정기도 같은 방식으로 처리
    queue = []
    for r in range(air[1], R): queue.append(dustMap[r][0])
    queue.extend(dustMap[R-1][1:])
    for r in range(R-2, air[1]-1, -1): queue.append(dustMap[r][C-1])
    queue.extend(reversed(dustMap[air[1]][1:C-1]))
    queue.append(queue.pop(0))


    for r in range(air[1], R): dustMap[r][0] = queue.pop(0)
    dustMap[R-1][1:C] = queue[:C-1]
    queue = queue[C-1:]
    for r in range(R-2, air[1]-1, -1): dustMap[r][C-1] = queue.pop(0)
    dustMap[air[1]][1:C-1] = reversed(queue[:])

    # print()
    # for rnd in range(R):
    #     print(*dustMap[rnd])
    # print()

    #filtering out the dust with the air filter
    dustMap[air[0]][0] = 0 
    dustMap[air[1]][0] = 0


air = [row for row in range(R) if Room[row][0] == -1] #공청 위치
for _ in range(T):
    dustMap = [[0 for _ in range(C)] for _ in range(R)]
    Spread()
    wind()
    
    Room = copy.deepcopy(dustMap)
    Room[air[0]][0] = -1
    Room[air[1]][0] = -1

total = 0
for r in range(R):
    total += sum(dustMap[r])

print(total)


'''
6 6 1
1 2 3 4 5 6
6 5 4 3 2 1
-1 2 3 4 5 6
-1 6 5 4 3 2
1 2 3 4 5 6
6 5 4 3 2 1
1 2 3 4 5 6


7 6 1
1 2 3 4 5 6
6 5 4 3 2 1
-1 2 3 4 5 6
-1 6 5 4 3 2
1 2 3 4 5 6
6 5 4 3 2 1
1 2 3 4 5 6
'''