import sys
import heapq
input = sys.stdin.readline
round = int(input())

for i in range(round):
    test = int(input())
    pQ = [] # 최대 힙
    mQ = [] # 최소 힙
    overdict = dict() # 중복 개수

    for j in range(test):
        command, value = input().rstrip().split()
    
        if command == 'I':
            # 중복된 값이라면 -> 힙에 안 넣고 개수만 증가 (중복 방지)
            if value in overdict and overdict[value] != 0:
                overdict[value] += 1
                continue

            # 힙에 값 넣기 (최대/최소 분리)
            if int(value) < 0:
                heapq.heappush(mQ, int(value))
            else:
                heapq.heappush(pQ, -int(value))
            
            # dictionary에 없는 경우이므로 1 기록
            overdict[value] = 1


        elif command == 'D':
            # 최소 힙(mQ) 우선 처리 (mQ가 없으면 pQ에서 처리)
            if value == '-1':
                if len(mQ) != 0:
                    if overdict[str(mQ[0])] > 1:
                        overdict[str(mQ[0])] -= 1
                    else:
                        v = heapq.heappop(mQ)
                        overdict[str(v)] = 0


                elif len(pQ) != 0:
                    if overdict[str(-max(pQ))] > 1:
                        overdict[str(-max(pQ))] -= 1
                    else:
                        overdict[str(-max(pQ))] = 0
                        pQ.remove(max(pQ))
            
            # 최대 힙(pQ) 우선 처리 (pQ가 없으면 mQ에서 처리)
            else:
                if len(pQ) != 0:
                    if overdict[str(-pQ[0])] > 1:
                        overdict[str(-pQ[0])] -= 1
                    else:
                        v = heapq.heappop(pQ)
                        overdict[str(-v)] = 0

                elif len(mQ) != 0:
                    if overdict[str(max(mQ))] > 1:
                        overdict[str(max(mQ))] -= 1
                    else:
                        overdict[str(max(mQ))] = 0
                        mQ.remove(max(mQ))

    # 2개의 힙에서 발생할 수 있는 경우에 대한 값 출력 if-elif-else                               
    if len(mQ) == 0 and len(pQ) == 0:
        print("EMPTY")
    elif len(mQ) > 0 and len(pQ) > 0:
        print(f"{-min(pQ)} {min(mQ)}")
    elif len(pQ) > 0:
        print(f"{-min(pQ)} {-max(pQ)}")
    else:
         print(f"{max(mQ)} {min(mQ)}")