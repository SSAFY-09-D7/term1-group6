import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static final int INF = Integer.MAX_VALUE;
    static int[] distance;
    static boolean[] visited;
    static ArrayList<Node>[] list;
    static class Node implements Comparable<Node>{
        int to, cost;
        public Node(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[from].add(new Node(to, cost));
            list[to].add(new Node(from, cost));
        }

        dijkstra(1);
        System.out.println(distance[N]);
    }

    private static void dijkstra(int start) {
        distance = new int[N + 1];
        visited = new boolean[N + 1];
        Arrays.fill(distance, INF);
        distance[start] = 0;

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();

            if(visited[now.to]) continue;

            visited[now.to] = true;

            if(now.to == N) return;

            for(Node next : list[now.to]){
                if(distance[now.to] + next.cost < distance[next.to]){
                    distance[next.to] = distance[now.to] + next.cost;
                    q.add(new Node(next.to, distance[next.to]));
                }
            }
        }
    }
}
