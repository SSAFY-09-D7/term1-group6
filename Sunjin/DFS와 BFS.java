package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static int N, M, V;
    static int[][] map;
    static StringBuilder sb;
    static boolean[] Dvisited;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        
        map = new int[N + 1][N + 1];
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	
        	map[from][to] = 1;
        	map[to][from] = 1;
        }
        
        Dvisited = new boolean[N + 1];
        dfs(V);
        sb.append("\n");
        bfs(V);
        System.out.println(sb);
    }
	
	private static void dfs(int start) {
		
		Dvisited[start] = true;
		sb.append(start + " ");
		
		for(int i = 1; i <= N; i++) {
			if(map[start][i] == 1 && !Dvisited[i]) {
				dfs(i);
			}
		}
		
	}
	
	private static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		q.add(start);
		visited[start] = true;
	
		while(!q.isEmpty()) {
			int current = q.poll();
			sb.append(current + " ");
			for(int i = 1; i <= N; i++) {
				if(map[current][i] == 1 && !visited[i]) {
					q.add(i);
					visited[i] = true;
				}
			}
		}
	}


}