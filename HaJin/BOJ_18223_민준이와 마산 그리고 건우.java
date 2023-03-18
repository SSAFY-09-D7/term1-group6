import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int V, E, P;
    public static int[] dist;
    public static List<Node>[] list;
    public static boolean[] visited;

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
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        list = new ArrayList[V+1];
        for(int i=0; i<=V; i++){
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

        int min = dijkstra(1, V);
        int save = dijkstra(1, P) + dijkstra(P, V);

        if(min == save)
            System.out.println("SAVE HIM");
        else System.out.println("GOOD BYE");

    }

    public static int dijkstra(int start, int end){
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist = new int[V+1];
        visited = new boolean[V+1];
        for(int i=0; i<=V; i++){
            dist[i] = Integer.MAX_VALUE;
        }

        dist[start] = 0;
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(!visited[now.node]){
                visited[now.node] = true;

                for(Node next : list[now.node]){
                    if(dist[next.node] > dist[now.node] + next.dist){
                        dist[next.node] = dist[now.node] + next.dist;
                        pq.add(new Node(next.node, dist[next.node]));
                    }
                }
            }

        }
        return dist[end];
    }

}