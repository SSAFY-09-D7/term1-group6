import sys;  input = sys.stdin.readline
row, col = map(int, input().split())
colmap = [0 for _ in range(col)]
ret = 0
#---------------------------------------
# 행에 'X'가 없으면 +1
# 'X'가 있으면 해당하는 열의 index = 1
# 각 행과 열에서 필요한 최댓값 반영
for r in range(row):
    line = input().strip()
    if 'X' not in line: ret += 1
    else:
        for i in range(col):
            if line[i] == 'X' and colmap[i] == 0: colmap[i] = 1 

print( max(colmap.count(0), ret) )