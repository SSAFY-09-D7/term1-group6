import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static int max;
    static int[][] keyForQuest;
    static boolean[] list;
    static int[] nums;
    static ArrayList<Integer> numList;
    static int[] select;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        select = new int[n];
        keyForQuest = new int[m][k];
        list = new boolean[2 * n + 1];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < k; j++){
                keyForQuest[i][j] = Integer.parseInt(st.nextToken());
//                list[keyForQuest[i][j]] = true;
            }
        }
//        numList = new ArrayList<>();
//        for(int i = 0; i < list.length; i++){
//            if(list[i]) numList.add(i);
//        }
        max = 0;
        comb(0, 0);
        System.out.println(max);
    }

    private static void comb(int idx, int count) {
        if(count == n){
            // 뽑은 조합이 몇 개의 퀘스트를 통과하는지
            boolean[] selectList = new boolean[2 * n + 1];
            for(int selectNum : select){
                selectList[selectNum] = true;
            }

            int clearCount = 0;
            for(int i = 0; i < m; i++){
                int canDo = 0;
                for(int j = 0; j < k; j++){
                    if(selectList[keyForQuest[i][j]]){
                        canDo++;
                    }
                }
                if(canDo == k) clearCount++;
            }

            if(max < clearCount) max = clearCount;

            return;
        }
        for(int i = idx; i < list.length; i++){
            select[count] = i;
            comb(i + 1, count + 1);
        }
    }
}
