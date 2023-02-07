import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	public static int min_dist;
	public static int N;
	public static int[][] customer;	// 고객 좌표
	public static int[] arr;	// 순열 만들 배열
	public static int[] ans;	// 정답 담을 배열
	public static boolean[] visited; // 방문 확인
	public static int com_x, com_y;	// 회사 좌표
	public static int home_x, home_y; // 집 좌표
	
	public static void func(int cnt) {
		if(cnt == N) {
			// 합 구하고 min_dist 비교
			//System.out.println(Arrays.toString(ans));
			
			
			int tmp_sum = 0;
			for(int i=0; i<N-1; i++) {
				tmp_sum += dist(customer[ans[i]][0], customer[ans[i]][1], customer[ans[i+1]][0], customer[ans[i+1]][1]);
			}
			tmp_sum += dist(com_x, com_y, customer[ans[0]][0], customer[ans[0]][1]);
			tmp_sum += dist(home_x, home_y, customer[ans[N-1]][0], customer[ans[N-1]][1]);
			
			if(tmp_sum < min_dist)
				min_dist = tmp_sum;
				
		}
		
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				ans[cnt] = i;
				func(cnt+1);
				visited[i] = false;
			}
		}
	
	}
	
	public static int dist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2)+Math.abs(y1-y2);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			customer = new int[15][2];
			arr = new int[15];
			ans = new int[15];
			visited = new boolean[15];
			min_dist = 2000;
			
			st = new StringTokenizer(br.readLine());
			com_x = Integer.parseInt(st.nextToken());
			com_y = Integer.parseInt(st.nextToken());
			home_x = Integer.parseInt(st.nextToken());
			home_y = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<N; i++) {
				customer[i][0] = Integer.parseInt(st.nextToken());
				customer[i][1] = Integer.parseInt(st.nextToken());
			}
			
			func(0);
			
			System.out.printf("#%d %d\n", test_case, min_dist);
		}
	}
	
}

