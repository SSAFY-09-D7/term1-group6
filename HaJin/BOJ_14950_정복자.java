import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N, M, T;
    public static List<Node> list;
    public static int[] parent;
    public static int CNT = 0;
    public static int ANS = 0;
    public static class Node implements Comparable<Node>{
        int node1;
        int node2;
        int dist;
        Node(int node1, int node2, int dist){
            this.node1 = node1;
            this.node2 = node2;
            this.dist = dist;
        }
        @Override
        public int compareTo(Node o){
            return this.dist - o.dist;
        }
    }

    public static int find(int a){
        if(parent[a] == a)
            return a;
        else
            return find(parent[a]);
    }

    public static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);

        if(pa != pb){
            if(pa < pb)
                parent[pb] = pa;
            else
                parent[pa] = pb;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        parent = new int[N+1];
        for(int i=0; i<=N; i++){
            parent[i] = i;
        }

        int a, b, c;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            list.add(new Node(a, b, c));
        }

        Collections.sort(list);

        for(Node n : list){
            if(find(n.node1) != find(n.node2)){
                ANS += n.dist + (CNT * T);
                union(n.node1, n.node2);
                CNT += 1;
            }
        }

        System.out.println(ANS);
    }
}