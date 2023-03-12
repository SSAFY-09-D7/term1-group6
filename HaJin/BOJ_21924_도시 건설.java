import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    public static int N, M;
    public static int[] parent;
    public static List<Node> list;
    public static long ANS = 0;
    public static long all = 0;
    
    public static class Node implements Comparable<Node>{
        int a;
        int b;
        int cost;

        Node(int a, int b, int cost){
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }

    public static int find(int a){
        if(parent[a] == a)
            return a;
        else
            return parent[a] = find(parent[a]);
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

        parent = new int[N+1];
        list = new ArrayList<>();

        for(int i=0; i<=N; i++)
            parent[i] = i;

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.add(new Node(a, b, c));
            all += c;
        }

        Collections.sort(list);

        int edge = func();

        if(edge == N-1)
            System.out.println(all - ANS);
        else
            System.out.println("-1");
    }


    public static int func(){

        int cnt = 0;

        for(int i=0; i<M; i++){
            Node node = list.get(i);

            if(find(node.a) != find(node.b)){
                ANS += node.cost;
                cnt += 1;
                union(node.a, node.b);
            }
        }

        return cnt;
    }

}