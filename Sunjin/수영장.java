import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[] cost;
    static int[] plan;
    static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++){
            cost = new int[4];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < cost.length; i++){
                cost[i] = Integer.parseInt(st.nextToken());
            }

            plan = new int[13];
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i < plan.length; i++){
                plan[i] = Integer.parseInt(st.nextToken());
            }

            min = Integer.MAX_VALUE;
            recursive(1, 0);
            if(cost[3] < min) min = cost[3];
            sb.append("#" + testCase + " " + min + "\n");
        }
        System.out.println(sb);
    }

    private static void recursive(int month, int totalMoney) {
        // basis part
        if(month > 12){
            if(totalMoney < min) min = totalMoney;
            return;
        }

        // inductive part

        // 1일치로 재귀
        recursive(month + 1, totalMoney + plan[month] * cost[0]);

        // 1달치로 재귀
        recursive(month + 1, totalMoney + cost[1]);

        // 3달치로 재귀
        recursive(month + 3, totalMoney + cost[2]);
    }
}
