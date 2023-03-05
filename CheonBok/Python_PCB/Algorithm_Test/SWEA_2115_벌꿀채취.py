'''
1. 2명의 일꾼  ->  가로로만 연결 가능한 M 크기의 벌통을 각각 사용
2. 최대 담을 수 있는 벌꿀양 C  ->  M 크기로 연결했다고 해도 전부 사용하지 못하고 C 크기 안으로만 담을 수 있다.
3. 계산은 꿀을 담은 cell의 값**2의 합계

★ 2M = N이면 한 행 전체의 합계 계산 가능
★ 2M > N이면 한 행에는 한 일꿀만 배치해서 경우를 계산해야 함. 
★ 2M < N이면 가능한 구간합을 모두 계산해야 함.
맵 크기 = 최대 10x10 = 100
선택 벌통 = 최대 5
'''
T = int(input())
def combMax(Arow:int, Acol: int, mincol:int,  cnt:int, combmap: list):
    if cnt == M:
        sumb = 0  # 선택한 벌통의 수익
        sumw = 0  # 선택한 벌통의 꿀 양
        for comb in combmap:
            if comb[1]: 
                sumb += powMap[Arow][comb[0]]-powMap[Arow][comb[0]-1]
                sumw += honeyMap[Arow][comb[0]]

                if sumw > C: return 0 # 선택한 벌통의 꿀의 양이 제한을 넘으면 채택 X
        return sumb

    tmp = 0
    # 정해진 범위에서 벌통을 선택하는 조합
    for col in range(Acol, mincol-1, -1):
        combmap[cnt] = [col, True]
        avalue = combMax(Arow, col-1, mincol, cnt+1, combmap)
        combmap[cnt] = [col, False]
        bvalue = combMax(Arow, col-1, mincol, cnt+1, combmap)
    
        tmp = max(tmp, avalue, bvalue) # 벌통의 수익들 중 가장 이득인 것을 채택
    
    return tmp # 채택한 최종 이득을 반환


def Benifit(worker: list) -> int:
    Arow, Acol = worker
    #print(f"worker = {worker}")
    Amax = 0

    # 모든 벌통을 채취할 수 없다면 (조합 구성 후 계산)
    if sumMap[Arow][Acol+(M-1)] - sumMap[Arow][Acol-1] > C: 
        combmap = [[] for _ in range(M)]
        Amax = combMax(Arow, Acol+(M-1), Acol, 0, combmap)

    # 모든 벌통에서 채취 가능 -> 즉시 계산
    elif sumMap[Arow][Acol+(M-1)] - sumMap[Arow][Acol-1] <= C:
        if Amax < sumMap[Arow][Acol+(M-1)] - sumMap[Arow][Acol-1]:
            Amax = powMap[Arow][Acol+(M-1)] - powMap[Arow][Acol-1]

    return Amax


# 양봉A가 선택된 상태에서, 양봉B가 선택할 수 있는 모든 경우를 선택하고 그 이득을 계산
def Gathering(maxA_v: int):
    global ret
    for row in range(N):
        for col in range(1, (N-M+1)+1):
            if sum(usedMap[row][col:col+M]) == 0:
                workerB = [row, col]
                #print(workerB, sum(usedMap[row][col:col+M]))
                maxB_v = Benifit(workerB)
                
                #print(f"양봉A ={maxA_v}, 양봉B = {maxB_v}")
                if ret < maxA_v + maxB_v:
                    ret = maxA_v + maxB_v



for t in range(1, T+1):
    ret = 0
    N, M, C = map(int, input().split())
    honeyMap = [[0 for _ in range(N+1)] for _ in range(N)]  #원본 
    sumMap   = [[0 for _ in range(N+1)] for _ in range(N)]  #벌꿀합
    powMap   = [[0 for _ in range(N+1)] for _ in range(N)]  #제곱합
    usedMap  = [[0 for _ in range(N+1)] for _ in range(N)]  #사용구간
    for row in range(N):
        for col, v in enumerate(list(map(int, input().split()))):
            honeyMap[row][col+1] = v
            sumMap[row][col+1] = sumMap[row][col] + v
            powMap[row][col+1] = powMap[row][col] + v**2
    

    # 한 행에서 들어갈 수 있는 경우: N=4, M=2면 N=3에서 마지막 탐색 가능 (N-M+1)
    for row in range(N):
        # 여기에선 양봉자A의 가능한 모든 경우를 담는다.
        # 이후 Method에서 양봉자 B의 가능만 모든 경우를 담는다.
        for col in range(1, (N-M+1)+1):
            usedMap[row][col:col+M] = [1] * M
            maxA_v = Benifit([row, col]) # 양봉A의 최대 이익
            
            # for r in range(N):
            #     print(*usedMap[r])
            # print()

            Gathering(maxA_v)
            usedMap[row][col:col+M] = [0] * M

    print(f"#{t} {ret}")



'''
    for r in range(N):
        print(*honeyMap[r])
    print()

    for r in range(N):
        print(*sumMap[r])
    print()

    for r in range(N):
        print(*powMap[r])
    print()

'''