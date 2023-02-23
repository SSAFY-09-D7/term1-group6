// BOJ 1992 쿼드트리
import java.io.*;
public class BAEK_1992 {
	/* 
	 *  If the one square is not filled with the only one of vectors(1 or 0),
	 *  divide half size of N.
	 *  
	 *  and ReCheck that the square is filled with only one vector.
	 */
	static int[][] map;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		
		map = new int[N+1][N+1];  // Raw 2-dimension
		
		for (int i = 1; i < N+1; i++) {
			String tmp = br.readLine();
			for (int j = 1; j < N+1; j++) {
				map[i][j] = tmp.charAt(j-1) - '0';
			}
		}

		checkTile(1, 1, N);
		System.out.println(sb);
		
	}

	/*
	 *  Check vectors that are configured only one value.
	 *   row  : row coordination
	 *   col  : col coordination
	 *   size : side length (N or divided N by 2)
	 */
	private static void checkTile(int row, int col, int size) {
		int start = map[row][col];
		boolean flg = false;
		
		L: for (int r = row; r < row+size; r++) {
			for (int c = col; c < col+size; c++) {
				if (map[r][c] != start) {
					flg = true;
					break L;
				}
			}
		}
		
		// true : not filled by only one value.  -> divide and ReCheck
		if (flg) {
			int Nsize = size/2;
			sb.append("(");
			checkTile(row, col, Nsize);
			checkTile(row, col+Nsize, Nsize);
			checkTile(row+Nsize, col, Nsize);
			checkTile(row+Nsize, col+Nsize, Nsize);
			sb.append(")");
		}
		else sb.append(start);
	}
}