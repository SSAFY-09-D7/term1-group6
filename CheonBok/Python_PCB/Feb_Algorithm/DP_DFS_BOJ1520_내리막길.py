import sys; input = sys.stdin.readline
sys.setrecursionlimit(10**6)
M, N = map(int, input().split())
arr = []     # 원본 배열
shadow = []  # 경우의 수 배열
visited = [] # 방문 배열
for i in range(M):
    arr.append(list(map(int, input().split())))
    shadow.append([0 for _ in range(N)])
    visited.append([0 for _ in range(N)])

dx = [1, 0, 0, -1]  # 하동서북
dy = [0, 1, -1, 0]  

def find(row: int, col: int) -> int:
    # 도착지 들어오면 1부터 시작해서 경우의 수 쌓아나감
    if row == M-1 and col == N-1:
        shadow[row][col] = 1
        return 1
    

    for i in range(4):
        nx = row + dx[i]
        ny = col + dy[i]

        if 0<=nx<M and 0<=ny<N and arr[nx][ny] < arr[row][col]:
            if not visited[nx][ny]:
                visited[nx][ny] = 1
                shadow[row][col] += find(nx, ny)
            else:
                # 이전에 다른 경우에서 방문해 값을 가지고 있다면 가져와서 반영
                shadow[row][col] += shadow[nx][ny]
        
    # 현재 가지고 있는 경우의 수를 이전 노드에 더해준다. (경우의 수 누적)
    return shadow[row][col]


# row, col
find(0, 0)
print(shadow[0][0])

# for i in range(M):
#     print(*shadow[i])
   
'''
반례 테스트

4 4
20 19 18 17
10 9 8 16
100 100 7 15
100 100 6 14
1

1 2
1 1
0

2 18
98 91 89 80 76 74 65 58 50 49 44 32 26 23 17 15 14 8
95 91 83 82 70 67 65 55 54 50 41 27 22 20 14 10 7 3
211

'''