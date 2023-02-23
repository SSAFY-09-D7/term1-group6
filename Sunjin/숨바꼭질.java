package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static int N, K;
	static int result;
	
	static class Info{
		int point, depth;
		public Info(int point, int depth) {
			this.point = point;
			this.depth = depth;
		}
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        bfs(N, 0);
        System.out.println(result);
    }

	private static void bfs(int startPoint, int startDepth) {
		Queue<Info> q = new LinkedList<>();
		boolean[] visited = new boolean[100001];
		
		q.add(new Info(startPoint, startDepth));
		visited[startPoint] = true;
		while(!q.isEmpty()) {
			Info now = q.poll();
			
			if(now.point == K) {
				result = now.depth;
				return;
			}
			
			int frontOne = now.point + 1;
			if(frontOne >= 0 && frontOne < 100001 && !visited[frontOne]) {
				q.add(new Info(now.point + 1, now.depth + 1));
				visited[frontOne] = true;
			}
			
			
			int backOne = now.point - 1;
			if(backOne >= 0 && backOne < 100001 && !visited[backOne]) {
				q.add(new Info(now.point - 1, now.depth + 1));
				visited[backOne] = true;
			}
			
			int jump = now.point * 2;
			if(jump >= 0 && jump < 100001 && !visited[jump]) {
				q.add(new Info(2 * now.point, now.depth + 1));
				visited[jump] = true;
			}
		}
	}
}