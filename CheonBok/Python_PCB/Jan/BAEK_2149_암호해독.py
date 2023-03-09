import sys
input = sys.stdin.readline
keys = input().rstrip() # 입력1: Key
keylen = len(keys)
text = input().rstrip() # 입력2: 암호문

textarr = [] # 최종 문자 Table
textarr.append(keys) # 첫 줄은 Key

# 방문한 인덱스를 수정하기 위한 list
visitarr = [0 for _ in range(keylen)] 

# text 길이에 맞춘 list 초기화
for _ in range(len(text)//keylen):
    textarr.append(["" for _ in range(keylen)])

# 입력 순서 정렬
orderarr = sorted(keys)

textidx = 0  # list에 저장할 암호문자 index

# 정렬된 순서대로 탐색 수행 
for s in orderarr:
    idx = textarr[0].index(s) # idx = 열 index

    # 이미 한 번 처리한 idx라면
    if visitarr[idx] >= 1:
        tmp = idx # visitarr의 첫 번째 탐색 idx
        idx = textarr[0].index(s, idx+visitarr[idx], keylen) # 이미 탐색했던 idx 이후부터 탐색
        visitarr[tmp] = idx+1  # 같은 문자가 여러개 있을 수 있으므로 다음 index 갱신
    else:
        visitarr[idx] = 1 # 첫 방문 시 1
    
    # 해당하는 열 index(idx)에 해당하는 모든 값 대입
    for i in range(1, (len(text)//keylen)+1):
        textarr[i][idx] = text[textidx]
        textidx += 1


for i in range(1, (len(text)//keylen)+1):
    print("".join(textarr[i]), end="")