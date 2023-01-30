c = int(input())
for tcase in range(1, c+1):
    N = input(); Nlen = len(N)

    # 1의 자리는 어떤 것이든 불가
    if len(N) == 1: print(f"#{tcase} impossible"); continue
    # 2배수 부터 자리수를 넘어가면 탐색 불필요
    elif len(N) < len(str(int(N)*2)): print(f"#{tcase} impossible"); continue

    nmap = [0 for _ in range(10)] #출현 개수 저장
    for t in N: nmap[int(t)] += 1 

    idx = 2
    tmp = str(int(N)*idx)

    while (len(tmp) <= Nlen):
        flag = 0 

        # 문자 집합이 다르면 다음 경우로 넘어감
        if set(N) != set(tmp): 
            idx += 1 
            tmp = str(int(N)*idx) 
            continue

        for i in range(10):
            if tmp.count(str(i)) == nmap[i]: flag = 1
            else: flag = 0

        
        if flag: break   #일치하는 경우를 찾으면 while 탈출
        idx += 1 
        tmp = str(int(N)*idx)
        

    if flag: print(f"#{tcase} possible")
    else: print(f"#{tcase} impossible")