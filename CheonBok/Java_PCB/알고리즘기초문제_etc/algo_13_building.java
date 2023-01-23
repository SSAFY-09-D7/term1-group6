// 알고리즘기초문제 13번 빌딩 (PDF)
import java.util.Arrays;
import java.util.Scanner;
public class algo_13_building {

	public static void main(String[] args) throws Exception{
	Scanner sc = new Scanner(System.in);
		int rnd = sc.nextInt();
		// Test case loop
		for (int r=1; r < rnd+1; r++) {
			
			int N = sc.nextInt();
			String[][] ground = new String[N][N];
			int[][] map = new int[N][N];
			
			// map sketch loop
			for (int i=0; i<N; i++) {
				for (int j = 0; j < N; j++) {
					ground[i][j] = sc.next();
				}			
			}
			
			boolean flag; // false: "G" is around "B" = 2
			int max_value = 0;
			
			// Searching loop (i=idx, j=idy)
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					flag = true;
					// if cell is "B" --> 8-way search.
					if (ground[i][j].equals("B")) {
						if ( flag && (i-1)>=0 && (j-1)>=0 ) {
							flag = Search(ground[i-1][j-1], map, i, j);
						}
						
						if ( flag && (i-1)>=0 && (j+1)<N ) {
							flag = Search(ground[i-1][j+1], map, i, j);
						}
						
						if ( flag && (i+1)<N && (j-1)>=0 ) {
							flag = Search(ground[i+1][j-1], map, i, j);
						}
						
						if ( flag && (i+1)<N && (j+1)<N ) {
							flag = Search(ground[i+1][j+1], map, i, j);
						}
						
						if ( flag && (i-1)>=0 ) {
							flag = Search(ground[i-1][j], map, i, j);
						}
						
						if ( flag && (i+1)<N ) {
							flag = Search(ground[i+1][j], map, i, j);
						}
						
						if ( flag && (j-1)>=0 ) {
							flag = Search(ground[i][j-1], map, i, j);
						}
						
						if ( flag && (j+1)<N ) {
							flag = Search(ground[i][j+1], map, i, j);
						}
						
						if (flag) { // if "G" isn't around "B"
							String[] row = Arrays.copyOf(ground[i], ground[i].length);
							int cnt = 0;
							for(String s : row) { // find "B" in same row.
								if (s.equals("B")) {
									cnt++;
								}
							}
							for (int k = 0; k < N; k++) { // find "B" in same column.
								if (ground[k][j].equals("B")) {
									cnt++;
								}
							}
							map[i][j] = cnt-1; // subtract overlap.
						}
					}
					max_value = Math.max(max_value, map[i][j]); // update return
				}
			}
			System.out.println("#"+r+" "+max_value);
		}
	}
	
	private static boolean Search(String value, int[][] map, int i, int j) {
		if (value.equals("G")) {
			map[i][j] = 2;
			return false;
		}
		else {return true;}
	}
}