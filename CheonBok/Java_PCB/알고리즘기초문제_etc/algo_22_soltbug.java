import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Scanner;
public class algo_22_soltbug {

	public static void main(String[] args) throws IOException{
		//Scanner sc = new Scanner(System.in);
		//int tcase = sc.nextInt();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tcase = Integer.parseInt(br.readLine());
		int row, col, direction, soltbug, Nsize, startx, starty, exist;
		String[] input;
		int[][] move, map;
		boolean flag;
		
		// 0 idx not used
		int[] dx = {0, -1, 1, 0, 0};
		int[] dy = {0, 0, 0, -1, 1};
		
		for (int tc = 1; tc < tcase+1; tc++) {
			input = br.readLine().split(" ");
			Nsize = Integer.parseInt(input[0]);
			soltbug = Integer.parseInt(input[1]);
			exist = soltbug; // exists strider number
			
			map = new int[Nsize][Nsize];
			move = new int[soltbug][3];
			
			// strider insert loop
			for (int i = 0; i < soltbug; i++) { 
				input = br.readLine().split(" ");
				
				startx = Integer.parseInt(input[0]);
				starty = Integer.parseInt(input[1]);
				
				// already exists other strider (case1)
				if (map[startx][starty] == 1) { 
					exist--;
					continue;
				}
				
				move[i][0] = Integer.parseInt(input[0]);
				move[i][1] = Integer.parseInt(input[1]);
				move[i][2] = Integer.parseInt(input[2]);
			}
			
			// strider move loop
			for (int i = 0; i < soltbug; i++) {
				if (move[i] != null) {
					flag = true;
					row = move[i][0];
					col = move[i][1];
					direction = move[i][2];
					
					for (int jump = 3; jump > 0; jump--) {
						row += dx[direction] * jump;
						col += dy[direction] * jump;
						
						// strider dead (case2+3)
						if (row < 0 || row >= Nsize || col < 0 || col >= Nsize || map[row][col] == 1) {
							exist--;
							flag = false;
							break;
						}
					}
					
					map[move[i][0]][move[i][1]] = 0; // strider update
					if (flag) { map[row][col] = 1; } // if strider save
				}
			}
			System.out.println("#"+tc+" "+exist);
		}	
	}
}