import java.io.*;
import java.util.StringTokenizer;
public class BAEK_10972 {
	
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 입력으로 주어진 순열의 다음이 존재하는지 확인
		// Next Permutation 방법을 적용
		boolean isNext = permutation(arr);
		
		// false면 다음 값이 없으니 -1, 있다면 그 순열을 출력
		if (isNext) {
			for (int i = 0; i < N; i++) {
				System.out.print(arr[i]+" ");
			}
		}
		else System.out.println("-1");
		

	}

	private static boolean permutation(int[] arr) {
		// Next Permutation 사용 (오름차순의 모든 순열 목록을 뽑아낼 수 있다)
		// step1. 현재 리스트의 마지막 인덱스에서부터 가장 큰 값의 위치 topidx를 찾는다.
		int topidx = N-1;
		while(topidx > 0 && arr[topidx-1] >= arr[topidx]) --topidx;
		
		// step1의 결과 topidx(가장 큰 값의 인덱스)가 0이 나왔다면 모든 가능한 순열의 마지막임을 의미
		if (topidx == 0) return false;
		
		
		// step2. 가장 큰 값의 인덱스(topidx)를 기준으로 앞의 값보다 큰 값 sideidx을 찾는다 (swap 목적).
		int sideidx = N-1;
		while(sideidx > 0 && arr[topidx-1] >= arr[sideidx]) --sideidx;
		
		// step3. 찾은 sideidx의 값과 topidx 앞의 값을 swap(자리 변경) 한다.
		swap(arr, topidx-1, sideidx);
		
		
		// step4. 가장 큰 값의 인덱스(topidx)를 기준으로 우측의 모든 원소들을 오름차순 정렬한다.
		int idx = N-1;
		while(topidx < idx) {
			swap(arr, topidx++, idx--);
		}
		
		return true;
	}

	// 두 인덱스의 값을 서로 변경
	private static void swap(int[] arr, int i, int sideidx) {
		int tmp = arr[i];
		arr[i] = arr[sideidx];
		arr[sideidx] = tmp;
	}
}