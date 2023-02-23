import java.io.*;
import java.util.*;

public class Main{

    public static StringBuilder sb = new StringBuilder();
    public static int N, M, V;
    public static List<Integer>[] arr;
    public static boolean[] visited;

    public static void dfs(int v){

        visited[v] = true;
        sb.append(v).append(" ");

        for(int i=0; i<arr[v].size(); i++){
            int a = arr[v].get(i);
            if(!visited[a]){
                dfs(a);
            }
        }
    }

    public static void bfs(int v){

        visited[v] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);

        while(!queue.isEmpty()){
            int a = queue.poll();
            sb.append(a).append(" ");

            for(int i=0; i<arr[a].size(); i++){
                int b = arr[a].get(i);
                if(!visited[b]){
                    visited[b] = true;
                    queue.add(b);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N+1];

        for(int i=0; i<=N; i++){
            arr[i] = new ArrayList<Integer>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a].add(b);
            arr[b].add(a);
        }

        for(int i=0; i<N+1; i++){
            Collections.sort(arr[i]);
        }

        visited = new boolean[N+1];
        dfs(V);

        sb.append("\n");

        visited = new boolean[N+1];
        bfs(V);

        System.out.println(sb);
    }
}
