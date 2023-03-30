
// BOJ 1463 1로 만들기  (DP 연습)
import java.io.*;
import java.util.Arrays;

public class BOJ_1463 {
	static int[] check;
	static int T;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		/*
		 *   check int 배열: 정수 X에 다다랐을 때 저장할 이동 최솟값.
		 *   
		 *   1. 최솟값 비교를 위해 초기 값들은 모두 최댓값으로 설정.
		 *   2. 도착하게 될 정수 1의 위치는 0으로 세팅.
		 *   
		 *   -> 정수 1부터 시작하며 역순(x3, x2, +1)으로 정수 X를 찾아나갑니다.
		 * 
		 */
		check = new int[T+1];
		Arrays.fill(check, Integer.MAX_VALUE);
		check[1] = 0;
        
		counting(2);
		System.out.println(check[T]);
	}

	
	/*
	 *   1로 가기위해 사용하는 연산은 3개.
	 *     1) X를 3으로 나누기 (가능하다면)
	 *     2) X를 2로 나누기  (가능하다면)
	 *     3) X에 1 빼기
	 *     
	 *     이 과정을 역순으로 진행합니다.
	 *     ex) 정수 4에 다다르기 위한 최소 이동은 4에 갈 수 있는 경우(정수 2, 정수 3)에서 확인 가능.
	 *     따라서 정수 2와 정수 3에서의 최소 이동 값이 저장되어 있어야 한다.
	 *  
	 */
	
	private static void counting(int t) {
		if (t > T) return;
		check[t] = Math.min(check[t-1]+1, check[t]);
		if (t%3 == 0) check[t] = Math.min(check[t/3]+1, check[t]);
		if (t%2 == 0) check[t] = Math.min(check[t/2]+1, check[t]);
		counting(t+1);
	}
}