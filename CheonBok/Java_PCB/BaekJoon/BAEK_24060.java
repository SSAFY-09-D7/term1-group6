import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BAEK_24060 {

	static int cnt = 0;
	static int K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());  
		int[] Narr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			Narr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		System.out.println("현재 인덱스들 = "+0+" "+(N/2)+" "+(N-1));
		Merge(Narr, new int[N], 0, N/2, N-1);
		
		System.out.println("-1");
	}

	private static void Merge(int[] narr, int[] sort, int start, int mid, int end) {
		if (start < end && cnt <= K) {
			System.out.println("현재 인덱스들 = "+start+" "+mid+" "+end);
			int m = (start+end)/2;
			Merge(narr, sort, start, m, mid);
			Merge(narr, sort, m+1, m+1, end);
			
			mergeSort(narr, sort, start, m, end);
		}
	}

	private static void mergeSort(int[] narr, int[] sort, int start, int mid, int end) {
		
		int i = start;
		int j = mid+1;
		int t = 0;
	
		while ( i <= mid && j <= end) {
			if (narr[i] <= narr[j]) {
				sort[t++] = narr[i++];
			}
			else {
				sort[t++] = narr[j++];
			}
		}
		
		while ( i <= mid) {
			sort[t++] = narr[i++];
		}
		
		while ( j <= end) {
			sort[t++] = narr[j++];
			
		}
		
		i = start;
		t = 0;
		while(i <= end) {
			narr[i++] = sort[t++];
			
			cnt += 1;
			if (cnt == K) {
				System.out.println("K = "+K);
				System.out.println("h4 "+narr[i]);
				System.exit(0);
			}
		}
		
		//System.out.println("현재 cnt = "+cnt);
		System.out.println(Arrays.toString(sort));
		System.out.println(Arrays.toString(narr));
		
	}

}
