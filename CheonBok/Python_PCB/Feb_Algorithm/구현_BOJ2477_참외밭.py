'''
1. ㄱ 자 모양의 참외밭. (90, 180 ,270 회전 모양 가능)
2. 출발 지점은 어느 꼭지점 중 한 곳이므로(정해지지 않음) 입력의 차이는 발생함
3. 하지만, 모두 같은 참외밭에 대한 입력이므로 출력 결과(밭에서 자라는 참외 수)는 동일.

 1 <= K <= 20
 모든 입력 =   1 <= x <= 500
 delta:  1:동, 2:서, 3:남, 4:북
'''
import sys; input = sys.stdin.readline
K = int(input())
sizeMap = []; direc = [0 for _ in range(5)] # 0 인덱스 사용 X
idx = []
for _ in range(6):
    d, v = map(int, input().split())
    sizeMap.append([d, v])
    direc[d] += 1

sidx = eidx = 0
for i in range(6):
    if direc[sizeMap[i][0]] == 1:  # 1개의 방향만 가지고 있다면
        idx.append(i)

# print(sizeMap)
# print(direc)
# print(idx)

'''
# 규칙 1. 참외밭은 ㄱ 자 모양으로 고정되어 있으므로 무조건 2개의 방향은 2번 나타난다.
#        1번 나타난 방향은 각각의 가로, 세로의 최대 길이가 된다.

# 규칙 2. 2번 나타난 방향에서 하나씩 골라서 사이즈를 계산해야 한다.
#        1번 나타난 방향의 인덱스와 붙어있는 인덱스의 사이즈는 계산하지 않는다.
'''
# 우선하는 인덱스가 왼쪽 기준, 다음 인덱스가 오른쪽 기준
# 왼쪽 기준으로 2칸 이전 값 * 오른쪽 기준으로 2칸 이후 값

if idx == [0, 5]: #극 위치에 있는 예외 경우
    t = sizeMap[0][1] * sizeMap[5][1]       # 사각형 크기
    target = sizeMap[2][1] * sizeMap[3][1]  # 차감될 크기
else:
    if idx[0] > idx[1]: sidx = idx[1]; eidx = idx[0]
    else: sidx = idx[0]; eidx = idx[1]
    t = sizeMap[sidx][1] * sizeMap[eidx][1]

    ts = 0; te = 0  # 차감할 값이 있는 인덱스
    if sidx < 2: ts = 4+sidx
    else: ts = sidx-2

    if eidx > 3: te = (eidx+2) % 6
    else: te = eidx+2

    target = sizeMap[ts][1] * sizeMap[te][1]

print((t - target) * K)