import java.io.*;
import java.util.*;

class Point {
	int idx;
	int height;
	
	public Point(int idx, int height) {
		this.idx = idx;
		this.height = height;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		Stack<Point> stack = new Stack<>();
		
		for(int i = 1; i <= N; i++) {
			int value = Integer.parseInt(st.nextToken());
			Point p = new Point(i, value);
			
			while(!stack.isEmpty()) {
				if(stack.peek().height >= value) {
					sb.append(stack.peek().idx + " ");
					break;
				}
				else {
					stack.pop();
				}
			}
			
			if(stack.isEmpty()) sb.append(0 + " ");
			
			stack.push(p);
		}
		System.out.println(sb);
	}
}
