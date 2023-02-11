# Queue 구조를 이용해 구현
# 하나의 list(Queue)에서 값을 업데이트하고 뽑아쓰며 처리
# 회전 횟수(R)만큼 반복할 필요가 없어서 처리 시간 개선
# 인덱스 조절을 집중해서 해야 한다 - 코드 가독성은 떨어지는 느낌
import sys; input = sys.stdin.readline
sout = sys.stdout.write
N, M, R = map(int, input().split())
arr = [] # 원본
ret = [[0] * M for _ in range(N)]  # 결과 저장

for _ in range(N):
    arr.append(list(map(int, input().split())))

round = round = N//2 if N < M else M//2      # 회전 구간 개수 (index로 활용)
                                            
for r in range(round):                       # r = 회전 구간의 첫 인덱스 ([r, r])
    # -------------------------------------------------------------------------------
    #     회전 구간의 모든 원소들을 하나의 list에 담아 놓는 과정
    #     1  2  3  4
    #     5  6  7  8
    #     9 10 11 12
    #    13 14 15 16   일 때,  첫 line = [1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5]
    #                        다음 line = [6, 7, 11, 10]
    
    line = []                                # 회전 데이터를 다루는 Queue
    line += arr[r][r: M-r]                   # 회전 구간의 첫 행 값들 가져오기

    for row in range(r+1, N-r-1):            # 회전 구간의 우측 열의 값들 가져오기
        line.append(arr[row][M-r-1])

    line += reversed(arr[N-r-1][r: M-r])     # 회전 구간의 하단 행의 값들 가져오기

    
    for row in range(N-r-2, r, -1):          # 회전 구간의 좌측 열의 값들 가져오기
        line.append(arr[row][r])
    
    # ---------------------------------------------------------------------------------

    # 움직이는 회전 수가 라인의 원소 개수보다 많으면 N바퀴 원점 회전도 가능하므로 나머지 구함
    move = R % len(line)

    # ---------------------------------------------------------------------------------
    #    회전 횟수 R만큼의 결과를 line에 반영
    #    [6, 7, 11, 10]의 line에서 "R=3"이었다면
    #    [10, 6, 7, 11]로 재배치

    line = line[move:] + line[:move]         # 이동 수만큼 라인 재배치
    ret[r][r: M-r] = line[:M-r*2]            # 회전 구간의 첫 행 값 채우기
    line = line[M-r*2:]                      # 사용한 원소는 line에서 제거

    for row in range(r+1, N-r-1):            # 회전 구간의 우측 열 값 채우기
        ret[row][M-r-1] = line.pop(0)
    
    ret[N-r-1][r: M-r] = reversed(line[: M-r*2])   # 회전 구간의 하단 행 값 채우기
    line = line[M-r*2:]

   
    for row in range(N-r-2, r, -1):          # 회전 구간의 좌측 열 값 채우기
        ret[row][r] = line.pop(0)


# 출력
for i in range(N):
    for j in range(M):
        sout(str(ret[i][j])+' ')
    sout("\n")
    