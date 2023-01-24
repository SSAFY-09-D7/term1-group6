// SWEA 4615 "재미있는 오셀로 게임"
import java.util.Scanner;
public class SWEA_4615
{
	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	static int N, M, type;
	
	private static void reverseLoop(int[][] map, int x, int y, int t, int direction) {
		int cnt = 0;
		int tmpx = x;
		int tmpy = y;
		boolean flag = true;
		
		while(true) {
			tmpx += dx[direction];
			tmpy += dy[direction];
			
			// don't reverse case
			if (tmpx < 0 || tmpx >= N || tmpy < 0 || tmpy >= N || map[tmpx][tmpy] == 0) {
				flag = false;
				break;
			}
			// checking progress end.
			else if (map[tmpx][tmpy] == t) {
				break;
			}
			
			cnt++; // the number of changing cells.
		}
		
		// flag: true = can reverse.
		if (flag) {
			for (int i = 1; i < cnt+1; i++) {
				map[x+dx[direction]*i][y+dy[direction]*i] = t;
			}
		}
	}
	
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		int x, y, ret1, ret2;
		int[][] map = null;
		
		for (int rnd = 1; rnd < test+1; rnd++) {
			ret1=0; ret2=0;
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][N];
			
			// Start setting
			map[N/2-1][N/2-1] = 2;
			map[N/2][N/2] = 2;
			map[N/2-1][N/2] = 1;
			map[N/2][N/2-1] = 1;
		
			// putting the piece and Checking area where can reverse.
			for (int c = 0; c < M; c++) {
				x = sc.nextInt()-1;
				y = sc.nextInt()-1;
				type = sc.nextInt();
				map[x][y] = type;
			
				for (int check = 0; check < 8; check++) {
					reverseLoop(map, x, y, type, check);
				}
			}
				
			// finally, counting a pieces.
			for (int[] is : map) {
				for (int i = 0; i < is.length; i++) {
					if (is[i] == 1) { ret1++; }
					else if (is[i] == 2) { ret2++; }
				}
			}
			
			System.out.println("#"+rnd+" "+ret1+" "+ret2);
		}
	}
}