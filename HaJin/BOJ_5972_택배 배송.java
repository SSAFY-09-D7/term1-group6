import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    public static int N, M;
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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 리스트 초기화
        list = new ArrayList[N+1];
        for(int i=0; i<=N; i++){
            list[i] = new ArrayList<>();
        }

        // dist 초기화
        dist = new int[N+1];
        for(int i=0; i<=N; i++){
            dist[i] = Integer.MAX_VALUE;
        }

        visited = new boolean[N+1];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }

        Dijkstra(1);

        System.out.println(dist[N]);
    }

    public static void Dijkstra(int start){

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int nowNode = node.node;

            if(!visited[nowNode]){

                visited[nowNode] = true;

                for(Node next : list[nowNode]){
                    if(dist[next.node] > dist[nowNode] + next.dist){
                        dist[next.node] = dist[nowNode] + next.dist;
                        pq.add(new Node(next.node, dist[next.node]));
                    }
                }
            }
        }

    }

}