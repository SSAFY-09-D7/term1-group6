import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Tank {
	private int[] point;
	private char headDir;
	
	public Tank(int row, int col, char headDir) {
		point = new int[2];
		point[0] = row;
		point[1] = col;
		this.headDir = headDir;
	}
	
	public int[] getPoint() {
		return point;
	}

	public void setPoint(int row, int col) {
		point[0] = row;
		point[1] = col;
	}

	public char getHeadDir() {
		return headDir;
	}

	public void setHeadDir(char headDir) {
		this.headDir = headDir;
	}
}

public class Solution{
	static char[][] map;
	static boolean[][] canMove;
	static int H;
	static int W;
	static int[] dRow = {-1, 1, 0, 0};
	static int[] dCol = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			map = new char[H][W];
			Tank tank = null;
			
			for(int i = 0; i < H; i++) {
				String str = br.readLine();
				char[] chs = str.toCharArray();
				for(int j = 0; j < W; j++) {
					map[i][j] = chs[j];
					if(chs[j] == '^' || chs[j] == 'v' || chs[j] == '<' || chs[j] == '>') {
						tank = new Tank(i, j, chs[j]);
						map[i][j] = '.';
					}
				}
			}
			
			int N = Integer.parseInt(br.readLine());
			String str = br.readLine();
			for(int i = 0; i < N; i++) {
				if(str.charAt(i) == 'S') shoot(tank);
				else move(tank, str.charAt(i));
			}
			
			// 최종 탱크 머리 그리기
			map[tank.getPoint()[0]][tank.getPoint()[1]] = tank.getHeadDir();
			System.out.printf("#%d ", test_case);
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}
	private static void move(Tank tank, char dir) {
		int tankRow = tank.getPoint()[0];
		int tankCol = tank.getPoint()[1];
		int nextRow = 0;
		int nextCol = 0;
		if(dir == 'U') {
			tank.setHeadDir('^');
			nextRow = tankRow + dRow[0];
			nextCol = tankCol + dCol[0];
		}
		else if(dir == 'D') {
			tank.setHeadDir('v');
			nextRow = tankRow + dRow[1];
			nextCol = tankCol + dCol[1];
		}
		else if(dir == 'L') {
			tank.setHeadDir('<');
			nextRow = tankRow + dRow[2];
			nextCol = tankCol + dCol[2];
		}
		else if(dir == 'R') {
			tank.setHeadDir('>');
			nextRow = tankRow + dRow[3];
			nextCol = tankCol + dCol[3];
		}
		
		if(nextRow < 0 || nextCol < 0 || nextRow >= H || nextCol >= W) return;
		if(map[nextRow][nextCol] == '.') tank.setPoint(nextRow, nextCol);
	}
	private static void shoot(Tank tank) {
		int tankRow = tank.getPoint()[0];
		int tankCol = tank.getPoint()[1];
		if(tank.getHeadDir() == '^') {
			for(int row = tankRow - 1; row >= 0; row--) {
				if(map[row][tankCol] == '*') {
					map[row][tankCol] = '.';
					break;
				}
				else if(map[row][tankCol] == '#') break;
			}
		}
		else if(tank.getHeadDir() == 'v') {
			for(int row = tankRow + 1; row < H; row++) {
				if(map[row][tankCol] == '*') {
					map[row][tankCol] = '.';
					break;
				}
				else if(map[row][tankCol] == '#') break;
			}
		}
		else if(tank.getHeadDir() == '<') {
			for(int col = tankCol - 1; col >= 0; col--) {
				if(map[tankRow][col] == '*') {
					map[tankRow][col] = '.';
					break;
				}
				else if(map[tankRow][col] == '#') break;
			}
		}
		else if(tank.getHeadDir() == '>') {
			for(int col = tankCol + 1; col < W; col++) {
				if(map[tankRow][col] == '*') {
					map[tankRow][col] = '.';
					break;
				}
				else if(map[tankRow][col] == '#') break;
			}
		}
	}
}
