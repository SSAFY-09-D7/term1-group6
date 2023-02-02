package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BAEK_17136 {
	
	static int total = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int b = 10;
		int[] paper = {0,5,5,5,5,5};
		
		String[][] board = new String[b][b];
		
		for (int i = 0; i < b; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < b; j++) {
				String tmp = st.nextToken();
				board[i][j] = tmp;
			}
		}
	// ---------------------------------------------------------------------------	
		
		// recursive 2-dimension handling
		search(board, 0, 0, paper, 0);
		
		if (total == Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(total);
		
		
//		for (String[] strings : board) {
//			System.out.println(Arrays.toString(strings));
//		}
		
	}

	private static void search(String[][] board, int row, int col, int[] paper, int cnt) {
		// 순회가 끝나는 경우 : 1. 마지막 항목까지 탐색했을 때 (정상 종료),
		// 2. 색종이(paper)를 모두 사용해버려서 더 이상 붙일 수 없을 때 (예외 발생)
		// col 기준으로 순회하는데, 인덱스를 벗어나면 행(row)을 다음으로 넘기고 col를 0으로 두어 탐색 재시작.
		
//		if (remain == 0) {
//			System.out.println("색종이로 모두 덮었다! 출력 = "+cnt+" "+remain+ "row,col "+ row+" "+col);
//			if (total > cnt) total = cnt;
//			return;
//		}
		
		
		// 마지막까지 탐색 했다면 결과 반영 (마지막까지 탐색 했다는 것은 사용 가능한 색종이로 이전의 "1"들을 모두 덮었다는 의미)
		if (row > 9 && col == 0) {
			if (total > cnt) total = cnt;
			return;
		}
		
		// 열 탐색을 모두 했다면 다음 행으로 넘어가 탐색
		if (col > 9) {
			search(board, row+1, 0, paper, cnt);
			return;
		}
		
		
		if (board[row][col].equals("1")) { // 해당 값이 "1"이라면 -> 1x1 ~ 5x5 가능한 것 모두 탐색
			boolean flag;

			for (int size = 0; size < 5; size++) {  // 1x1 부터 5x5까지 확인
				// 예외 1. 남는 색종이가 없다면 해당 경우는 탐색할 필요가 없다! -> 다음으로 넘기기
				if (paper[size+1] == 0) continue;
				
				// 예외 2. 색종이 사이즈로 붙일 범위가 정해진 인덱스를 벗어난다! -> 이후의 과정은 볼 필요가 없으니 break
				if (row+size > 9 || col+size > 9) break;
				
				// 예외 3. 색종이 사이즈만큼 값들을 보았는데, "0"이 포함되어 있다면 적용할 수 없다!
				// 색종이 적용이 가능한지 아닌지를 flag로 표현
				flag = true;
				for (int i = row; i < row+size+1; i++) {
					for (int j = col; j < col+size+1; j++) {
						if (board[i][j].equals("0")) {
							flag = false;
							break;
						}
					}
					if (!flag) break;
				}
				
				// flag가 true면 해당 size의 색종이로 덮을 수 있으니 해당 범위를 0으로 바꿔서 다음 조회 때 접근 못하게 한다.
				if (flag) {
					for (int i = row; i < row+size+1; i++) {
						for (int j = col; j < col+size+1; j++) {
							board[i][j] = "0";
						}
					}					
					paper[size+1] -= 1; // 사용한 색종이 빼기
					search(board, row, col+1, paper, cnt+1);  // 다음 순회 인덱스를 넣어 재귀 (사용한 색종이 개수 cnt를 1 더해줌)
					
					// 이곳에 도달했다는 것은 위의 search 재귀를 모두 돌아서 나왔다는의미
					// 이전의 재귀 순회를 마치고 나왔으므로 새로운 경우에 대한 탐색을 진행하기 위해 이전에 "0"으로 바꾼 값들을 복원한다.
					for (int i = row; i < row+size+1; i++) {
						for (int j = col; j < col+size+1; j++) {
							board[i][j] = "1";
						}
					}
					paper[size+1] += 1;
				}
				
			}
				
		}
		else{ // 다음 인덱스로 넘어가서 계속 검사
			search(board, row, col+1, paper, cnt);
		}
	}
}



//if (size+1 == 5) {
//for (String[] a : board) {
//	System.out.println(Arrays.toString(a));
//}
//}

//System.out.println();
