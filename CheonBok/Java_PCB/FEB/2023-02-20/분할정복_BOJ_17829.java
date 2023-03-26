import java.io.*;
import java.util.*;

public class BAEK_17829 {
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		int N = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1]; // Raw 2-Dimension
		
		// first coordinate = (1,1)
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// final return value by using Divide-Conquer
		int ret = squ4Cehck(1,1, N);
		System.out.println(ret);
	}

	private static int squ4Cehck(int row, int col, int size) {
		// size check for get values (At 2x2 square)
		if (size == 2) {
			List<Integer> arr = new ArrayList<>();
			
			for (int i = row; i <row+size; i++) {
				for (int j = col; j < col+size; j++) {
					arr.add(map[i][j]);
				}
			}
			arr.sort(null);
			return arr.get(2);
		}
		
		int Nsize = size/2; // not size 2 = more divide
		List<Integer> tmparr = new ArrayList<>();
		
		// get the return values from the last divided sector 
		// and choose the second largest of values
		// this result is the one of them used to scale up
		tmparr.add(squ4Cehck(row, col, Nsize));
		tmparr.add(squ4Cehck(row+Nsize, col, Nsize));
		tmparr.add(squ4Cehck(row, col+Nsize, Nsize));
		tmparr.add(squ4Cehck(row+Nsize, col+Nsize, Nsize));
		
		tmparr.sort(null);
		return tmparr.get(2);
	}

}
