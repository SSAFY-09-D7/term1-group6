import java.util.Scanner;
import java.io.FileInputStream;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
 
        int T = sc.nextInt();
 
        for(int test_case = 1; test_case <= T; test_case++){
 
            int sum = 0;
            int[][] arr = new int[50][50];
            int n = sc.nextInt();
 
            for(int i = 0; i < n; i++){
                String s = sc.next();
                for(int j = 0; j < n; j++){
                    arr[i][j] = s.charAt(j) - '0';
                }
            }
 
            for(int i = 0; i < n; i++){
                if(i <= n/2){
                    for(int j = n/2 - i; j <= n/2 + i; j++)
                        sum += arr[i][j];
                }
                else
                    for(int j = i - n/2 ; j <= n * 3 / 2 - i - 1; j++)
                        sum += arr[i][j];
            }
             
            System.out.printf("#%d %d\n", test_case, sum);
        }
    }
}