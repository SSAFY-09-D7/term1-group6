import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int minusCount, plusCount, zeroCount;
	public static void main(String[] args) throws IOException {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	N = Integer.parseInt(br.readLine());
    	map = new int[N][N];
    	
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < N; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	find(0, 0, N);
    	System.out.println(minusCount);
    	System.out.println(zeroCount);
    	System.out.println(plusCount);
    	
	}
	private static void find(int row, int col, int size) {
		int plus = 0;
		int minus = 0;
		int zero = 0;
		for(int i = row, rowEnd = row + size; i < rowEnd; i++) {
			for(int j = col, colEnd = col + size; j < colEnd; j++) {
				if(map[i][j] == 1) plus++;
				if(map[i][j] == -1) minus++;
				if(map[i][j] == 0) zero++;
			}
		}
		
		if(plus == size * size) {
			plusCount++;
		}
		else if(minus == size * size) {
			minusCount++;
		}
		else if(zero == size * size) {
			zeroCount++;
		}
		else {
			int nextSize = size / 3;
			
			find(row, col, nextSize);
			find(row, col + nextSize, nextSize);
			find(row, col + nextSize * 2, nextSize);
			find(row + nextSize, col, nextSize);
			find(row + nextSize, col + nextSize, nextSize);
			find(row + nextSize, col + nextSize * 2, nextSize);
			find(row + nextSize * 2, col, nextSize);
			find(row + nextSize * 2, col + nextSize, nextSize);
			find(row + nextSize * 2, col + nextSize * 2, nextSize);
		}
		
	}	

}