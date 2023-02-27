'''
총 10개의 테스트 케이스
1. 8개의 숫자를 입력.

규칙 1. 첫 번째 숫자를 1감소한 뒤 뒤로 보낸다.
규칙 2. 다음 수는 2 감소한 뒤 맨 뒤로 보낸다.
이렇게 반복해서 5 감소까지 마무리되면 1사이클 종료.
규칙 3. 숫자가 감소할 때 0보다 작아지면 0으로 유지하며 프로그램을 종료한다.

그 때의 8자리 숫자 값이 최종 출력
'''
from collections import deque
T = 11
for t in range(1, T):
    n = int(input())
    arr = deque(list(map(int, input().split())))

    offset = 1
    while (arr[-1] != 0):
        tmp = arr.popleft() - offset
        if tmp < 0: arr.append(0)
        else: arr.append(tmp)
        offset += 1

        # 사이클은 5까지 (이후엔 1로 초기화)
        if offset == 6: offset = 1

    print(f"#{t}", end = " ")
    print(*arr)


    