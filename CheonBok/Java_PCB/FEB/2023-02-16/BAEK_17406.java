import java.io.*;
import java.util.*;
public class BAEK_17406 {
	/*
	 *   point : 회전 연산에 대한 정보를 담는 class
	 *   	 x : r(타겟 행 좌표)
	 *   	 y : c(타겟 열 좌표)
	 *   depth : s(타겟 좌표를 기점으로 회전하는 깊이)
	 */	
	static class point{
		int x,y,depth;
		
		public point(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
	}
	
	static Queue<Integer> queue = new LinkedList<>();	// 회전 구간의 모든 원소를 담아서 한 번에 처리할 Queue
	static point[] pointQ;		// 회전 연산에 대한 point 객체 정보를 담는 point 배열
	static int[] variable;		// 회전 연산의 순서를 정의하는 순열 배열
	static int[][] arr;			// 원본 배열
	static int[][] copyarr;		// 경우에 따른 회전 연산을 수행하기 위한 복사본 배열
	static int N, M, K;
	static int ret = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
	// ----------------------------------------------------------------------------------
		// 원본 배열 입력 구간
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());  // 행 수 row
		M = Integer.parseInt(st.nextToken());  // 열 수 col
		K = Integer.parseInt(st.nextToken());  // 회전 연산 수
		
		// 시작 인덱스를 (1,1)로 세팅
		arr = new int[N+1][M+1];
		pointQ = new point[K];   // 사용할 수 있는 회전 연산 담아 둠
		variable = new int[K];   // 회전 연산의 순서를 담을 순열
		
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < M+1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	// -----------------------------------------------------------------------------------
		// 연산 정보 입력 구간
		for (int r = 0; r < K; r++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int depth = Integer.parseInt(st.nextToken());
			
			pointQ[r] = new point(x, y, depth);
		}	
	// ------------------------------------------------------------------------------------
		
		
		configuration(0, new boolean[K]);
		
