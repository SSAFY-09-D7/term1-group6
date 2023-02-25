'''
1. N장의 카드에서 3장의 카드를 고른다.
2. 3장 카드의 합이 최대한 M에 가까워야 한다. (단 M보다 크면 안 된다.)
3. 만들 수 있는 가장 큰 합계 ret를 출력.
'''
import sys; input = sys.stdin.readline
sys.setrecursionlimit(2**10)

# Combination
def makeCardSet(start: int, cnt: int) -> None:
    global ret
    if cnt == 3:
        t = sum(group)
        if ret < t <= M: ret = t
        return

    for d in range(start, N):
        group[cnt] = cards[d]
        makeCardSet(d+1, cnt+1)

# N: the number of cards. (3 <= N <= 100)  100C3 = 161700
# 10 <= M 300,000
N, M = map(int, input().split())  
cards = list(map(int, input().split()))
group = [0]*3; ret = 0
makeCardSet(0, 0)
print(ret)