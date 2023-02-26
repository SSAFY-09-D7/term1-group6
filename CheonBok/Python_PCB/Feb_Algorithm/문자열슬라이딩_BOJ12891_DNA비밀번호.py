# BOJ 12891 DNA 비밀번호
# 슬라이딩 윈도우, 문자열
'''
1. 사용할 수 있는 DNA는 ['A', 'C', 'G', 'T']
2. 문제에서 언급된 "뽑는 부분문자열"은 특정 문자열 X에서 N개의 연속된 문자로 구성된 것을 의미한다.
   -> X = ACCTGTGCA 에서 5개로 뽑는 부분문자열 => 'ACCTG', 'CCTGT', 'CTGTG' 등

3. 규칙: 특정 문자의 개수가 특정 개수 이상이어야 비밀번호로 사용할 수 있다.
4. 규칙을 통해 만들 수 있는 비밀번호의 개수를 찾기.

  1 <=  비밀번호로 사용할 문자열 길이 <= DNA 문자열 길이 <= 1,000,000 
'''
import sys; input = sys.stdin.readline
# 순서대로 A C G T
S, P = map(int, input().split())
DNA = input().rstrip()
rule = list(map(int, input().split()))
ret = 0; 

# 사용된 DNA 문자의 개수 저장
used = {'A':0, 'C':0, 'G':0, 'T':0}

# DNA 최소 사용 규칙을 만족하면 1, 아니면 0 return
def checkrule() -> bool: 
    flg = 1
    for idx, type in enumerate(['A', 'C', 'G', 'T']):
        if used[type] < rule[idx]: flg=0; break
    
    if flg: return 1
    else: return 0


# 첫 부분 문자열 구간 비밀번호 검사
for d in range(P): used[DNA[d]] += 1
if checkrule(): ret += 1

# 이후의 가능한 모든 부분 문자열 구간의 비밀번호 세팅 후 결과 계산
for d in range(1, S-P+1):
    used[DNA[d-1]] -= 1
    used[DNA[d+P-1]] += 1

    if checkrule(): ret += 1

print(ret)