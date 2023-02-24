package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	static boolean flag;
	static int depth;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        list = new ArrayList[N];
        visited = new boolean[N];
        for(int i = 0; i < N; i++) {
        	list[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	list[from].add(to);
        	list[to].add(from);
        }
        
        flag = false;
        for(int i = 0; i < N; i++) {
        	visited = new boolean[N];
        	dfs(i, 1);
        	if(flag) break;
        }
        
    	if(flag) System.out.println(1);
    	else System.out.println(0);
    }
	private static void dfs(int cur, int depth) {
		if(depth == 5) {
			flag = true;
			return;
		}
		
		visited[cur] = true;
		
		for(int i = 0; i < list[cur].size(); i++) {
			// 방문하지 않은 지점이라면
			if(!visited[list[cur].get(i)]) {
				visited[list[cur].get(i)] = true;
				dfs(list[cur].get(i), depth + 1);
				visited[list[cur].get(i)] = false;
			}
		}
	}

}