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
	static int R, C;
	static char[][] arr;
	static int ans = 0;
	static boolean flag;
	
	static int[] r = {-1, 0, 1};
	static int[] c = {1, 1, 1};
	
	// i, j: 출발
	public static void func(int i, int j) {
		if(flag)
			return;
		
		if(j == C-1) {
			ans += 1;
			flag = true;
			return;
		}
		
		for(int p = 0; p<3; p++) {
			int nextI = i + r[p];
			int nextJ = j + c[p];
			
			if( nextI>=0 && nextI<R && nextJ>=0 && nextJ<C && !flag && arr[nextI][nextJ] == '.') {
				arr[nextI][nextJ] = 'x';
				func(nextI, nextJ);
			}
		}
	}

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[R][C];
		
		for(int i=0; i<R; i++) {
			String s = br.readLine();
			for(int j=0; j<C; j++) {
				arr[i][j] = s.charAt(j);
			}
		}
		
		for(int i=0; i<R; i++) {
			flag = false;
			func(i, 0);
		}
		
		System.out.println(ans);
	}
}