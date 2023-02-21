import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	
	static StringBuilder sb = new StringBuilder();
	static int[][] arr = new int[9][9];
	static int[][] zero = new int[85][2];
	static int zeroIdx, zeroCnt;
	static boolean flag = false;
	
	public static void func(int cnt) {
		
		if(cnt == zeroCnt) {
			flag = true;
			
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					sb.append(arr[i][j]).append(" ");
				}
				sb.append("\n");
			}
			
			System.out.println(sb);
			
			return;
		}
		
		
		for(int i=1; i<=9; i++) {
			if(row(zero[cnt][0], i) && col(zero[cnt][1], i) && square(zero[cnt][0], zero[cnt][1], i)) {
				arr[zero[cnt][0]][zero[cnt][1]] = i;
				
				func(cnt+1);
				arr[zero[cnt][0]][zero[cnt][1]] = 0;
				
				if(flag)
					return;
			}
		}
	}
	
	public static boolean row(int i, int k) {
		for(int p = 0; p < 9; p++) {
			if(arr[i][p] == k)
				return false;
		}
		return true;
	}
	
	public static boolean col(int j, int k) {
		for(int p = 0; p < 9; p++) {
			if(arr[p][j] == k)
				return false;
		}
		return true;
	}
	
	public static boolean square(int i,int j, int k) {
		for(int p = (i/3)*3; p < (i/3)*3+3; p++) {
			for(int q = (j/3)*3; q < (j/3)*3+3; q++) {
				if(arr[p][q] == k)
					return false;
			}
		}
		return true;
	}


	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		
		zeroIdx = 0;
		zeroCnt = 0;
		
		for(int i=0; i<9; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 0) {
					zero[zeroIdx][0] = i;
					zero[zeroIdx][1] = j;
					zeroIdx += 1;
					zeroCnt += 1;
				}
			}
		}

		func(0);
	}
}