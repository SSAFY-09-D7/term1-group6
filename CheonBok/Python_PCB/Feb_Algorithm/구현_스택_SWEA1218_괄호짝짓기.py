'''
1. 4가지 종류의 괄호 '()', '[]', '{}', '<>'
2. 사용된 괄호의 짝이 모두 맞는지 확인
'''
T = 11
from collections import deque
pair = {'}':'{', ')':'(', '>':'<', ']':'['}
stk = deque()
for t in range(1, T):
    stk.clear()  # 테스트 케이스마다 초기화
    N = int(input())
    line = input()
    
    flg = 0
    for txt in line:
        if txt in ['[', '{', '(', '<']:
            stk.append(txt)
        else:
            if pair[txt] != stk[-1]:
                flg = 1
                break
            else:
                stk.pop()

    # flg 참 또는 스택에 값이 있다면 0
    if flg or stk: print(f"#{t} 0") 
    else: print(f"#{t} 1")
    
