import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class cs_9934 {
    static int K;
    static int[] arr;
    static HashMap<Integer, ArrayList<Integer>> map;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());
        arr = new int[(int)Math.pow(2, K) - 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < arr.length; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        map = new HashMap<>();
        for(int i = 0; i < K; i++){
            map.put(i, new ArrayList<>());
        }

        dfs(0, arr.length, 0);

        for(int i = 0; i < K; i++){
            for(int cur : map.get(i)){
                sb.append(cur + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int start, int end, int depth) {
        if(depth == K){
            return;
        }

        int mid = (start + end) / 2;
        map.get(depth).add(arr[mid]);

        dfs(start, mid, depth + 1);
        dfs(mid + 1, end, depth + 1);
    }
}
