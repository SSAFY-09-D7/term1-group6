import sys; input = sys.stdin.readline
N = int(input())
#가능한 노드: a~z까지. 총 26개의 경우를 담는 2차원 배열
floyd = [[0 for _ in range(27)] for _ in range(27)]
for _ in range(N):
    a, x, b = input().rstrip().split()
    floyd[ord(a)-96][ord(b)-96] = 1

#floyd-warshall 방법으로 참이 되는 연결을 찾는다.
for mid in range(1, 27):
    for start in range(1, 27):
        if start == mid: continue
        for end in range(1, 27):
            if start == end or mid == end: continue

            if (floyd[start][mid] == floyd[mid][end] == 1):
                floyd[start][end] = 1

R = int(input())
for _ in range(R):
    a, x, b = input().rstrip().split()
    if floyd[ord(a)-96][ord(b)-96] == 1: print('T')
    else: print('F')