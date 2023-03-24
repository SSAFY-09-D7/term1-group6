/*
 * 놓친 부분: 맨해튼 거리 주면서 푸는 문제는 대부분 맵을 만들어 가는게 아닌듯
 * 		    범위 정해서 대소 비교하는 유형 익히기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9205_맥주마시면서걸어가기 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}

	}

	static int T, N;
	static Point[] store;
	static Point start, end;
	static boolean[] v;
	static boolean success=false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());
			store = new Point[N];
			v = new boolean[N];
			success = false;

			st = new StringTokenizer(br.readLine());
			start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				store[j] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			bfs();
			if(!success) System.out.println("sad");

		}

	}

	private static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(start);
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			int dist = Math.abs(p.r-end.r) + Math.abs(p.c-end.c);
			//도착점에 갈 수 있으면 간다
			if(dist <= 1000) {
				System.out.println("happy");
				success = true;
				return;
			}	
			
			for (int i = 0; i < store.length; i++) {
				Point next = store[i];
				dist = Math.abs(p.r-next.r) + Math.abs(p.c-next.c);
				if(dist<=1000 && !v[i]) {
					q.add(next);
					v[i] = true;
				}
			}
		}
		
	}

}
