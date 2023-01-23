import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
 
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int now = 0;
            String s = sc.next();
            int sum = 0;
 
            for(int i=0; i<s.length()-1; i++){
                if (s.charAt(i) == '(' && s.charAt(i+1) == ')'){
                    sum += now;
                    i++;
                }
                else if (s.charAt(i) == '('){
                    now += 1;
                }
                else if (s.charAt(i) == ')'){
                    now -= 1;
                    sum += 1;
                }
            }
            sum += now;
 
            System.out.printf("#%d %d\n", test_case, sum);
 
        }
    }
}