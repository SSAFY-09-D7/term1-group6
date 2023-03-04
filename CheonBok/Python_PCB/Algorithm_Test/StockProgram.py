from math import inf
import copy
T = int(input())
def benifits(money, needs, M):
    if money < needs: # 최소 주식금액보다 작아졌으면 최대 이익 계산
        global total, getCount
        ssum = 0
        for c in range(size):
            if choices[c]: ssum += tmp[c] * choices[c][1]
        
        #print(f"얻게될 이익 = {ssum}")
        if total < ssum:
            total = ssum
            getCount = copy.deepcopy(tmp)
        return
    
    # 구매할 수 있는 상태라면 모든 경우를 계산
    for c in range(size):
        if choices[c] and money - items[c][M] >= 0:
            tmp[c] += 1
            benifits(money-items[c][M], needs, M)
            tmp[c] -= 1


for t in range(1, T+1):
    money, monI = map(int, input().split())  # 원금, 불입금
    size, month = map(int, input().split())  # 종목수, 개월
    items = []              # 모든 종목에 대한 개월별 가격
    getCount = [0] * size   # 종목별 최종 매수 개수를 담음
    moneytmp = money        # 마지막 계산에 필요한 원금

    for i in range(size): 
        items.append(list(map(int, input().split()))+ [0])

    for M in range(month+1):
        if M > 0: money += monI              # 1개월부터 불입금
        choices = [[] for _ in range(size)]  # 이득 종목 담음
    
        # Step1. 규칙. 가지고 있는 주식은 다음 달에 무조건 매도.
        for idx in range(size):
            if getCount[idx] != 0:
                money += getCount[idx] * items[idx][M]
                getCount[idx] = 0

        #print(f"정산된 금액 = {money}")
        # Step2. 종목별 다음 달과의 차익이 "양수"인 항목만 choices에 담음
        minvalue = inf;  cnt = 0
        for idx in range(size):
            if items[idx][M+1] - items[idx][M] > 0:
                if minvalue > items[idx][M]: minvalue = items[idx][M]
                choices[idx].extend([items[idx][M], items[idx][M+1] - items[idx][M]])
                cnt += 1 
        

        #Step3. 이득이 발생하는 종목들의 조합에서 최대 이익을 보장하는 경우를 채택
        #print(choices)  # 이득인 종목들만 저장됨
        if cnt > 1: # 1개 이상의 종목이 이득이면 경우의 수를 고려함.
            tmp =[0] * size
            total = 0
            benifits(money, minvalue, M)

            for idx in range(size):
                if getCount[idx] != 0: money -= getCount[idx] * items[idx][M]

        elif cnt == 1:  #종목이 1개만 이득이면
            for idx in range(size):
                if choices[idx]:
                    getCount[idx] = money // items[idx][M]
                    money -= getCount[idx] * items[idx][M]
        
        #print(f"최종 반영된 {getCount=}, 남은 금액 = {money}")
    
    #print(f"초기 금액 = {moneytmp}, 최종 금액 = {money}, 불입금 = {monI*M}")
    #print(f"최종  이득 = {money - (moneytmp+monI*M)}")
    print(f"#{t} {money - (moneytmp+monI*M)}")

'''
5 
300 60 
3 8
135 120 111 144 170 170 171 173 169 
156 150 144 144 144 150 150 150 147 
195 180 165 150 141 172 185 190 159 
400 0
5 8
180 180 180 150 120 150 180 180 180 
315 315 315 300 300 300 300 300 315 
219 282 255 255 255 219 219 219 219 
228 222 204 246 255 228 228 228 228 
120 150 120 120 120 120 120 120 120 
450 0
7 10
405 405 345 345 315 345 345 345 345 405 405 
210 210 210 240 240 210 210 210 210 210 210 
270 225 225 225 225 225 225 292 270 270 270 
120 135 135 135 135 120 120 120 120 120 120 
210 210 210 210 210 210 210 210 210 270 225 
180 180 180 180 180 180 180 180 180 180 210 
180 126 120 120 150 150 150 180 180 180 180 
500 0
5 12
470 470 510 663 861 1119 1187 1210 1230 1230 1260 1270 1280 
1490 1490 1160 1200 1200 1200 1200 1410 1650 1860 1860 1860 1860 
260 270 260 260 260 260 260 260 280 280 280 280 280 
1420 1660 1660 1260 1260 1260 1260 1270 1270 1270 1270 1270 1270 
800 900 980 1040 1140 1240 1340 1340 1440 1540 1640 1880 2000 
600 40
8 8
125 125 125 125 125 115 110 110 125 
512 616 616 616 616 616 616 616 616 
660 660 660 575 575 575 660 660 660 
345 345 448 540 540 515 515 450 395 
425 425 425 425 425 500 500 500 425 
100 100 100 100 100 110 110 100 100 
870 850 906 906 906 906 860 860 870
305 305 305 305 260 295 305 305 305
'''