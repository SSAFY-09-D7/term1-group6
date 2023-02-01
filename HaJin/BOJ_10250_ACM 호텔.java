import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case<=T; test_case++) {
			
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken()); // 층 수
			int W = Integer.parseInt(st.nextToken()); // 방 수
			int N = Integer.parseInt(st.nextToken()); // 몇 번 째 손님인지
			
			int front = N % H;
			
			int back = (N / H);
			
			if(front != 0)
				back += 1;
			if(front == 0) {
				front = H;
			}

			String frontS = Integer.toString(front);
			String backS = Integer.toString(back);
			String zero = "0";
			String ans = "";
			
			if(backS.length() == 1) {
				ans = frontS+zero+backS;
			}
			else
				ans = frontS+backS;
			
			System.out.println(ans);
		}
		
	}
	
}

