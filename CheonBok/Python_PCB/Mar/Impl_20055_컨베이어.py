import sys; input = sys.stdin.readline
from collections import deque
N, M = map(int, input().split())
conveyer = deque(list(map(int, input().split())))  # 컨베이어 내구도
robots = deque([0]*N)  # 로봇 배치도

rnd = 0    # 모든 작업을 마무리한 순환 횟수 (최종 출력)
rcnt = 0   # 컨베이어에 배치된 로봇 수
zero = 0   # 0이된 컨베이어 내구도 개수


 # while 내에서 zero는 2차례 변한다.  --> == M이 안 될 수도 있으므로 <M
 # 문제에서도 개수가 M 이상이라 언급
while (zero < M): 
    conveyer.appendleft(conveyer.pop())
    robots.appendleft(robots.pop())
    if robots[N-1]: rcnt -= 1
    robots[N -1] = 0

    if rcnt:
        for idx in range(N-2, -1, -1): # 1-2
            if conveyer[idx+1] and not robots[idx+1] and robots[idx]:
                conveyer[idx+1] -= 1

                if not conveyer[idx+1]: zero += 1
                robots[idx] = 0

                if idx+1 != N-1: # 내리는 위치: 로봇 버림
                    robots[idx+1] = 1

    if robots[N - 1]: rcnt -= 1
    robots[N-1] = 0
    if conveyer[0] and not robots[0]:  #2
        rcnt += 1
        conveyer[0] -= 1
        if not conveyer[0]: zero += 1
        robots[0] = 1
    rnd += 1

print(rnd)