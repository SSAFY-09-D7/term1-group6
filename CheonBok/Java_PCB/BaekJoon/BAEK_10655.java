import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BAEK_10655 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tcase = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] arr = new int[tcase][2];
		int ret = Integer.MAX_VALUE;
		
		for (int i = 0; i < tcase; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int total = 0; // 모든 노드를 경유했을 때 거리
		for (int i = 0; i < tcase-1; i++) {
			int ax = Math.abs(arr[i][0] - arr[i+1][0]);
			int ay = Math.abs(arr[i][1] - arr[i+1][1]);
			
			total += ax+ay;
		}
		
	// ----------------------------------------------------------------------------------	
		
		int tmp = total; // total 값 재사용을 위한 변수
		int ax, ay; // 인접 노드 간의 거리
		
		// 한 칸 건너 뛸 수 있는 모든 경우에 대한 탐색
		for (int i = 0; i < tcase-2; i++) {
			ax = Math.abs(arr[i][0] - arr[i+2][0]);
			ay = Math.abs(arr[i][1] - arr[i+2][1]);
			
			tmp += ax+ay;
			
			// 한 칸 뛰었을 때 버려지는 노드를 경유하는 모든 거리 제거
			for (int j = i; j < i+2; j++) {
				ax = Math.abs(arr[j][0] - arr[j+1][0]);
				ay = Math.abs(arr[j][1] - arr[j+1][1]);
				
				tmp -= (ax+ay);
			}
			
			ret = Math.min(ret, tmp);
			tmp = total;
		}
		System.out.println(ret);
	}
}
