import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] parents;
    static ArrayList<Edge> edges;
    static class Edge implements Comparable<Edge>{
        int from, to;
        long cost;
        public Edge(int from, int to , long cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.cost, o.cost);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();

        long allCost = 0;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, cost));
            allCost += cost;
        }

        Collections.sort(edges);

        makeSet();

        int count = 0;
        long result = 0;
        boolean flag = false;
        for(int i = 0; i < edges.size(); i++){
            Edge now = edges.get(i);
            if(unionSet(now.from, now.to)){
                count++;
                result += now.cost;
                if(count == N - 1) {
                    flag = true;
                    break;
                }
            }
        }

        if(flag) System.out.println(allCost - result);
        else System.out.println(-1);
    }

    private static boolean unionSet(int a, int b){
        int rootA = findRoot(a);
        int rootB = findRoot(b);

        if(rootA == rootB){
            return false;
        }
        else{
            parents[rootA] = rootB;
            return true;
        }
    }

    private static int findRoot(int val){
        if(parents[val] == val) return val;
        else return parents[val] = findRoot(parents[val]);
    }

    private static void makeSet(){
        parents = new int[N + 1];
        for(int i = 1; i <= N; i++){
            parents[i] = i;
        }
    }
}
