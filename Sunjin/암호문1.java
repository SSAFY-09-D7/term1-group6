
import java.io.*;
import java.util.*;


public class Solution{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = 10;
		for(int testCase = 1; testCase <= T; testCase++) {
			
			int N = Integer.parseInt(br.readLine());
			
			LinkedList<Integer> list = new LinkedList<Integer>();
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			int O = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < O; i++) {
				// 삽입을 위한 I 버리기
				st.nextToken();
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				int[] arr = new int[y];
				for(int j = 0; j < y; j++) {
					list.add(x + j, Integer.parseInt(st.nextToken()));
				}	
			}
			
			sb.append("#" + testCase + " ");
			for(int i = 0; i < 10; i++) {
				sb.append(list.get(i) + " ");
			}
			
			sb.append("\n");
		}
		System.out.println(sb);
	}
}