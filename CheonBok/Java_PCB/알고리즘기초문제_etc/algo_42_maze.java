// 알고리즘기초문제 42번 미로 도착지점
import java.util.Scanner;
public class algo_42_maze {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		
		// Test case loop
		for (int tc = 1; tc < test+1; tc++) {
			// map includes border because first idx is (1,1)
			int arrsize = sc.nextInt()+2; 
			int x = sc.nextInt();  // start x
			int y = sc.nextInt();  // start y
			int jump = sc.nextInt();
			
			int[][] arrmap = new int[arrsize][arrsize];
			
			int[][] jumparr = new int[jump][2];
			
			// jumper cell info loop (jumper cell = 1)
			for (int i = 0; i < jump; i++) {
				jumparr[i][0] = sc.nextInt();
				jumparr[i][1] = sc.nextInt();
				
				arrmap[jumparr[i][0]][jumparr[i][1]] = 1;
			}
			
			int move_cnt = sc.nextInt();
			int[][] direct = new int[move_cnt][2];
			
			// direction, depth info loop
			for (int i = 0; i < move_cnt; i++) {
				direct[i][0] = sc.nextInt();
				direct[i][1] = sc.nextInt();
			}
			
			/*
			 * update idx (0,0) situations.
			 * 1. find jumper when move cells.
			 * 2. out of map.
			 */
			
			// move loop
			for (int i = 0; i < move_cnt; i++) {
				int direction = direct[i][0]; 
				int depth = direct[i][1]; 
				
				switch (direction) {
					// move up case
					case 1:
						// can move
						if (x-depth > 0) {
							int tmp = 1;
							// go as possible to depth.
							while (tmp != depth) {
								// find jumper (case1)
								if (arrmap[x-tmp][y] == 1) {
									x = 0;
									y = 0; 
									break; // while
								}
								else {
									tmp += 1;
								}
							}
							if (x==0 && y==0) {
								break; // switch
							}
							else {
								x -= depth;
								break; // switch
							}
						}
						// don't movement (case2)
						else {
							x = 0;
							y = 0;
							break; // switch
						}
					
					// move right case
					case 2:
						if (y+depth < arrsize-1) {
							int tmp = 1;
							while (tmp != depth) {
								if (arrmap[x][y+tmp] == 1) {
									x = 0;
									y = 0; 
									break;
								}
								else {
									tmp += 1;
								}
							}
							if (x==0 && y==0) {
								break; // switch
							}
							else {
								y += depth;
								break; // switch
							}
						}
						else {
							x = 0;
							y = 0;
							break; // switch
						}
					
					// move down case
					case 3:
						if (x+depth < arrsize-1) {
							int tmp = 1;
							while (tmp != depth) {
								if (arrmap[x+tmp][y] == 1) {
									x = 0;
									y = 0; 
									break;
								}
								else {
									tmp += 1;
								}
							}
							if (x==0 && y==0) {
								break; // switch
							}
							else {
								x += depth;
								break; // switch
							}
						}
						else {
							x = 0;
							y = 0;
							break; // switch
						}
					
					// move left case
					case 4:
						if (y-depth > 0 && arrmap[x][y-depth] != 1) {
							int tmp = 1;
							while (tmp != depth) {
								if (arrmap[x][y-depth] == 1) {
									x = 0;
									y = 0; 
									break;
								}
								else {
									tmp += 1;
								}
							}
							if (x==0 && y==0) {
								break; // switch
							}
							else {
								y -= depth;
								break; // switch
							}
						}
						else {
							x = 0;
							y = 0;
							break; // switch
						}
				}
				
				if (x==y && y==0) {
					break; // for
				}
			}
			System.out.println("#"+tc+" "+x+" "+y);
		}
	}
}
