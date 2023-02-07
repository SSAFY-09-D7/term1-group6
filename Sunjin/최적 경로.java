import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Point {
	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}


public class Solution{
	static boolean[] visited;
	static Point company, house;
	static List<Point> customers;
	static int N, min;
	static int[] sel;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			company = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			house = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			customers = new ArrayList<Point>();
			for(int i = 0; i < N; i++) {
				Point customer = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				customers.add(customer);
			}
			
			min = Integer.MAX_VALUE;
			visited = new boolean[N];
			sel = new int[N];
			perm(0);
			System.out.printf("#%d %d\n", testCase, min);
		}
	}
	private static void perm(int depth) {
		if(depth == N) {
			int sum = PlusAllDistance(sel);
			if(sum < min) min = sum;
			return;
		}
		
		for(int i = 0; i < customers.size(); i++) {
			if(visited[i] == false) {
				visited[i] = true;
				sel[depth] = i;
				perm(depth + 1);
				visited[i] = false;
			}
		}
	}
	
	private static int PlusAllDistance(int[] sel) {
		int sum = 0;
		sum += getDistance(company, customers.get(sel[0]));
		for(int i = 0; i < customers.size() - 1; i++) {
			sum += getDistance(customers.get(sel[i]), customers.get(sel[i + 1]));
		}
		sum += getDistance(customers.get(sel[customers.size() - 1]), house);
		return sum;
		
	}
	private static int getDistance(Point p1, Point p2) {
		int distance = 0;
		distance = Math.abs(p1.getX() - p2.getX()) 
				+ Math.abs(p1.getY() - p2.getY());
		return distance;
	}
}
