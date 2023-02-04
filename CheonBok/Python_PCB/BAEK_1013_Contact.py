import sys;  input = sys.stdin.readline;  rnd = int(input())
for _ in range(rnd):
    line = input().strip()
    retable : int
    flag = -1

    # 문자 하나씩 탐색 진행
    for L in line:
        retable = 0 

        # 첫 시작(-1)
        if flag == -1:
            if L == '0': flag = 0
            elif L == '1': flag = 1
        
        # 01을 만들어야 하는 경우
        elif flag == 0:
            if L == '1':
                retable = 1 
                flag = -1
            else:
                break
                
        #1001을 만들어야 하는 경우
        elif flag == 1:
            if L == '0' : flag = 2
            else:
                break
        
        # 10까지 맞음, 다음이 0이 나와야 한다.
        elif flag == 2:
            if L == '0': flag = 3
            else:
                break
        
        # 100까지 맞음, 0이 나오거나 1이 나와야 한다.
        elif flag == 3:
            if L == '1':
                retable = 1 
                flag = 4
        
        
        # 1001까지 맞음. 다음은 1이 나오면 계속, 0이 나오면 새로운 시작.
        elif flag == 4:
            if L == '0': flag = 0
            elif L == '1': 
                retable = 1
                flag = 5
        
        
        # 1이 계속 나와 애매한 경우. 이 다음부터 0이 나오면 10인 케이스가 될 수도, 01의 시작이 될 수도 있다.
        # 1이 또 나오면 이 상태를 유지해야 한다.
        elif flag == 5:
            retable = 1
            if L == '0': flag = 6

        
        # 1이 계속나와 애매해진 관계에서 해결점이 나온다
        # 1 = 01의 마지막이 된다 = flag: -1
        # 0 = 10의 0이 된다 = flag: 3
        elif flag == 6:
            if L == '1':
                retable = 1
                flag = -1
            elif L == '0': flag = 3

    if retable: print('YES')
    else: print("NO")