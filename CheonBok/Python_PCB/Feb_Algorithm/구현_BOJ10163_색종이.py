'''
가로 최대 1001, 세로 최대 1001  (1001 x 1001)
N장의 색종이를 붙이며 순서에 의해 겹쳐지는 부분이 존재한다.

각칸에 표시된 값 (a,b)는 해당 칸의 번호를 나타낸다.
가장 왼쪽 아래의 칸은 (0,0) 가장 오른 쪽 위의 칸은 (5,5)이다.

입력된 순서대로 색종이를 붙이며 최종 결과의 색종이 면적을 하나씩 정수로 출력.
* 색종이가 보이지 않는 상태면 0 출력

* 그림 예시에서는 (0,0) 좌표가 좌측 하단이지만, 색종이 붙는 형태나 좌표 특징 상
상하 반전된 구조일 뿐 값의 변화는 없다. -> (0,0)을 좌측 상단으로 두고 구현 가능.
'''
import sys; input = sys.stdin.readline
N = int(input()); maxRow = maxCol = 0
paperMap = [[0 for _ in range(1001)] for _ in range(1001)]
for number in range(1, N+1):
    # Start Point(sR, sC),  offSet(eR, eC)
    sR, sC, eR, eC = map(int, input().split())
    for row in range(sR, sR+eR): paperMap[row][sC: sC+eC] = [number] * eC

cnt = [0] * (N+1)  #ret
for i in range(1001):
    for n in range(1, N+1):
        cnt[n] += paperMap[i].count(n)

for i in range(1, N+1): print(cnt[i])


