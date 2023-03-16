import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N, E;
    public static List<Node>[] list;
    public static int NODE1, NODE2;
    public static int[] dist;
    public static boolean[] visited;
    public static int INF = 200000000;

    public static class Node implements Comparable<Node>{
        int node;
        int dist;

        Node(int node, int dist){
            this.node = node;
            this.dist = dist;
        }
        @Override
        public int compareTo(Node o){
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];
        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }

        int a, b, c;
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        NODE1 = Integer.parseInt(st.nextToken());
        NODE2 = Integer.parseInt(st.nextToken());

        // 1번 -> NODE1 -> NODE2 -> N번
        a = dijkstra(1, NODE1) + dijkstra(NODE1, NODE2) + dijkstra(NODE2, N);

        // 1번 -> NODE2 -> NODE1 -> N번
        b = dijkstra(1, NODE2) + dijkstra(NODE2, NODE1) + dijkstra(NODE1, N);

        if(a >= INF && b >= INF){
            System.out.println("-1");
        }
        else System.out.println(Math.min(a, b));
    }
    
    public static int dijkstra(int start, int end){

        dist = new int[N+1];
        visited = new boolean[N+1];

        for(int i=0; i<=N; i++){
            dist[i] = INF;
        }

        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int now = node.node;
            int d = node.dist;

            if(!visited[now]){

                visited[now] = true;
                for(Node next : list[now]){
                    if(dist[next.node] > dist[now] + next.dist){
                        dist[next.node] = dist[now] + next.dist;
                        pq.add(new Node(next.node, dist[next.node]));
                    }
                }
            }
        }

        return dist[end];
    }
}