import sys; input = sys.stdin.readline
R, C, M = map(int, input().split())  # 격자판 크기(RxC), 상어 수 M
arr = [[list() for _ in range(C+1)] for _ in range(R+1)]
sharks = []
for _ in range(M):
    r, c, s, d, z = map(int, input().split())
    arr[r][c].append([r,c,s,d,z])  # 지도에 상어 배치
    sharks.append([r,c,s,d,z])  # 상어 정보 저장

if M == 0:
    print("0"); sys.exit(0)

# for i in range(1, R+1):
#     print(*arr[i])

dx = (0, -1 ,1, 0, 0)
dy = (0, 0, 0, 1, -1)

step = 1 #낚시왕의 위치
retW = 0 #출력 크기
while (step != C+1):
    #print(f"현재 낚시왕 위치 {step=} ")
    coline = list(arr[r][step] for r in range(1,R+1)) # 각 열마다 상어 위치 확인
    #print(coline)
    #Step1. 같은 열에 있는 가장 가까운 상어 잡기
    for shark in coline:
        if shark:
            r,c,s,d,z = shark[0]
            #print(sharks.index([r,c,s,d,z]))
            arr[r][c] = []
            retW += z
            sharks.pop(sharks.index([r,c,s,d,z]))  # 상어 1마리 잡으면 바로 나옴
            break

    # 1:상, 2:하, 3:우, 4:좌
    # r,c:좌표, s:속력, d:이동 방향, z:크기
    #Step2. 상어 이동
    #print(f"{sharks=}")
    #print()
    for _ in range(len(sharks)):
        S = sharks.pop(0)
        rr, cc, ss, dd, zz = S
        arr[rr][cc].pop(arr[rr][cc].index(S))  # 상어 재배치 해야 하니까 제거함

        if dd in [1, 2]: ss %= (R-1)*2
        else: ss %= (C-1)*2

        #print(f"꺼낸 값 = {S}, {dd}, {ss}")

        while ss > 0:
            #print(rr, cc, ss, dd, zz)
            if dd == 3 and ss <= C-cc: cc += ss; break
            elif dd == 4 and ss <= cc-1: cc -= ss; break
            elif dd == 1 and ss <= rr-1: rr -= ss; break
            elif dd == 2 and ss <= R-rr: rr += ss; break

            rr += dx[dd]
            cc += dy[dd]
            #print(f"방향 진행 = {rr=}, {cc=}, {ss=}  {dd=}")

            if 1 <= rr <= R and 1 <= cc <= C: ss -= 1
            else: # 범위를 벗어나 방향을 바꿔야 하는 경우
                rr -= dx[dd]
                cc -= dy[dd]
                if dd == 1: dd=2
                elif dd == 2: dd=1
                elif dd == 3: dd=4
                elif dd == 4: dd=3
                #print(f"방향 변경 = {rr=}, {cc=}, {ss=}  {dd=}")
        
        # 옮긴 상어를 배열에 반영
        arr[rr][cc].append([rr, cc, S[2], dd, S[4]])
        sharks.append([rr, cc, S[2], dd, S[4]])

    #print("--------------------------------------------") 
    #겹치는 상어가 있으면 사이즈가 가장 큰 놈으로 변경
    for r in range(1, R+1):
        for c in range(1, C+1):
            #print(arr[r][c])
            if len(arr[r][c]) > 1:
                arr[r][c].sort(key= lambda x: -x[4])
                #print(arr[r][c])
                for idx in range(1, len(arr[r][c])):
                    #print(f"삭제할 값 = {arr[r][c][idx]}")
                    sharks.pop(sharks.index(arr[r][c][idx]))
                arr[r][c] = [arr[r][c][0]]
    
    step += 1  # 낚시꾼 한 칸 이동
    # print("--------------------------------------------")       
    # print(sharks)
    # print()

    # for i in range(1, R+1):
    #     print(*arr[i])
    # print()
print(retW)


'''
1 6 6
1 1 0 4 1
1 2 1 4 2
1 3 2 4 3
1 4 3 4 4
1 5 4 4 5
1 6 5 4 6
= 1

50 50 19
4 9 21 1 999
50 50 4 4 500
50 49 222 3 200
50 48 12 2 45
50 47 36 1 900
2 3 20 3 444
4 8 4 2 49
3 3 40 4 50
2 2 460 2 4444
48 23 500 3 12
1 1 200 1 123
44 44 123 3 125
44 40 222 3 17
40 44 333 3 57
18 40 1 1 4
3 10 50 2 406
1 36 177 4 50
1 46 120 4 7
1 50 28 4 54
= 7718

2 3 3
1 3 2 4 1
1 2 1 4 2
1 1 2 3 4
=4

10 10 2
1 9 8 2 1
5 10 7 4 2
=0

2 5 1
1 5 1 3 1
= 1

2 2 1
1 2 5 1 4
'''