		System.out.println(ret);
	}

	
	private static void makeCopyArr() {
		copyarr = new int[N+1][M+1];
		for (int i = 1; i < N+1; i++) {
			System.arraycopy(arr[i], 0, copyarr[i], 0, M+1);
		}
	}

	
	/*
	 *   configuration(int cnt, boolean[] used)
	 *   cnt  : 순열 배열(variable)에 값이 들어간 개수 = return 조건에 사용
	 *   used : 같은 원소에 대한 중복을 방지하기 위한 검사 배열
	 *   
	 *   pointQ는 입력으로 제시된 모든 연산에 대한 정보를 담아둔 point 배열이며
	 *   variable은 pointQ에서 연산을 꺼낼 인덱스 순서를 정의한 배열.
	 *   
	 *    같은 인덱스에 대한 중복 저장을 방지하기 위해 used에서 확인 처리 하며
	 *    순열 구성이 모두 끝났다면 (명령어 처리 순서 구성을 완료했다면) 다음의 연산들을 수행한다.
	 *    
	 *    1. makeCopyArr() : 원본 배열인 arr을 회전 연산에 그대로 사용하면, 다양한 경우에 대한 처리 과정에서 오류를 범한다.
	 *                       따라서 원본 배열을 그대로 복사한 copyarr을 만들어 회전 연산과 최솟값 계산에 활용한다.
	 *    
	 *    2. rotation() : 타겟 정보를 매개 변수로 받아 (x, y, depth) 회전 연산 수행  => 결과는 copyarr에 반영된다.
	 *    
	 *    3. calculation() : rotation() 결과로 나온 copyarr을 바탕으로 행 값들의 합계를 계산한다.
	 *                       행 값들의 계산 결과는 최종 출력 값(ret)과 비교해 더 작은 값으로 업데이트한다.
	 */
	private static void configuration(int cnt, boolean[] used) {
		if (cnt == K) {
			makeCopyArr(); // 회전을 위한 복사 배열 생성
			
			//variable에 정의된 순서대로 회전 연산 수행.   
			for (int i = 0; i < K; i++) {
				point tmp = pointQ[variable[i]];
				rotation(tmp.x, tmp.y, tmp.depth);
			}
			calculation(); // 행 값들 계산 후 최종 결과 반영
			return;
		}
		
		// 순열 구성 과정 (used로 중복 방지)
		for (int i = 0; i < K; i++) {
			if (!used[i]) {
				used[i] = true;
				variable[cnt] = i;
				configuration(cnt+1, used);
				used[i] = false;
			}	
		}
	}

	/*
	 *   회전 연산(rotation)을 끝낸 결과인 copyarr을 바탕으로
	 *   행 합계를 계산하는 Method
	 */
	private static void calculation() {
		int size;
		for (int row = 1; row < N+1; row++) {
			size = 0;
			for (int col = 1; col < M+1; col++) {
				size += copyarr[row][col];
			}
			//System.out.println("행 연산 결과 = "+ size);
			ret = Math.min(ret, size);
		}
		
		//System.out.println("회전 후 연산 결과 = "+ret);
		
	}

	/*
	 *  회전 연산을 수행하는 Method
	 *  depth = 회전 구간의 깊이 => 타겟이 [3,4]라면 depth 1일 때 회전 시작점은 [2,3], depth 2일 때 시작점은 [1,2]가 된다.
	 *  
	 *  회전 구간 예시
	 *  2 3 2 5 6
	 *  8 7 2 1 3
	 *  2 3 @ 4 5
	 *  4 5 1 1 1
	 *  3 2 1 4 3  에서 @(3,3)이 타겟 인덱스 좌표이며 depth는 2라고 가정. => 회전은 2개의 구간에서 일어나며 시작 좌표는 [2,2], [1,1]이다.
	 *  
	 *  Queue에 회전 구간에 대한 정보를 순서대로 담아둔다.
	 *  [1,1]에서 시작하는 회전 구간은 Queue에  [7, 3, 5, 1, 1, 4, 1, 2] 가 저장된다.
	 *  N번 회전한다고 하면 Queue에서 N번 만큼 poll하고, poll한 값을 뒤에 offer하면 된다. (문제에서는 1번만 회전함)
	 *  
	 *  -> 1번 회전했을 때 [3, 5, 1, 1, 4, 1, 2, 7]  (7이 맨 뒤로 옮겨짐)
	 *  Queue에 값을 넣었던 순서대로 poll해서 회전 구간의 인덱스 위치에 값을 업데이트 하면 회전 종료.
	 *
	 *  위와 같은 회전을 depth만큼 한다.
	 */
	private static void rotation(int x, int y, int depth) {
		for (int i = 1; i < depth+1; i++) {
			int size = i * 2 + 1;
			int startx = x-i;
			int starty = y-i;
			int endx = x+i;
			int endy = y+i;
			
			// 회전 구간의 좌측 열 값 담기
			for (int j = startx; j < startx+size; j++) {
				queue.offer(copyarr[j][starty]);
			}
			
			// 회전 구간의 하단 행 값 담기
			for (int j = starty+1; j < endy+1; j++) {
				queue.offer(copyarr[endx][j]);
			}
			
			// 회전 구간의 우측 열 값 담기
			for (int j = endx-1; j > startx ; j--) {
				queue.offer(copyarr[j][endy]);
			}
			
			
			// 회전 구간의 상단 행 값 담기
			for (int j = endy; j > starty ; j--) {
				queue.offer(copyarr[startx][j]);
			}
			
			// 한 번 이동
			queue.offer(queue.poll());
			
			// 회전 구간의 좌측 열 값 업데이트
			for (int j = startx; j < startx+size; j++) {
				copyarr[j][starty] = queue.poll();
			}
			
			// 회전 구간의 하단 행 값 업데이트
			for (int j = starty+1; j < endy+1; j++) {
				copyarr[endx][j] = queue.poll();
			}
			
			// 회전 구간의 우측 열 값 업데이트
			for (int j = endx-1; j > startx; j--) {
				copyarr[j][endy] = queue.poll();
			}
			
			
			// 회전 구간의 상단 행 값 업데이트
			for (int j = endy; j > starty; j--) {
				copyarr[startx][j] = queue.poll();
			}
		}
		// 출력 test
//		for (int[] js : copyarr) {
//			System.out.println(Arrays.toString(js));
//		}
//		System.out.println();
	}
}

// 2차원 배열 출력 Sample

//for (int[] js : arr) {
//System.out.println(Arrays.toString(js));
//}
//System.out.println();
