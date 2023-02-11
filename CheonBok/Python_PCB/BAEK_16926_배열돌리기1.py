
# Delta 방식으로 방향 바꿔가며 값 업데이트
# 코드를 해석하는 것에 큰 무리가 없으나
# 값들을 하나씩 바꿔가는 과정에 의해 시간이 많이 필요  = 시간 초과
# pypy3에서는 통과. --> python에서 해결하고 싶으니 다른 방법 필요
import sys; input = sys.stdin.readline
sout = sys.stdout.write
N, M, R = map(int, input().split())
arr = []
dx = [0, 1, 0, -1]  # [우, 하, 좌, 상] 순서
dy = [1, 0, -1, 0]
for _ in range(N):
    arr.append(list(map(int, input().split())))

round = N//2 if N < M else M//2


for _ in range(R):             # 회전 횟수(R)만큼 반복 loop
    
    for r in range(round):     # 회전 구간별 처리 loop
        x = y = r
        tmp = arr[x][y]        # 시작 좌표의 값 저장
        flg = 0                # delta index

        while True:
            nx = x + dx[flg]
            ny = y + dy[flg]

            if (nx < r or nx >= N-r or ny < r or ny >= M-r):
                if flg == 3: break  # 회전 종료
                flg += 1; continue  # 회전 방향 변경
        

            if nx == r and ny == r:      # 회전 구간의 마지막 위치면 tmp 저장
                arr[x][y] = tmp
            else:                        # 회전 중 : 값 update
                arr[x][y] = arr[nx][ny]
            x = nx
            y = ny

# 출력
for i in range(N):
    for j in range(M):
        sout(f"{arr[i][j]}"+" ")
    sout("\n")