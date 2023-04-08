# 1 <= x <= 1,000,000,000
total, wins = map(float, input().split())
rate = int(wins*100/total)  
start = 1; end = 1000000000
ret = -1

# start가 end를 넘을 때까지 탐색
while start <= end:
    needs = (start + end) // 2  #중앙값
    tmpRate = int((wins+needs) / (total+needs) * 100) #변경된 승률
    print(f"{rate=} {tmpRate=}")

    # 변경 승률이 이전 승률을 최소 1 넘어야만 한다.
    if tmpRate <= rate: start = needs+1
    else: 
        # 변경된 승률이 최초 승률보다 1~N까지 크다면 이곳에서 처리.
        # 값이 크다면 계속 여기 들어와 승률 차이가 떨어지게 되며 결국 out.
        end = needs-1  #탈출 조건을 위해 needs 그대로 사용 X
        ret = needs

print(ret)