import sys; input = sys.stdin.readline
N = int(input()) #집 수
rgb = [list(map(int, input().split()))] + [[1e8] * 3]
house = []
for _ in range(N-1):
    house.append(list(map(int, input().split())))

#rgb[0]은 이전까지 탐색한 집에서 각각의 경우로 칠했을 때 얻게되는 최솟값
#rgb[1]은 현재 위치한 집을 각각의 경우로 색칠했을 때 얻게되는 최솟값
for i in range(N-1):
    rgb[1][0] = min(rgb[0][1] + house[i][0], rgb[0][2] + house[i][0])
    rgb[1][1] = min(rgb[0][0] + house[i][1], rgb[0][2] + house[i][1])
    rgb[1][2] = min(rgb[0][0] + house[i][2], rgb[0][1] + house[i][2])

    rgb[0][0] = rgb[1][0]
    rgb[0][1] = rgb[1][1]
    rgb[0][2] = rgb[1][2]

print(min(rgb[0]))