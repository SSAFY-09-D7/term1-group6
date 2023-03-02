import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N;
    public static int[] arr;    // 인구 수
    public static List<Integer>[] list;    // 연결 노드 저장
    public static int[] comb;    // 조합 결과 저장
    public static boolean[] visited;
    public static int MIN = Integer.MAX_VALUE;

    // MAX : 뽑을 조합 개수, idx : 원 배열 인덱스, k : comb 인덱스
    public static void comb(int MAX, int idx, int k){
        if(k == MAX){

            visited = new boolean[N+1];

            // 뽑힌 구역은 true로 변경
            for(int i=0; i<MAX; i++)
                visited[comb[i]] = true;

            // 연결되어있는지 확인
            if(connected(MAX)){

                // 연결되어 있으면 인구수 비교
                int trueCnt = 0;
                int falseCnt = 0;
                for(int i=1; i<=N; i++){
                    if(visited[i])
                        trueCnt += arr[i];
                    else
                        falseCnt += arr[i];
                }

                if(MIN > Math.abs(trueCnt - falseCnt))
                    MIN = Math.abs(trueCnt - falseCnt);
            }

            return;
        }

        for(int i=idx; i<=N; i++){
            comb[k] = i;
            comb(MAX, i+1, k+1);
        }
    }

    public static boolean connected(int MAX){
        // visited 배열에서 true 값들 연결되어있는지 확인
        int start = 0;
        for(int i=1; i<=N; i++) {
            if (visited[i]) {
                start = i;
                break;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] conVisited = new boolean[N+1];
        queue.add(start);

        while(!queue.isEmpty()){
            int a = queue.poll();
            conVisited[a] = true;

            for(int i=0; i<list[a].size(); i++){
                if(visited[list[a].get(i)] && !conVisited[list[a].get(i)]){
                    queue.add(list[a].get(i));
                }
            }
        }
        for(int i=1; i<=N; i++){
            if(visited[i] && !conVisited[i])
                return false;
        }

        // visited 배열에서 false 값들 연결되어있는지 확인
        queue = new LinkedList<>();
        conVisited = new boolean[N+1];
        start = 0;
        for(int i=1; i<=N; i++){
            if(!visited[i]){
                start = i;
                break;
            }
        }
        queue.add(start);

        while(!queue.isEmpty()){
            int a = queue.poll();
            conVisited[a] = true;

            for(int i=0; i<list[a].size(); i++){
                if(!visited[list[a].get(i)] && !conVisited[list[a].get(i)]){
                    queue.add(list[a].get(i));
                }
            }
        }
        for(int i=1; i<=N; i++){
            if(!visited[i] && !conVisited[i])
                return false;
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList[N+1];
        for(int i=0; i<list.length; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            for(int j=0; j<n; j++) {
                int a = Integer.parseInt(st.nextToken());
                list[i].add(a);
            }
        }

        // 1개 ~ N/2개 까지 뽑는 조합 구하기
        for(int i=1; i<=N/2; i++){
            comb = new int[i];
            comb(i, 0, 0);
        }

        if(MIN == Integer.MAX_VALUE)
            System.out.println("-1");
        else
            System.out.println(MIN);
    }
}