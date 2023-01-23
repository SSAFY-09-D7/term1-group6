import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class test {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			int N = Integer.parseInt(br.readLine());
			String[][] map = new String[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = st.nextToken();
				}
			}
			
			int count = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					// A 로봇의 경우의 수 계산
					if(map[i][j].equals("A")) {
						int temp = j;
						while(true) {
							temp++;
							if(temp >= N || temp < 0) {
								break;
							}
							if(map[i][temp].equals("W") || map[i][temp].equals("A") || map[i][temp].equals("B") || map[i][temp].equals("C")) {
								break;
							}
							count++;
						}
					}
					
					// B 로봇의 경우의 수 계산
					if(map[i][j].equals("B")) {
						int temp = j;
						while(true) {
							temp++;
							if(temp >= N || temp < 0) {
								break;
							}
							if(map[i][temp].equals("W") || map[i][temp].equals("A") || map[i][temp].equals("B") || map[i][temp].equals("C")) {
								break;
							}
							count++;
						}
						temp = j;
						while(true) {
							temp--;
							if(temp >= N || temp < 0) {
								break;
							}
							if(map[i][temp].equals("W") || map[i][temp].equals("A") || map[i][temp].equals("B") || map[i][temp].equals("C")) {
								break;
							}
							count++;
						}
					}
					
					// C 로봇의 경우의 수 계산
					if(map[i][j].equals("C")) {
						int temp = j;
						while(true) {
							temp++;
							if(temp >= N || temp < 0) {
								break;
							}
							if(map[i][temp].equals("W") || map[i][temp].equals("A") || map[i][temp].equals("B") || map[i][temp].equals("C")) {
								break;
							}
							count++;
						}
						temp = j;
						while(true) {
							temp--;
							if(temp >= N || temp < 0) {
								break;
							}
							if(map[i][temp].equals("W") || map[i][temp].equals("A") || map[i][temp].equals("B") || map[i][temp].equals("C")) {
								break;
							}
							count++;
						}
						temp = i;
						while(true) {
							temp++;
							if(temp >= N || temp < 0) {
								break;
							}
							if(map[temp][j].equals("W") || map[temp][j].equals("A") || map[temp][j].equals("B") || map[temp][j].equals("C")) {
								break;
							}
							count++;
						}
						while(true) {
							temp--;
							if(temp >= N || temp < 0) {
								break;
							}
							if(map[temp][j].equals("W") || map[temp][j].equals("A") || map[temp][j].equals("B") || map[temp][j].equals("C")) {
								break;
							}
							count++;
						}
					}
				}
			}
			
			System.out.printf("#%d %d\n", test_case, count);
			
		}
	}	
}
