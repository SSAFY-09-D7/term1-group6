import java.util.Scanner;
public class algo_32_jump {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tcase = sc.nextInt();
		
		// Test case loop
		for (int tc = 1; tc < tcase+1; tc++) {
			int row = sc.nextInt();
			int col = sc.nextInt();
			int human = sc.nextInt();
			int[][] arr = new int[row][col];
			
			// map sketch loop
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			int[][] challenger = new int[human][3];
			
			// challenger info loop
			for (int i = 0; i < human; i++) {
				for (int j = 0; j < 3; j++) {
					challenger[i][j] = sc.nextInt();
				}
			}
			
			int danger_v = sc.nextInt();
			
			// Red Room setting loop in map
			for (int i = 0; i < danger_v; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				
				arr[x-1][y-1] = 0;
			}
			
			int total = 0-(human*1000);  // 시작 참가비
			
			// challenge loop
			for (int i = 0; i < challenger.length; i++) {
				int x = challenger[i][0]-1; // start x
				int y = challenger[i][1]-1; // start y
				int value = arr[x][y]; // cell of (x,y)
				
				int direction = value/10;
				int depth = value%10;
				
				// repeat challenge as possible
				for (int j = 0; j < challenger[i][2]; j++) {
					switch (direction) {
						case 1:
							if (y+depth < col) {
								y += depth;
								value = arr[x][y];
								break;
							}
							else {
								value = 0;
								break;
							}
						
						case 2:
							if (x+depth < row) {
								x += depth;
								value = arr[x][y];
								break;
							}
							else {
								value = 0;
								break;
							}
							
						case 3:
							if (y-depth >= 0) {
								y -= depth;
								value = arr[x][y];
								break;
							}
							else {
								value = 0;
								break;
							}
						
						case 4:
							if (x-depth >= 0) {
								x -= depth;
								value = arr[x][y];
								break;
							}
							else {
								value = 0;
								break;
							}
					}
					// update new direction and depth of cell
					direction = value/10; 
					depth = value%10;
				}
				total += (value*100);
			}
			System.out.println("#"+tc+" "+total);
		}
	}
}