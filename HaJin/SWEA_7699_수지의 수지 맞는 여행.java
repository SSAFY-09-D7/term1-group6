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

public class Solution{
	
	StringBuilder sb = new StringBuilder();
	static int R, C;
	static int[] row = {-1, 1, 0, 0};
	static int[] col = {0, 0, -1, 1};
	static int cnt = 0;
	
	static int[][] arr;
	static boolean[] visited;

	public static void func(int i, int j, int tmpCnt) {
		
		if(tmpCnt > cnt)
			cnt = tmpCnt;
		
		for(int k=0; k<4; k++) {
			int tmpI = i + row[k];
			int tmpJ = j + col[k];
			
			if(tmpI>=0 && tmpI<R && tmpJ>=0 && tmpJ<C && !visited[arr[tmpI][tmpJ]]) {

				visited[arr[tmpI][tmpJ]] = true;
				func(tmpI, tmpJ, tmpCnt+1);
				visited[arr[tmpI][tmpJ]] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			cnt = 0;
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			arr = new int[R][C];
			visited = new boolean[30];
			
			for(int i = 0; i < R; i++) {
				String s = br.readLine();
				for(int j = 0; j < C; j++) {
					arr[i][j] = s.charAt(j) - 65;
				}
			}

			visited[arr[0][0]] = true;
			func(0, 0, 1);

			System.out.printf("#%d %d\n", test_case, cnt);
		}
	}
}
