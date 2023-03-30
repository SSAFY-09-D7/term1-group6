import java.io.*;
import java.util.*;
// SWEA 5656 벽돌깨기
/*
	1. 벽돌은 위에서 떨어지는 구슬로 제거 가능. (떨어지는 기준은 열)
		1-1. 벽돌의 연쇄 제거 조건이 있기 때문에, 벽돌을 제거하는 순서에 따라 그 결과는 달라진다.
		     따라서 순서에 의존하는 경우의 수인 "순열"을 사용해 떨어지는 구슬의 모든 순서에 대한 경우를 계산한다.
		1-2. 구슬은 같은 곳에 N만큼 떨어뜨릴 수 있다 = 같은 값을 순열의 구성요소로 포함시킬 수 있다. = "중복 순열"
		  
	2. 각 벽돌에 적힌 범위만큼 추가 제거가 가능 (각 벽돌의 제거 가능 범위만큼 추가 탐색 필요 -> BFS)
	
	3. 중복순열에서 추출된 모든 경우에 대해 벽돌을 제거하고, 남은 벽돌을 계산해야 하므로 하나의 2차원 배열에서 모두 다루긴 힘들다.
	   -> 각 경우마다 2차원 배열을 초기화해야 하므로 모든 경우에 대한 복사본을 만들어서 계산한다.
 */
public class SWEA_5656 {
	// BFS용 제거될 벽돌의 좌표 정보 (x:행, y:열, w:추가 제거 가능 범위)
	static class Node {
		int x, y, w;

		public Node(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}
	}
	
	static ArrayList<int[]> marbles;  // 배치할 수 있는 중복 순열 결과
	static int[][] Frame;             // 원본 벽돌 맵
	static int N, W, H;               
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int ret;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <=T; i++) {
		// --------------------------------------------------------------
			// 입력 구간
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			// 원본 배열 값 입력
			Frame = new int[H][W];
			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < W; c++) {
					Frame[r][c] = Integer.parseInt(st.nextToken());
				}
			}
		// ---------------------------------------------------------------
			
			marbles = new ArrayList();  // 가능한 모든 "중복 순열" 결과 저장
			Permutation(0, new int[N]); // 중복순열 생성기
			
			// 모든 경우의 수를 하나씩 꺼내서(marbles) 벽돌깨기 진행
			ret = Integer.MAX_VALUE;
			for (int rnd = 0; rnd < marbles.size(); rnd++) {  
				int[] RoundMarble = marbles.get(rnd); 
				
				// 매번 벽돌깨기를 할 때마다 새로운 복사본 2차원 배열을 만든다.
				int[][] CopyFrame = new int[H][W];
				for (int r = 0; r < H; r++) {
					System.arraycopy(Frame[r], 0, CopyFrame[r], 0, W);
				}
				
//				for (int[] js : CopyFrame) {
//					System.out.println(Arrays.toString(js));
//				}
//				System.out.println();
				
				
				// 벽돌깨기 진행
				StoneBreak(RoundMarble, CopyFrame);
				
				// 벽돌깨기 결과로 나온 최종 결과의 벽돌 개수를 센다.
				int total = 0;
				for (int r = 0; r < H; r++) {
					for (int c = 0; c < W; c++) {
						if (CopyFrame[r][c] != 0) total++;
					}
				}
				
				// 남은 벽돌 최솟값 갱신
				if (ret > total) ret = total;
			}
			System.out.println("#"+i+" "+ret);
		}
	}

	
	private static void StoneBreak(int[] roundMarble, int[][] copyFrame) {
		//System.out.println(Arrays.toString(roundMarble));
		Queue<Node> queue = new LinkedList<>();
		
		// 각 열마다 구슬을 떨어뜨려 BFS를 통해 연쇄 부수기 진행
		for (int col : roundMarble) {
			
			Node start = findStart(col, copyFrame);  // 처음 부서지는 벽돌
			if (start == null) break;                // 더 이상 해당 열에 제거할 벽돌이 없으면 탈출
			copyFrame[start.x][start.y] = 0;         // 제거된 벽돌의 위치는 0
			
			queue.add(start);
			while(!queue.isEmpty()) {
				Node target = queue.poll();          // 제거할 벽돌
				
				// 범위에 대한 4방향 검사
				for (int d = 0; d < 4; d++) {
					int r = target.x;
					int c = target.y;
					
					for (int rnd = 1; rnd < target.w; rnd++) {
						r += dx[d];
						c += dy[d];

						if (r<0 || r>=H || c<0 || c>=W) break;
						
						// 추가 제거되는 범위에 벽돌이 있다면 해당 벽돌에서의 연쇄 제거를 확인해야 한다.
						// 따라서 해당 벽돌에 대한 정보 (좌표, 제거 가능 범위)를 queue에 담는다.
						if (copyFrame[r][c] != 0) {
							queue.add(new Node(r, c, copyFrame[r][c]));
							copyFrame[r][c] = 0;
						}
					}
				}
			}
			// 한 번의 벽돌 제거로 연쇄적인 처리가 완료되었다면 이제 빈 공간을 채운다.
			merge(copyFrame);
		}
	}

	private static void merge(int[][] copyFrame) {
		for (int c = 0; c < W; c++) {
			int r = H-1;
			while (r > 0) {
				if (copyFrame[r][c] == 0) {
					int tmp = 1;
					
					while (true) {
						if (r-tmp < 0) break;
						if (copyFrame[r-tmp][c] != 0) {
							copyFrame[r][c] = copyFrame[r-tmp][c];
							copyFrame[r-tmp][c] = 0;
							break;
						}
						tmp++;
					}
				}
				r--;
			}
		}
	}

	// 제시된 열에서 제거되는 벽돌의 좌표와 제거 가능한 범위를 반환 받는다.
	// 제거 가능한 벽돌이 없다면 null을 반환.
	private static Node findStart(int col, int[][] copyFrame) {
		for (int r = 0; r < H; r++) {
			if (r == 0 && copyFrame[r][col] != 0) return new Node(r, col, copyFrame[r][col]);
			if (copyFrame[r][col] != 0 && copyFrame[r-1][col] == 0) return new Node(r, col, copyFrame[r][col]);
		}
		return null;
	}

	
	// 전체 열 중에서 선택가능한 모든 중복 순열을 생성한다.
	private static void Permutation(int cnt, int[] used) {
		if (cnt == N) {
			//System.out.println(Arrays.toString(used));
			marbles.add(Arrays.copyOf(used, cnt));
			return;
		}
		
		for (int idx = 0; idx < W; idx++) {
			used[cnt] = idx;
			Permutation(cnt+1, used);
		}
	}
}
