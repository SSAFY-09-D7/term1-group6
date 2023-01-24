import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class algo_21_soltbug {
	/**
	 * 
	 * @param map : area map
	 * @param bugrow : bug row position
	 * @param bugcol : bug col position
	 * @param i : the number of strider
	 * @return  : i+1 or 0(not found)
	 */
	private static int checkpoint(int[][] map, int bugrow, int bugcol, int i) {
		int r;
		if (map[bugrow][bugcol] == 1) {
			r = i+1;
			return r;
		}
		else { return 0; }
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int Nsize, soltbug, ret, bugrow, bugcol, direction;
		int[][] move, map;
		String[] inputext;
		
		// 0 idx not used
		int[] dx = {0, 1, 0}; 
		int[] dy = {0, 0, 1};
		
		// test case loop
		for (int rnd = 1; rnd < t+1; rnd++) {
			ret = 0; // return
			
			inputext = br.readLine().split(" ");
			Nsize = Integer.parseInt(inputext[0]);
			soltbug = Integer.parseInt(inputext[1]);
			
			map = new int[Nsize][Nsize];
			move = new int[soltbug][3];
			
			/**
			 *  idx 0 : row position
			 *  idx 1 : col position
			 *  idx 2 : move direction
			 */
			for (int i = 0; i < soltbug; i++) {
				inputext = br.readLine().split(" ");
				move[i][0] = Integer.parseInt(inputext[0]);
				move[i][1] = Integer.parseInt(inputext[1]);
				move[i][2] = Integer.parseInt(inputext[2]);
			}
			
			// strider movement start loop
			for (int i = 0; i < soltbug; i++) {
				bugrow = move[i][0];
				bugcol = move[i][1];
				direction = move[i][2];
				
				ret = checkpoint(map, bugrow, bugcol, i); // find case 1
				if (ret != 0) { break; }

				// strider jump loop
				for (int jump = 3; jump > 0; jump--) {
					bugrow += dx[direction] * jump;
					bugcol += dy[direction] * jump;
					
					if (bugrow < Nsize && bugcol < Nsize) {
						ret = checkpoint(map, bugrow, bugcol, i); // find case 2
						if (ret != 0) { break; }
						
						map[bugrow][bugcol] = 1;
					}
					// if strider doesn't move, jump loop quit. 
					else { break; }
				}
				// if find return, movement loop quit.
				if (ret != 0) { break; }
			}
			System.out.println("#"+rnd+" "+ret);
		}
	}
}
