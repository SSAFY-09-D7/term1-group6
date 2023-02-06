import java.io.BufferedReader;
import java.io.InputStreamReader;
public class SWEA_1289
{
    static int minv; // 최솟값을 저장할 변수
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] str;
		String[] arr;
		
		// TestCase for loop
		for (int i = 0; i < N; i++) {
		// ----------------------------------------	
		// 테스트 케이스 때마다 입력되는 값에 따라 변수 초기화
			str = br.readLine().split("");
			minv = Integer.MAX_VALUE;
			
			// 값을 비교하기 위한 arr 초기화.
			arr = new String[str.length];
			for (int k = 0; k < str.length; k++) {
				arr[k] = "0";
			}
		// ----------------------------------------
			Check(arr, str, 0, 0);
			System.out.println("#"+(i+1)+" "+minv);
		}
	}

	private static void Check(String[] arr, String[] str, int idx, int cnt) {
		String tmp; // 바꿀 값 지정
		
		// basis : 인덱스를 모두 검사했다면 return
		if (idx == arr.length) {
			if (minv > cnt) {
				minv = cnt;
			}
			return;
		}
				
		/*
		 * 인덱스 0부터 시작해서 마지막 인덱스까지 1개씩 값을 비교한다.
		 * 1. 비교 결과가 다르다 -> 해당 인덱스부터 마지막 인덱스까지 값을 모두 바꾼다.
		 *    다음 인덱스 비교를 위해 idx+1, 값을 바꿨으니 cnt+1 해서 재귀.
		 * 
		 * 2. 비교 결과가 같다 -> 다음 인덱스 비교를 위해 idx+1만 해서 재귀.
		 */
		if (!arr[idx].equals(str[idx])) {
			
			if (str[idx].equals("0")) {
				tmp = "0";
			}
			else {
				tmp = "1";		
			}
				
			for (int i = idx; i < arr.length; i++) {
				arr[i] = tmp;
			}
			
			// 비교 결과가 다를 경우의 재귀
			Check(arr, str, idx+1, cnt+1);
		}
		else {
			// 비교 결과가 같을 경우의 재귀
			Check(arr, str, idx+1, cnt);
		}
	}
}