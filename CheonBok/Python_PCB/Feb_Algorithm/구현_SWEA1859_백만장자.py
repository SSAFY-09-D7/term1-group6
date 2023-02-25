test = int(input())
for rnd in range(1,test+1):
    day = int(input())
    arr = list(map(int, input().split()))
    maxvalue = arr[-1]

    # 마지막 인덱스부터 왼쪽으로 이동
    # 현재 값이 이전 값보다 큰 상태면 차익 만듦.
    # 새로운 큰 값이 등장한다면 갱신
    total = 0
    for i in range(day-2, -1, -1):
        if arr[i] > maxvalue: maxvalue = arr[i]
        else: total += maxvalue - arr[i]
    
    print(f"#{rnd} {total}")