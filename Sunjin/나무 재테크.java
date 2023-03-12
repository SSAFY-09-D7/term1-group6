import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static int[][] plusEnergy;
	static int[][] currentEnergy;
	static int[] dRow = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dCol = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	static ArrayList<Point> treeList;
	static class Point implements Comparable<Point>{
		int row, col, age;
		boolean canChangeEnergy;
		public Point(int row, int col, int age, boolean canChangeEnergy) {
			this.row = row;
			this.col = col;
			this.age = age;
			this.canChangeEnergy = canChangeEnergy;
		}
		@Override
		public String toString() {
			return "row : " + row + " col : " + col + "   " + " age : " + age + " canCE : " + canChangeEnergy;
		}
		@Override
		public int compareTo(Main.Point o) {
			return Integer.compare(this.age, o.age);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		plusEnergy = new int[N][N];
		currentEnergy = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				plusEnergy[i][j] = Integer.parseInt(st.nextToken());
				currentEnergy[i][j] = 5;
			}
		}

		treeList = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			Point point = new Point(row, col, age, false);
			
			treeList.add(point);
		}
		
		for(int i = 0; i < K; i++) {
			// 봄
			spring();
			
			// 여름
			summer();
			
			// 가을
			autumn();
			
			// 겨울
			winter();
		}

		System.out.println(treeList.size());
	}
	
	private static void spring() {
//		System.out.println("Start Spring");
//		printTrees();
//		printEnergy();
		Collections.sort(treeList);
		for(Point tree : treeList) {
			if(tree.age <= currentEnergy[tree.row][tree.col]) {
				currentEnergy[tree.row][tree.col] -= tree.age; 
				tree.age += 1;
			}
			// 죽어야 하는 나무
			else {
				tree.canChangeEnergy = true;
			}
		}
	}
	
	private static void summer() {
//		System.out.println("Start Summer");
//		printTrees();
//		printEnergy();
		
		ArrayList<Point> aliveTreeList = new ArrayList<>();
		ArrayList<Point> removeTreeList = new ArrayList<>();
		for(Point now : treeList) {	
			if(now.canChangeEnergy == true) {
				int energy = now.age / 2;
				currentEnergy[now.row][now.col] += energy;
				removeTreeList.add(now);
			}
			else {
				aliveTreeList.add(now);
			}
		}
		
		treeList = new ArrayList<>();
		for(int i = 0; i < aliveTreeList.size(); i++) {
			treeList.add(aliveTreeList.get(i));
		}
	}

	private static void autumn() {
//		System.out.println("Start Autumn");
//		printTrees();
//		printEnergy();
		
		ArrayList<Point> addTreeList = new ArrayList<>();
		
		for(Point now : treeList) {
			if(now.age % 5 == 0) {
				for(int i = 0; i < 8; i++) {
					int nextRow = now.row + dRow[i];
					int nextCol = now.col + dCol[i];
					
					if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) continue;
					
					Point babyTree = new Point(nextRow, nextCol, 1, false);
//					System.out.println(babyTree);
					addTreeList.add(babyTree);
				}
			}
		}
		
		for(Point addTree : addTreeList) {
			treeList.add(addTree);
		}
	}
	
	private static void winter() {
//		System.out.println("Start Winter");
//		printTrees();
//		printEnergy();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				currentEnergy[i][j] += plusEnergy[i][j];
			}
		}
	}
	
	private static void printTrees() {
		for(Point tree : treeList) {
			System.out.println(tree);
		}
	}
	
	private static void printEnergy() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(currentEnergy[i][j] + " ");
			}
			System.out.println();
		}
	}
}
