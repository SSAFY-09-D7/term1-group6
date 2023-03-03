import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N, M, K, X;
    public static List<Integer>[] list;
    public static boolean[] visited;
    public static int[] dist;

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];
        for(int i=0; i<=N; i++)
            list[i] = new ArrayList<>();
        visited = new boolean[N+1];
        dist = new int[N+1];
        for(int i=0; i<=N; i++){
            dist[i] = Integer.MAX_VALUE;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
        }

        func(X);

        boolean flag = false;
        for(int i=1; i<=N; i++){
            if(dist[i] == K){
                flag = true;
                System.out.println(i);
            }
        }

        if(!flag)
            System.out.println("-1");

    }

    public static class Node{
        int n;
        int cnt;

        Node(int n, int cnt){
            this.n = n;
            this.cnt = cnt;
        }
    }
    public static void func(int start){
        Queue<Node> pq = new LinkedList<>();

        dist[start] = 0;

        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(visited[now.n])
                continue;

            visited[now.n] = true;

            for(int next : list[now.n]){
                if(dist[next] > dist[now.n]+1){
                    dist[next] = dist[now.n] + 1;
                    pq.add(new Node(next, dist[now.n]+1));
                }
            }
        }
    }
}