from collections import deque
from math import inf
T = int(input())
delta = [(-1,0), (1,0), (0,-1), (0,1)]
def check():
    global ret
    while stk:
        #0:row, 1:col
        node = stk.pop()

        for x, y in delta:
            nx = node[0] + x
            ny = node[1] + y


            if 1 <= nx <= N and 1<=ny<=N:
                # Check the cost to move the next.
                if arr[node[0]][node[1]] == arr[nx][ny]: cost = 1
                elif arr[node[0]][node[1]] < arr[nx][ny]: 
                    cost = (arr[nx][ny] - arr[node[0]][node[1]]) * 2
                else:
                    cost = 0
                
                # Choice minimum cost to move and add to stk
                if costMap[nx][ny] > costMap[node[0]][node[1]] + cost:
                    costMap[nx][ny] = costMap[node[0]][node[1]] + cost
                    stk.append([nx,ny])

# if __name__ == "__main__":
for t in range(1, T+1):
    N = int(input())
    arr = [[0] * (N+1)] # MainFrame
    costMap = [[inf for _ in range(N+1)] for _ in range(N+1)] # InfoFrame
    costMap[1][1] = 0

    for _ in range(N): arr.append([0]+ list(map(int, input().split())))
    
    stk = deque()
    stk.append([1,1])  # row, col
    check()

    # for r in range(1, N+1):
    #     print(*costMap[r][1:])
    # print()
    print(costMap[N][N])
    

'''
3 
4 
9 5 7 9
8 4 2 5
7 6 5 4
8 8 9 5
6
1 1 1 1 1 1
9 9 9 9 9 1
9 9 1 1 1 1
9 9 1 9 9 9
9 9 1 9 9 9
9 9 1 1 1 1
10
4 0 8 1 6 9 6 8 6 2
2 7 7 7 9 9 7 2 9 8
5 2 5 0 0 6 4 5 0 5
0 0 3 9 1 8 9 2 8 3
0 0 5 5 3 4 1 2 4 4
9 5 4 4 0 5 7 0 6 0
3 2 8 2 3 4 7 8 3 5
0 2 1 5 0 2 6 1 9 2
5 9 5 5 0 1 6 4 2 5
4 2 2 6 1 8 2 8 5 4
'''