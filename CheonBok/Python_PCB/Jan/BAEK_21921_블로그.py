import sys; input = sys.stdin.readline
N, X = map(int, input().split())
arr = tuple(map(int, input().split()))
start = sum(arr[0:X]); ret = start
cnt = 1

# start로 누적합 업데이트.
# 최댓값과 같으면 +1 시킴.
# 새로운 최댓값이 나타나면 1 초기화
for idx in range(N-X):
    start += (arr[idx+X] - arr[idx])
    if start == ret: cnt += 1
    elif start > ret: ret = start; cnt = 1

# 0-SAD, 외에 출력
if ret == 0: print("SAD")
else: print(f"{ret}\n{cnt}")