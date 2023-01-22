//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.Iterator;
import java.io.IOException;
import java.util.Scanner;

public class swea2805 {

	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		
		int test = sc.nextInt();
		int N, mid, ret;
		int[][] map;
		String[] input;
		
		for (int rnd = 1; rnd < test+1; rnd++) {
			ret = 0;
			//N = Integer.parseInt(br.readLine());
			N = sc.nextInt();
			sc.nextLine();
			map = new int[N][N];
			mid = N/2; // Center row (Assume odd rows)
			
			for (int i = 0; i < map.length; i++) {
				//input = br.readLine().split("");
				input = sc.nextLine().split("");
				for (int j = 0; j < input.length; j++) {
					map[i][j] = Integer.parseInt(input[j]);
				}
			}
		
			// Sum of required rows cell without mid row.
			for (int row = 0; row < mid; row++) { 
				for (int col = mid-row; col < mid+row+1 ; col++) {
					ret += map[row][col];
					ret += map[N-1-row][col];
				}
			}
			
			// Sum of mid row.
			for (int col = 0; col < N; col++) {
				ret += map[mid][col];
			}
			System.out.println("#"+rnd+" "+ ret);
		}
	}
}
