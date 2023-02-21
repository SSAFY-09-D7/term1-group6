package algorithm;

import java.io.*;
import java.util.StringTokenizer;

public class Main2 {
	static int N;
	static int[][] map;
	public static void main(String[] args) throws IOException {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	N = Integer.parseInt(br.readLine());
    	map = new int[N][N];
    	
    	for(int i = 0; i < N; i++) {
    		String str = br.readLine();
    		for(int j = 0; j < N; j++) {
    			map[i][j] = str.charAt(j) - '0';
    		}
    	}
    	draw(0, 0, N);
	}
	
	private static void draw(int i, int j, int size) {
		int sum = 0;
		for(int row = i, rowEnd = i + size; row < rowEnd; row++) {
			for(int col = j, colEnd = j + size; col < colEnd; col++) {
				sum += map[row][col];
			}
		}
		
		if(sum == size * size) {
			System.out.print(1);
		}
		else if(sum == 0) {
			System.out.print(0);
		}
		else {
			System.out.print("(");
			int half = size / 2;
			draw(i, j, half);
			draw(i, j + half, half);
			draw(i + half, j , half);
			draw(i + half, j + half, half);
			
			System.out.print(")");
		}
	}
	
}