import java.io.*;
import java.util.StringTokenizer;
public class BOJ2357 {
	static int[] minTree;   // Minimum SegmentTree
 	static int[] maxTree;   // Maximum SegmentTree
 	static int[] arr;       // Input Array
 	static int N, M;
 	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //value
		M = Integer.parseInt(st.nextToken()); //Tcase
		
		arr = new int[N+1];     //tree start = 1
		minTree = new int[N*4]; 
		maxTree = new int[N*4];
		
		
		// Input
		for (int n = 1; n <= N; n++) {
			arr[n] = Integer.parseInt(br.readLine());
		}
		
		// the value is not used.
		int l = makeMinTree(1, N, 1); //start=1, end=N, index=1
		int r = makeMaxTree(1, N, 1); // ""
		
		
		// Tcase Input
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());  // start
			int E = Integer.parseInt(st.nextToken());  //   end
			
			/*
			 *  1: start node (root = 1)
			 *  N: end node
			 *  1: arr index  (start= 1)
			 *  S: range start
			 *  E: range end
			 */
			bw.append(findMin(1, N, 1, S, E) + " " + findMax(1, N, 1, S, E) + "\n");
		}
		bw.flush();
		bw.close();
	}

	
	// Check Values in range(Lrange, Rrange)
	/*
	 *  check range start, end.
	 *   -> if Lrange ~ Rrange is not contained between start and end, can't find the correct value.
	 *   
	 *   -> if range(start, end) are in range(Lrange, Rrange),  return value.
	 *   -> if some are included, next step.
	 */
	private static int findMin(int start, int end, int index, int Lrange, int Rrange) {
		if (start > Rrange || end < Lrange) return 1000000001;        // not find
		if (start >= Lrange && end <= Rrange) return minTree[index];  // find Range
		
		int mid = (start+end)/2;
		int left  = findMin(start, mid, index*2,   Lrange, Rrange);
		int right = findMin(mid+1, end, index*2+1, Lrange, Rrange);
		
		return Math.min(left, right);  // some are not contained
	}
	
	
	private static int findMax(int start, int end, int index, int Lrange, int Rrange) {
		if (start > Rrange || end < Lrange) return 0;        // not find
		if (start >= Lrange && end <= Rrange) return maxTree[index];  // find Range
		
		int mid = (start+end)/2;
		int left  = findMax(start, mid, index*2,   Lrange, Rrange);
		int right = findMax(mid+1, end, index*2+1, Lrange, Rrange);
		
		return Math.max(left, right);  // some are not contained
	}
	
	
	
	// Init Minimum Value of range(start, end)
	// index = idx of Array "arr"
	private static int makeMinTree(int start, int end, int index) {
		// the leaf node is same
		if (start == end) {
			return minTree[index] = arr[start];
		}
		
		int   mid = (start+end)/2;  //middle index
		int  left = makeMinTree(start, mid, index*2);   // left node
		int right = makeMinTree(mid+1, end, index*2+1); //right node
		return minTree[index] = Math.min(left, right);
	}
	
	
	private static int makeMaxTree(int start, int end, int index) {
		if (start == end) {
			return maxTree[index] = arr[start];
		}
		
		// the Maximum value in left or right part (Recursive).
		int   mid = (start+end)/2;
		int  left = makeMaxTree(start, mid, index*2);
		int right = makeMaxTree(mid+1, end, index*2+1);
		return maxTree[index] = Math.max(left, right);
	}
	

}



// 테스트 출력
//// MinTree
//int cnt = 0;
//int rnd = 1;
//System.out.println("MinTree Graph");
//for (int i = 1; i < minTree.length; i++) {
//	if(cnt==rnd) {
//		cnt = 0;
//		rnd++;
//		System.out.println();
//	}
//	System.out.print(minTree[i] + " ");
//	cnt++;
//	
//}
//System.out.println();
//
//cnt = 0;
//rnd = 1;
//System.out.println("MaxTree Graph");
//for (int i = 1; i < maxTree.length; i++) {
//	if(cnt==rnd) {
//		cnt = 0;
//		rnd++;
//		System.out.println();
//	}
//	System.out.print(maxTree[i] + " ");
//	cnt++;
//}
//System.out.println();
//
//
//System.out.println(Arrays.toString(arr));