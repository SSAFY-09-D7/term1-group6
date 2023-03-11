import sys
input = sys.stdin.readline

# ------------------------------------------------------------------------
# 문제에서 주어진 문자 집합. 비트 연산 목적으로 2진수로 변환. (표시는 10진 정수값)
# ------------------------------------------------------------------------
Character = {0:'A', 15:'B', 19:'C', 28:'D', 38:'E', 41:'F', 53:'G', 58:'H'}

cnum = int(input())     # kyboard 1
text = input().rstrip() # kyboard 2
ret = ''  # 결과 문자열 출력용

idx = 0   # Slicing index
round = 1 # 문자를 찾을 수 없으면 출력하는 index

while (idx != cnum*6):
    tmp = int(text[idx:idx+6], 2)

    # ----------------------------------------------------------------------------
    # @@ if   : Character dictionary에 값 있으면 추출
    # @@ elif : '111111' 값이면 index(round) 출력 후 종료
    # @@ else : dictionary의 값과 target(tmp)을 XOR 연산. -> 비트끼리 서로 다르면 1
    #           결과로 나온 '1'의 개수가 1개면 해당 문자 채택.
    # -----------------------------------------------------------------------------
    if tmp in Character: ret += Character[tmp]
    elif tmp == 63:
        print(round)
        sys.exit(0)
    else:
        flag = False
        for comp in Character.keys():
            diff = format(tmp ^ comp, 'b').count('1')

            if diff == 1:
                ret += Character[comp]
                flag = False
                break
            else:
                flag = True

        # 8개의 문자 중 하나도 판별할 수 없다면 flag는 True 상태 -> 종료
        if flag:
            print(round)
            sys.exit(0)    
        
    idx += 6
    round += 1

print(ret)

     
