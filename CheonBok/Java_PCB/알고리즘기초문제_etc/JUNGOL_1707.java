import java.util.Scanner;

public class JUNGOL_1707 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] map = new int[N][N];
		int flag = 1; // 1:right, 2:down, 3:left, 4:up
		
		int x = 0; // idx
		int y = 0; 
		
		for (int i = 1; i < N*N+1; i++) {
			map[x][y] = i;
			
			// down move
			if (flag == 1) {
				if (y+1 >= N || map[x][y+1] != 0) {
					x++;
					flag = 2; // turn down
				}
				else { y++; }
			}
			
			// down move
			else if (flag == 2) {
				if (x+1 >= N || map[x+1][y] != 0) {
					y--;
					flag = 3; // turn left
				}
				else { x++; }
			}
			
			// left move
			else if (flag == 3) {
				if (y-1 < 0 || map[x][y-1] != 0) {
					x--;
					flag = 4; // turn up
				}
				else { y--; }
			}
			
			// up move
			else if (flag == 4) {
				if (x-1 < 0 || map[x-1][y] != 0) {
					y++;
					flag = 1; // turn right
				}
				else { x--; }
			}
		}
		
		for (int[] is : map) {
			for (int i = 0; i < is.length; i++) {
				System.out.print(is[i]+" ");
			}
			System.out.println();
		}
	}
}
