import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution
{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            String str = br.readLine();
            int sum = 0;
            int value = 0;
            for(int i = 0; i < str.length() - 1; i++) {
                // 레이저일 경우
                if(str.charAt(i) == '(' && str.charAt(i + 1) == ')'){
                    sum += value;
                    i++;
                    continue;
                }

                // 여는 막대 블록일 경우
                if(str.charAt(i) == '(') {
                    sum++;
                    value++;
                }
                // 닫는 막대 블록일 경우
                if(str.charAt(i) == ')') {
                    value--;
                }
            }
            value--;
            sum += value;
            System.out.printf("#%d %d\n", test_case, sum);
        }
    }
}