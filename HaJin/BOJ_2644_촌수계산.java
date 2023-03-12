import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    public static int N, M;
    public static int start, end;
    public static List<Integer>[] list;
    public static boolean[] visited;
    public static int ANS = -1;
    
    public static class Node{
        int num;
        int cnt;

        Node(int num, int cnt){
            this.num = num;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());

        visited = new boolean[N+1];
        list = new ArrayList[N+1];
        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        bfs();

        System.out.println(ANS);
    }

    public static void bfs(){
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(start, 0));
        visited[start] = true;

        while(!queue.isEmpty()){
            Node node = queue.poll();
            int num = node.num;
            int cnt = node.cnt;

            if(num == end){
                ANS = cnt;
                break;
            }

            for(int i=0; i<list[num].size(); i++){
                if(!visited[list[num].get(i)]){
                    visited[list[num].get(i)] = true;
                    queue.add(new Node(list[num].get(i), cnt+1));
                }
            }

        }
    }

}