import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    public static int N;
    public static List<Integer>[] list;
    public static int[] parent;
    
    public static class Node{
        int parent;
        int num;

        Node(int parent, int num){
            this.parent = parent;
            this.num = num;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        parent = new int[N+1];
        list = new ArrayList[N+1];
        for(int i=0; i<=N; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        bfs();

        for(int i=2; i<=N; i++)
            System.out.println(parent[i]);
    }

    public static void bfs(){

        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(1, 1));

        while(!queue.isEmpty()){
            Node node = queue.poll();
            int nowNum = node.num;
            int nowP = node.parent;

            for(int next : list[nowNum]){
                // 이미 방문한 노드 continue;
                if(parent[next] != 0)
                    continue;

                if(next == nowP)
                    continue;
                else{
                    parent[next] = nowNum;
                    queue.add(new Node(nowNum, next));
                }
            }
        }
 
    }
}