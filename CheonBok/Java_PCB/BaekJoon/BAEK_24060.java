import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BAEK_24060 { 

	static int cnt = 0;
	static int K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // the count of elements
		K = Integer.parseInt(st.nextToken());     // the value of needs
		int[] Narr = new int[N]; // Arrays of elements
		
		// append elements
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			Narr[i] = Integer.parseInt(st.nextToken());
		}
		
		// elements array, sorted array, start idx, end idx
		Merge(Narr, new int[N], 0, N-1);
		
		// out of count
		System.out.println("-1");
	}

	
	
	private static void Merge(int[] narr, int[] sort, int start, int end) {
		int mid = (start+end)/2; // middle index
		
		// if start >= end, all elements is sorted
		if (start < end) {
			Merge(narr, sort, start, mid); // Left Side
			Merge(narr, sort, mid+1, end); // Right Side
			
			// Merge Sort Method
			mergeSort(narr, sort, start, mid, end);
		}
	}

	// 
	private static void mergeSort(int[] narr, int[] sort, int start, int mid, int end) {
		int s = start;
		int m = mid+1;
		int t = 0;
	
		// Compare Left side elements and Right side elements
		// if left > right = right element is added and index ++
		// else -> left element is added and index ++
		while ( s <= mid && m <= end) {
			if (narr[s] <= narr[m]) {
				sort[t++] = narr[s++];
			}
			else {
				sort[t++] = narr[m++];
			}
		}
		
		// if left elements is remained
		while ( s <= mid) {
			sort[t++] = narr[s++];
		}
		
		
		// if right elements is remained
		while ( m <= end) {
			sort[t++] = narr[m++];
			
		}
		
		// Update the located element of index (start to end)
		s = start;
		t = 0;
		while(s <= end) {
			narr[s++] = sort[t++];
			
			// elements searching = add count
			cnt += 1;
			if (cnt == K) {
				System.out.println(narr[s-1]);
				System.exit(0);
			}
		}
	}
}
