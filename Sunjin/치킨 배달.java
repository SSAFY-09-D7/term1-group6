package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int min;
	static int[][] map;
	static boolean[] visited;
	static ArrayList<Point> houseList;
	static ArrayList<Point> chickenList;
	static int[] liveChickenList;
	static class Point{
		int row, col;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][N + 1];
		chickenList = new ArrayList<>();
		liveChickenList = new int[M];
		houseList = new ArrayList<>();
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) houseList.add(new Point(i, j));
				if(map[i][j] == 2) chickenList.add(new Point(i, j));
			}
		}
		
		visited = new boolean[chickenList.size()];
		min = Integer.MAX_VALUE;
		comb(0,0);
		
		System.out.println(min);
	}
	private static void comb(int idx, int count) {
		// 조합 다 뽑음
		if(count == M) {
			// 집별로 거리 계산 후 최솟값 판정
			int cityChickenDistance = findChDistance();
			if(cityChickenDistance < min) min = cityChickenDistance;
			return;
		}
		for(int i = idx; i < chickenList.size(); i++) {
			if(visited[i] == false) {
				visited[i] = true;
				liveChickenList[count] = i;
				comb(i + 1, count + 1);
				visited[i] = false;
			}		
		}
	}
	private static int findChDistance() {
		int sum = 0;
		for(int i = 0; i < houseList.size(); i++) {
			int houseRow = houseList.get(i).row;
			int houseCol = houseList.get(i).col;
			sum += calc(houseRow, houseCol);
		}
		// sum 변수에 집별 최소 치킨거리의 합이 담겨있음
		return sum;
	}
	
	private static int calc(int row, int col) {
		int chickenDistance = Integer.MAX_VALUE;
		for(int i = 0; i < liveChickenList.length; i++) {
			Point p = chickenList.get(liveChickenList[i]);
			int distance = Math.abs(p.row - row) + Math.abs(p.col - col);
			if(distance < chickenDistance) chickenDistance = distance;
		}
		return chickenDistance;
	}
}
