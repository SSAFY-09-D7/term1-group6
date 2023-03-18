import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    public static int K, V, E;

    public static List<Integer>[] list;
    public static boolean[] visited;
    public static boolean[] color;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        for(int test_case=1; test_case<=K; test_case++){
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            list = new ArrayList[V+1];
            for(int i=0; i<=V; i++){
                list[i] = new ArrayList<>();
            }

            visited = new boolean[V+1];
            color = new boolean[V+1];

            int a, b;
            for(int i=0; i<E; i++){
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                list[a].add(b);
                list[b].add(a);
            }

            boolean result = true;
            for(int i=1; i<=V; i++){
                if(!visited[i]){
                    result = bfs(i);
                }
                if(!result)
                    break;
            }

            if(result)
                System.out.println("YES");
            else
                System.out.println("NO");

        }
    }
    public static boolean bfs(int start){
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited[start] = true;
        color[start] = true;

        while(!queue.isEmpty()){
            int now = queue.poll();

            for(int next : list[now]){
                // 이미 방문한 노드인 경우 color 다른지 확인
                if(visited[next]){
                    if(color[now] == color[next]){
                        return false;
                    }
                }
                // 방문하지 않은 노드인 경우 color 다른 색으로, queue에 넣기
                else{
                    visited[next] = true;
                    color[next] = !color[now];
                    queue.add(next);
                }
            }
        }
        return true;
    }
}
