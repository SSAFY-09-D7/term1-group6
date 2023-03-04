import sys; input = sys.stdin.readline
import copy
delta = [(0,0), (-1,0), (1,0), (0,1), (0,-1)]
R, C, M = map(int, input().split())  # 격자판 크기(RxC), 상어 수 M
arr = [[list() for _ in range(C+1)] for _ in range(R+1)]
sharks = []

for _ in range(M):
    r, c, s, d, z = map(int, input().split())
    arr[r][c].append([r,c,s,d,z])  # 지도에 상어 배치
    sharks.append([r,c,s,d,z])  # 상어 정보 저장

if M == 0:
    print("0"); sys.exit(0)

step = 1 #낚시왕의 위치
retW = 0 #출력 크기
while (step != C+1):
    # Step1. 상어 잡기 (변경점: 열 배열 --> 그냥 해당 열의 행 탐색)
    for row in range(1,R+1):
        if arr[row][step]:
            target = arr[row][step][0]
            retW += target[4]
            sharks.pop(sharks.index(target))
            break
    
    # Step2. 상어 이동 (변경점: 하나의 arr에 처리 --> 상어가 이동한 새로운 arr로 복제)
    # 새로운 배열을 만들어 복사가 빠른지... 인덱스 찾아서 상어 제거하는 게 빠른지...
    tmpMap = [[list() for _ in range(C+1)] for _ in range(R+1)]
    for _ in range(len(sharks)):
        S = sharks.pop(0)
        rr, cc, ss, dd, zz = S

        if dd in [1, 2]: ss %= (R-1)*2
        else:            ss %= (C-1)*2

        while (ss > 0):
            if   dd == 1 and ss <= rr-1: rr -= ss; break
            elif dd == 2 and ss <= R-rr: rr += ss; break
            elif dd == 3 and ss <= C-cc: cc += ss; break
            elif dd == 4 and ss <= cc-1: cc -= ss; break
            
            rr += delta[dd][0]
            cc += delta[dd][1]

            if 1<=rr<=R and 1<=cc<=C: ss -= 1
            else:
                rr -= delta[dd][0]
                cc -= delta[dd][1]
                if   dd == 1: dd=2
                elif dd == 2: dd=1
                elif dd == 3: dd=4
                elif dd == 4: dd=3
        
        #변경: 옮긴 상어를 새로운 배열에 담기
        tmpMap[rr][cc].append([rr, cc, S[2], dd, S[4]])
    
    #변경: 옮긴 상어 배열로 업데이트
    arr = copy.deepcopy(tmpMap)

    # Step3. 겹치는 상어는 사이즈 큰 값으로 통일
    # 변경: 상어 리스트에서 인덱스 탐색 후 삭제하지 말고
    #      여기에서 찾은 상어들로 새 리스트를 구성
    sharks.clear()
    for row in range(1, R+1):
        for col in range(1, C+1):
            if len(arr[row][col]) > 1:
                arr[row][col].sort(key=lambda x: -x[4])
                arr[row][col] = [arr[row][col][0]]
                sharks.append(arr[row][col][0])
            elif len(arr[row][col]) == 1:
                sharks.append(arr[row][col][0])

    step += 1
print(retW)