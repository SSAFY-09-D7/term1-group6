from collections import deque
T = int(input())

#    idx: 접근할 코어
# length: 사용한 전선 길이
#   conn: 연결된 코어
def solve(idx, length, conn):
    global process
    
    if idx == process:
        global maxCore, minLen

        if conn > maxCore:  #코어 더 많으면 업데이트
            maxCore = conn
            minLen = length
        # 코어 같으면 전선 더 짧은 것으로
        elif conn == maxCore and minLen > length: minLen = length 
        return

    x, y = coreList[idx]  # 사용할 코어
   
    # 1. 현재 코어가 가장자리라면 (conn 증가 후 다음 탐색)
    if x in [0, N-1] or y in [0, N-1]:
        solve(idx+1, length, conn+1)

    else:
        # 2. 코어가 가장자리가 아니라면 (4방향 연결 모두 진행)
        # 2-1 상 방향
        if sum([arr[tr][y] for tr in range(x-1, -1, -1)]) == 0:
            for tr in range(x-1, -1, -1): arr[tr][y] = 1
            solve(idx+1, length+x, conn+1)
            for tr in range(x-1, -1, -1): arr[tr][y] = 0

        
        # 2-2 하 방향
        if sum([arr[tr][y] for tr in range(x+1, N)]) == 0:
            for tr in range(x+1, N): arr[tr][y] = 1
            solve(idx+1, length+(N-1-x), conn+1)
            for tr in range(x+1, N): arr[tr][y] = 0


        # 2-3 좌 방향
        if sum(arr[x][:y]) == 0:
            arr[x][:y] = [1]*y
            solve(idx+1, length+y, conn+1)
            arr[x][:y] = [0]*y
        

        # 2-4 우 방향
        if sum(arr[x][y+1:N]) == 0:
            arr[x][y+1:N] = [1]*(N-1-y)
            solve(idx+1, length+(N-1-y), conn+1)
            arr[x][y+1:N] = [0]*(N-1-y)


        # 3. 아무 것도 해당되지 않을 때 그냥 넘어가는 경우
        solve(idx+1, length, conn)


for t in range(1, T+1):
    N = int(input())
    arr = [[0 for _ in range(N)] for _ in range(N)]  # Frame
    coreList = deque()
    
    # process: 제시된 모든 코어 수
    # maxCore: 연결된 최대 코어 수
    #  minLen: 연결에 사용한 최소 전선
    process, maxCore, minLen = 0, 0, 0
    
    for row in range(N):
        for col, v in enumerate(list(map(int, input().split()))):
            if v == 1: 
                process += 1
                coreList.append([row, col])  # input the core position
            arr[row][col] = v
            
    #idx, length, conn
    solve(0, 0, 0)
    print(f"#{t} {minLen}")


'''
특이: 연결할 수 없는 코어가 존재할 가능성
1
7
1 1 1 1 1 1 1
1 0 0 1 0 0 1
0 0 0 0 0 0 1
1 1 0 0 0 0 1
1 0 0 0 0 0 1
0 0 1 0 0 1 1
1 1 1 0 1 1 1

out = 7
'''