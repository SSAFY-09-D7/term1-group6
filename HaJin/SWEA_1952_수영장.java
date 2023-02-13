import java.io.*;
import java.util.*;


public class Solution {

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for(int test_case = 1; test_case <= T; test_case++){
            int[] price = new int[4];   // 1일, 1달, 3달, 1년 이용권 가격
            int[] plan = new int[15];
            int[] answer = new int[15];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<4; i++){
                price[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=12; i++){
                plan[i] = Integer.parseInt(st.nextToken());
            }
            for(int i=1; i<=12; i++){
                answer[i] = Math.min(answer[i-1] + price[0] * plan[i], answer[i-1] + price[1]);  // 1일 vs 1달
                if(i >= 3){
                    answer[i] = Math.min(answer[i], answer[i-3] + price[2]);
                }
            }

            answer[12] = Math.min(answer[12], price[3]);
            System.out.printf("#%d %d\n", test_case, answer[12]);

        }
    }
}