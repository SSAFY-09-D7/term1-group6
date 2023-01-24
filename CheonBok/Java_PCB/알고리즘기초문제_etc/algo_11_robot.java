//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.Arrays;
import java.util.Scanner;

public class algo_11_robot {

	public static void main(String[] args) {
		// 버퍼 입출력 방식 BufferedReader
		//BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		//String s = bf.readLine();
		//int i = Integer.parseInt(bf.readLine());
		
		Scanner sc = new Scanner(System.in);
		int rnd = sc.nextInt(); // Test case
		
		for (int r=1; r <rnd+1; r++) {
			int N = sc.nextInt();
			char[][] map = new char[N][N];
			sc.nextLine();
			
			for (int i = 0; i < N; i++) { // mapping
				String[] tmp = sc.nextLine().split(" ");
				for (int j=0; j <N; j++) {
					map[i][j] = tmp[j].charAt(0);
				}
			}

			int cnt = 0; // The count of movement
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 'A') { // A element
						// Only Move Right direction
						cnt += Check_Right(map, i, j, N);
					}
					
					else if (map[i][j] == 'B') { // B element
						// Move horizontal direction
						cnt += Check_Right(map, i, j, N);
						cnt += Check_Left(map, i, j);
					}
					
					else if (map[i][j] == 'C') { // C element
						// Move 4-way
						cnt += Check_Right(map, i, j, N);
						cnt += Check_Left(map, i, j);
						cnt += Check_Top(map, i, j);
						cnt += Check_Bottom(map, i, j, N);
					}
				}
			}
			System.out.println("#"+r+" "+cnt);
		}
	}

	// Function Parameter: mapTable, index(x), index(y), sizeof(mapTable)
	private static int Check_Bottom(char[][] map, int i, int j, int maxl) {
		int idx = i+1;
		int cnt = 0;
		while (idx < maxl && map[idx][j] == 'S') {
			cnt++;
			idx++;
		}
		return cnt;
	}

	private static int Check_Top(char[][] map, int i, int j) {
		int idx = i-1;
		int cnt = 0;
		while (idx >= 0 && map[idx][j] == 'S') {
			cnt++;
			idx--;
		}
		return cnt;
	}

	private static int Check_Left(char[][] map, int i, int j) {
		int idx = j-1;
		int cnt = 0;
		while (idx >= 0 && map[i][idx] == 'S') {
			cnt++;
			idx--;
		}
		return cnt;
	}

	private static int Check_Right(char[][] map, int i, int j, int maxl) {
		int idx = j+1;
		int cnt = 0;
		while (idx < maxl && map[i][idx] == 'S') {
			cnt++;
			idx++;
		}
		return cnt;
	}

}
