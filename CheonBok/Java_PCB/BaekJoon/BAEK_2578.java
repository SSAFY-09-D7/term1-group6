// 백준 2578 빙고  (구현, 시뮬레이션)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BAEK_2578 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int nlen = 5*2+2; // all bingo line
		int idx = 0;      // index for bingo line map
		int ret = 0;      // check bingo
		int round = 0;    // return value ( round call )
		String[] token;   // input String line
		int[] countmap = new int[nlen];  // counting numbers called
		
		String[][] map = new String[nlen][5]; // the bingo map includes the all bingo cases
		
		// main bingo map insert loop
		for (int i = idx; i < idx+5; i++) {
			token = br.readLine().split(" ");
			for (int j = 0; j <5; j++) {
				map[i][j] = token[j];
			}
		}
		
		// insert bingo map of vertical cases
		idx += 5;
		int k = 0;
		for (int i = idx; i < idx+5; i++) {
			for (int j = 0; j < 5; j++) {
				map[i][j] = map[j][k];
			}
			k++;
		}
		
		// insert bingo map of diagonal cases 1
		idx += 5;
		k = 0;
		for (int j = 0; j <5; j++) {
			map[idx][j] = map[k][k];
			k++;
		}
		
		// insert bingo map of diagonal cases 2
		idx++;
		k=0;
		for (int i = 0; i < 5; i++) {
			map[idx][i] = map[k][4-k];
			k++;
		}
		
		// 빙고 게임 시작!!
		// bingo start loop (call 5 rows)
		for (int i = 0; i < 5; i++) {
			token = br.readLine().split(" ");
			
			for (String t : token) { // loop for 5 cols
				round++;
				
				for (int j = 0; j < nlen; j++) {
					if (Arrays.asList(map[j]).contains(t)) {
						countmap[j]++;
						//System.out.println("현재 countmap["+j+"] 값 : "+ countmap[j]);
					}
					if (countmap[j] == 5) { 
						ret++; 
						countmap[j]++;
					}
					
					// end of the bingo!!
					if (ret == 3) {
						System.out.println(round);
						System.exit(0);
					}
				}
			}
		}
	}
}
