// SWEA 4012 요리사
import java.io.*;
import java.util.*;
public class SWEA_4012
{
	static int N;
	static int[][] arr;	// 시너지 정보 원본 배열
	static int ret;     // 맛의 차이 최솟값

	public static void main(String[] args) throws NumberFormatException, IOException {
	// ---------------------------------------------------------------------------------
		// 입력 구간
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int Tcase = Integer.parseInt(br.readLine());
		for (int i = 1; i < Tcase+1; i++) {
			N = Integer.parseInt(br.readLine());
			ret = Integer.MAX_VALUE;
			arr = new int[N+1][N+1];
			
			for (int j = 1; j < N+1; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 1; k < N+1; k++) {
					arr[j][k] = Integer.parseInt(st.nextToken());
				}
			}
	// ----------------------------------------------------------------------------------	
			permutation(new boolean[N+1], 1, 0);
			sb.append("#"+i+" "+ret+"\n");
		}
		System.out.println(sb);
	}

	/*
	 *   usemap : A 그룹과 B 그룹의 식재료 구분을 위함 (ex. true = A 그룹, false = B 그룹)
	 *   start  : 재귀에서의 for문 시작 인덱스 = 같은 식재료를 다시 사용하지 않기 위해 조절
	 *   cnt    : 사용한 식재료 수 = 총 N개의 식재료가 있다면 하나의 그룹은 N/2개로 구성. => 식재료 구성이 완료되면 맛의 차이 계산
	 *   
	 * 
	 *   A, B 그룹에 사용되는 식재료는 모두 다르다. (A 그룹에 a 식재료가 사용되었다면 B 그룹에는 사용 불가)
	 *   => 식재료 N개가 있다면 N/2개가 각각 그룹 A와 B의 구성원이다.
	 *   => 그룹은 총 2개이므로 true, false 구분이 가능다 --> usemap에서 A 그룹은 false, B 그룹은 true로 구성한다.
	 *   => cnt를 늘려가면서 구분된 개수를 확인하고, N/2개의 구분이 되었다면 맛의 차이를 검사한다 -> checkTaste Method
	 */
	private static void permutation(boolean[] usemap, int start, int cnt) {
		if (cnt == N/2) {
			checkTaste(usemap);
			return;
		}
		
		for (int i = start; i < N+1; i++) {
			usemap[i] = true;
			permutation(usemap, i+1, cnt+1);
			usemap[i] = false;
		}
	}

	
	/*
	 *   usemap : false로 된 인덱스의 재료는 A 그룹의 재료, true는 B 그룹의 재료
	 *   
	 *   usemap의 인덱스를 하나씩 탐색한다.(1부터 시작. 식재료 1부터 N까지 있다고 가정 - 0번 인덱스 사용 X)
	 *   
	 *   false면 A 그룹의 식재료이다.  처음 찾은 A그룹의 식재료를 a라고 가정한다.
	 *     -> a 식재료의 다음 인덱스부터 다시 탐색한다. 같은 false가 있는 식재료의 인덱스를 찾으면 이들의 맛을 계산한다.
	 *     -> 탐색을 모두 끝냈다면 a를 포함한 식재료의 맛 계산을 마친 것이다.
	 *     -> 가장 밖의 for문을 돌면서 a 이후의 false인 식재료를 찾게 되고, 찾은 식재료를 기준으로 이후의 식재료에서 false인 것을 찾는다.
	 *     
	 *     ex.  [a,      b,      c,      d,      e,      f]   <- 식재료
	 *          [F,      T,      T,      F,      F,      T]   <- 그룹
	 *          
	 *    rnd 1. 시작(F)    --     --      F 찾았으니 계산               <- a와 d의 맛 계산
	 *                            계속 진행 ->      F 찾았으니 계산        <- a와 e의 맛 계산
	 *    
	 *    rnd 2.  X      X        X      시작(F)   F 찾았으니 계산        <- d와 e의 맛 계산
	 *    
	 *    
	 *    rnd 3.  X      X        X       X      시작(F)   --  다른 F가 없으니 계산 X   
	 *     
	 *    true 재료도 위와 같은 방식으로 계산되며 2중 for문을 통해 조합을 구성해 나가는 방식.
	 *     
	 */
	private static void checkTaste(boolean[] usemap) {
		int TasteA = 0;
		int TasteB = 0;
		
		for (int i = 1; i < N+1; i++) {
			// 그룹 A의 구성원이면 같은 구성원들을 이후의 인덱스에서 찾아 맛 계산
			if (usemap[i] == false) {
				for (int fj = i+1; fj < N+1; fj++) {
					if (usemap[fj] == false) TasteA += arr[i][fj] + arr[fj][i];
				}
			}
			
			// 그룹 B의 구성원이면 같은 구성원들을 이후의 인덱스에서 찾아 맛 계산
			if (usemap[i]) {
				for (int tj = i+1; tj < N+1; tj++) {
					if (usemap[tj]) TasteB += arr[i][tj] + arr[tj][i];
				}
			}
		}
		// 맛 차이의 최솟값 갱신
		ret = Math.min(ret, Math.abs(TasteA-TasteB));		
	}
}