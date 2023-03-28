//package Algorithm;

import java.io.*;
import java.util.*;

public class BOJ_1149 {
	static int[] RGB = new int[3];
	static int[] tmp = new int[3];
	static int[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		arr = new int[N][3];
		
		for (int r = 0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 3; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		System.arraycopy(arr[0], 0, tmp, 0, 3);
		
		/*
		 *   arr: 입력으로 주어진 RGB 색칠 값에 대한 2차원 배열
		 *   RGB: 최종으로 얻게되는 총 색칠 비용
		 *   tmp: 최종 RGB 값을 구하기 위해 이전의 비용 값을 저장하는 Memoization
		 * 
		 *   ★ 문제 설정.
		 *     - 색상 선택시 바로 인접한 위아래의 집과 같은 색을 칠할 수 없다.
		 *     ex) N번째 집에서 B색상을 선택하려면 N-1번째 집은 R 또는 G색상을 선택한 상태여야 한다.
		 * 
		 * 
		 * 	  tmp는 색칠한 가장 마지막 집까지의 색칠 비용이 저장되어 있습니다.
		 *    그래서 첫 시작은 첫 집의 RGB값이 그대로 들어갑니다. 
		 *        (왜냐하면 각각의 RGB 값이 첫 집에서 각각의 R, G, B를 선택했을 때의 최소 색칠 비용이기 때문입니다.)
		 *        
		 *        
		 *    다음 턴(for문 시작)부터는 해당 row(r)의 집에서 선택할 수 있는 경우에 대한 최소 색칠비용을 계산합니다.
		 *                                            ↓
		 *    ex) N번째 집에서 R(0번)로 색칠하고자 한다면 N-1번째 집에서는 G(1번) 또는 B(2번)를 선택한 상태여야 합니다.
		 *        tmp에는 N-1번째 집이 R, G, B 중 하나를 선택했을 경우에 최소 색칠 비용이 저장되어 있습니다.
		 *        따라서 N번째 집이 R(0번)을 칠하려면 tmp의 1번(G), 2번(B)인덱스 값 중의 최솟값에 R(0번) 색칠 비용을 더해주면 됩니다.
		 *       
		 */
		
		for (int r = 1; r < N; r++) {
			RGB[0] = Math.min(tmp[1], tmp[2]) + arr[r][0];
			RGB[1] = Math.min(tmp[0], tmp[2]) + arr[r][1];
			RGB[2] = Math.min(tmp[0], tmp[1]) + arr[r][2];
			System.arraycopy(RGB, 0, tmp, 0, 3);
			//System.out.println(Arrays.toString(RGB));
		}
		System.out.println(Math.min(RGB[0], Math.min(RGB[1], RGB[2])));
	}
}
