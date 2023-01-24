import java.util.Scanner;
public class SWEA_5215 {
	
	static int MAXT; // final return

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		int N, L;
		int[][] info;
		boolean[] visited;
		
		for (int rnd = 1; rnd < test+1; rnd++) {
			MAXT = 0;
			N = sc.nextInt();
			L = sc.nextInt();
			info = new int[N][2];
			visited = new boolean[N];
			
			for (int i = 0; i < N; i++) {
				info[i][0] = sc.nextInt(); // taste
				info[i][1] = sc.nextInt(); // calorie
			}
			
			int calo = 0;
			comb(info, visited, 0, N, L, calo); 
			System.out.println("#"+rnd+" "+MAXT);
		}

	}
	
	// configure able combination.
	private static void comb(int[][] info, boolean[] visited, int start, int N, int L, int calo) {
		if (start >= N) { // if check the last index.
			checkTaste(info, visited, N);
			return;
		}
		
		// Make combination for available calories.
		for (int j = start; j < N; j++) {
			if (calo + info[j][1] <= L) {
				visited[j] = true;  // used
				comb(info, visited, j+1, N, L, calo+info[j][1]);
				visited[j] = false; // not used
			}
			// if calories are over the limits.
			else {
				checkTaste(info, visited, N);
			}
		}
	}

	private static void checkTaste(int[][] info, boolean[] visited, int N) {
		int total = 0;
		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				
				total += info[i][0];
			}
		}
		MAXT = Math.max(MAXT, total);
	}
}
