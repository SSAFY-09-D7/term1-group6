import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	
	public static int N, K;
	public static int MIN = Integer.MAX_VALUE;
	public static boolean[] visited;
	
	static class Node{
		int location;
		int cnt;
		
		Node(int location, int cnt){
			this.location = location;
			this.cnt = cnt;
		}
	}
	
	public static void func() {
		Queue<Node> queue = new LinkedList<>();
		
		queue.add(new Node(N, 0));
		visited[N] = true;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int loc = node.location;
			int cnt = node.cnt;
			
			if(loc == K) {
				if(cnt < MIN)
					MIN = cnt;
				break;
			}

			if(loc-1>=0 && !visited[loc-1]) {
				queue.add(new Node(loc-1, cnt+1));
				visited[loc-1] = true;
			}
				
			if(loc+1 <=100000 && !visited[loc+1]) {
				queue.add(new Node(loc+1, cnt+1));
				visited[loc+1] = true;
			}
				
			if(loc*2 <= 100000 && !visited[loc*2]) {
				queue.add(new Node(loc*2, cnt+1));
				visited[loc*2] = true;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st ;
	
	    st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken());
	    K = Integer.parseInt(st.nextToken());
	    visited = new boolean[100001];
	    func();
	    
	    System.out.println(MIN);
	}
}