'''
1.  N개의 재료에서 최소 1개 이상을 이용해 요리를 만든다.
2.  재료마다 신맛(S) 쓴맛(B)이 있으며 이 차이를 최소화할 수 있는 결과를 도출.
3.  신맛은 사용한 신맛들의 곱,  쓴맛은 사용한 쓴맛들의 합으로 계산된다.
'''
import sys; input = sys.stdin.readline
N = int(input()); material = []
for _ in range(N): material.append([*map(int, input().split())])
check = [0]*N; ret = 2**31

def calcul():
    global ret
    sour = 1   # 신맛
    pain = 0   # 쓴맛
    for d in range(N):
        if check[d]:
            sour *= material[d][0]
            pain += material[d][1]

    diff = abs(sour - pain)
    if ret > diff:  ret = diff  # update

# combination of at least 1 element.
def choice(start: int) -> None:
    if start == N:
        return

    for d in range(start, N):
        check[d] = 1  # bit ON
        calcul()      # get the difference between two tastes.
        choice(d+1)
        check[d] = 0  # bit OFF

choice(0)
print(ret)