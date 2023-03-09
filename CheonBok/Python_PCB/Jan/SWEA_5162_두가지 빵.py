testcase = int(input())
for i in range(1, testcase+1):
    A, B, C = map(int, input().split())
    first = min(A,B)
    second = max(A,B)

    # 몫과 나머지
    tmp = list(divmod(C, first))

    if tmp[1] <= second:
        tmp[0] += tmp[1]//second

    print(f"#{i} {tmp[0]}")