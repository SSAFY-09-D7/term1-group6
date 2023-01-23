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
			
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			
			int[] jump = {3, 5, 6};
			
			boolean exit = false;
			
			int result = 0;
			
			for(int i = 0; i < count; i++) {
				st = new StringTokenizer(br.readLine());
				int startRow = Integer.parseInt(st.nextToken());
				int startCol = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				
				if(!exit) {
					// 시작 지점이 이미 뛰었던 자리라면
					if(map[startRow][startCol] != 0) {
						result = map[startRow][startCol];
						exit = true;
					}
					
					// 시작 지점이 아무도 안 뛴 자리라면
					else {
						map[startRow][startCol] = i + 1;
						
						
						// 아래로 이동
						if(dir == 1) {
							for(int j = 0; j < jump.length; j++) {
								// 다음에 뛸 자리가 지도 안이라면
								if(startRow + jump[j] < N) {
									if(map[startRow + jump[j]][startCol] == 0) {
										map[startRow + jump[j]][startCol] = i + 1;
									}
									else {
										result = i + 1;
										exit = true;
										break;
									}
								}
							}
						}
						
						// 우로 이동
						if(dir == 2) {
							for(int j = 0; j < jump.length; j++) {
								// 다음에 뛸 자리가 지도 안이라면
								if(startCol + jump[j] < N) {
									if(map[startRow][startCol + jump[j]] == 0) {
										map[startRow][startCol + jump[j]] = i + 1;
									}
									else {
										result = i + 1;
										exit = true;
										break;
									}
								}
							}
						}
					}
				}

			}
			System.out.println("#" + test_case + " " + result);
		}
	}	
}
