case = int(input())
# 0 idx 안 쓰는 delta
dx = [0, 1, 0] 
dy = [0, 0, 1]

def print_ret(cnt :int, idx :int) -> int:
     print(f"#{cnt} {idx+1}")
     return 1

for cnt in range(1, case+1):
    N, v = map(int, input().split())
    arr = [[0 for _ in range(N)] for _ in range(N)]
    soltbug = []

    for i in range(v): # 소금쟁이 이동 정보 순서대로 저장
        soltbug.append(list(map(int, input().split())))
        arr[soltbug[i][0]][soltbug[i][1]] = 1 

    idx = 0  # 소금쟁이 순서 번호
    flag = 0 # 출력할 소금쟁이 찾으면 1

    for x, y, direction in soltbug:
        if arr[x][y] == 2:  # 시작하려는데 이미 뛴 위치라면 (case1)
            flag = print_ret(cnt, idx)
            break

        for jump in range(3, 0, -1):
            x += dx[direction] * jump
            y += dy[direction] * jump

            if x < N and y < N:
                if arr[x][y] == 2: #이미 뛴 위치에 도달한다면 (case2)
                    flag = print_ret(cnt, idx)
                    break
                else:
                    arr[x][y] = 2
            else:
                break

        if flag: break
        idx += 1

    if flag == 0:
        print(f"#{cnt} 0")