//[SWEA] 5432. 쇠막대기 자르기 D4
//구현, 시뮬레이션
//자료구조 - 스택

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA5432 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            String str = br.readLine();
            char[] c = new char[str.length()];
            // 배열 c에 기호 입력
            for (int i = 0; i < str.length(); i++)
                c[i] = str.charAt(i);

            Stack<Character> stack = new Stack<>();
            int ans=0;

            for (int i = 0; i < str.length(); i++) {

                if(c[i] == '(') {
                    stack.push(c[i]);
                } else if(c[i-1] == '(' && c[i] == ')') {   //레이저일 경우
                    stack.pop(); //레이저 ( 를 제거
                    ans += stack.size();    //앞에 막대가 있었다면 자르기
                } else if(c[i-1] == ')' && c[i] == ')'){    //쇠막대기 닫히는 경우(끝나는경우)
                    stack.pop();    //  쇠막대기 ( 제거
                    ans++;
                }
            }
            System.out.println("#"+ t + " " +ans);
        }
    }
}
