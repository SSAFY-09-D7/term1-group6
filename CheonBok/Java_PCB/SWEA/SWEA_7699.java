// SWEA 7699 수지의 수지 맞는 여행
import java.io.*;
import java.util.StringTokenizer;
public class SWEA_7699
{
	// 같은 것을 안 본다 = 중복 방지
	// 4방 탐색을 해야 한다.
	// 1행 1열이 시작이다.
	static boolean[] visitAlpha;  		// 명물은 한 번만 본다. (명물은 A~Z)
	static Character[][] arr;	  		// 원본 배열
	static int[] dx = {-1, 1, 0, 0};  	// 4방 탐색 delta
	static int[] dy = {0, 0, -1, 1};
	static int R, C;
	static int ret;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int Tcase = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < Tcase; i++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());  // row
			C = Integer.parseInt(st.nextToken());  // col
			
			arr = new Character[R+1][C+1];  // 문제 : "이 섬은 1행 1열로 시작"
			visitAlpha = new boolean[26];   // 명물은 A~Z이므로 26크기의 배열
			
			// 각각의 Alpha 입력 구간
			for (int r = 1; r < R+1; r++) {
				String tmp = br.readLine();
				for (int c = 1; c < C+1; c++) {
					arr[r][c] = tmp.charAt(c-1);
				}
			}
			
			ret = 0;
			dfs(1, 1); // 수지가 있는 위치 (1행1열)부터 dfs로 명물 여행 시작
			
			System.out.println("#"+(i+1)+" "+ret);
			
		}
	}

	private static void dfs(int row, int col) {
		// 인덱스를 벗어나는 경우 return
		if (row < 1 || row > R || col < 1 || col > R) {
			return;
		}
		
		// 이미 방문한 명물이다. (갈 수 없음)
		int Aidx = arr[row][col] - 'A';
		if (visitAlpha[Aidx]) return;
		

		// 방문한 명물 중복 여행 방지.
		// 명물은 대문자 alphabet이므로 해당 alphabet 위치에 true로 둔다.
		visitAlpha[Aidx] = true;
	
		// 4방 탐색
		for (int i = 0; i < 4; i++) {
			int start = row + dx[i];
			int end = col + dy[i];
			
			// 인접한 곳을 계속 탐색한다.
			dfs(start, end);
		}
		
		int alen = 0;
		// 가능한 깊이까지 탐색한 결과 -> 방문한 곳은 true 이므로 true의 개수를 계산
		for (int j = 0; j < 26; j++) {
			if (visitAlpha[j]) {
				alen++;
			}
		}
		
		// 명물을 더 많이 봤다면 갱신
		ret = Math.max(ret, alen);
		// 다른 경우에 대해서도 탐색할 수 있도록 방문 기록을 초기화
		visitAlpha[Aidx] = false;
	}
}


// 2차원 출력 sample
//for (int r = 0; r < R; r++) {
//for (int c = 0; c < C; c++) {
//	System.out.print(arr[r][c]+" ");
//}
//System.out.println();
//}
