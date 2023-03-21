import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static StringBuilder sb = new StringBuilder();
    public static int N, M;
    public static int[] dist;
    public static boolean[] visited;
    public static List<Node> list[];
    public static int[] connect;
    public static int start, end;
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

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N+1];
        for(int i=0; i<=N; i++){
            dist[i] = Integer.MAX_VALUE;
        }

        list = new ArrayList[N+1];
        for(int i=0; i<=N; i++){
            list[i] = new ArrayList<>();
        }

        connect = new int[N+1];
        visited = new boolean[N+1];

        int a, b, c;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, c));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        System.out.println(Dijkstra());

        List<Integer> ans = new ArrayList<>();
        ans.add(end);
        a = end;

        while(true){
            ans.add(connect[a]);
            if(connect[a] == start){
                break;
            }
            a = connect[a];
        }

        int size = ans.size();
        System.out.println(size);
        for(int i=size-1; i>=0; i--){
            sb.append(ans.get(i)).append(" ");
        }

        System.out.println(sb);

    }

    public static int Dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(now.node == end)
                break;
            if(!visited[now.node]){
                visited[now.node] = true;

                for(Node next : list[now.node]){
                    if(dist[next.node] > dist[now.node] + next.dist){
                        dist[next.node] = dist[now.node] + next.dist;
                        pq.add(new Node(next.node, dist[next.node]));
                        // connect 배열에 연결정보 저장하기
                        connect[next.node] = now.node;
                    }
                }
            }
        }
        return dist[end];
    }
    
}
