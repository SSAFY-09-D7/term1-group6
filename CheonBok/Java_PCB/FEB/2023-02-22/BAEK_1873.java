import java.io.*;
import java.util.StringTokenizer;

// SWEA 1873 상호의 배틀필드

/*
  	.	평지(전차가 들어갈 수 있다.)
 	*	벽돌로 만들어진 벽
	#	강철로 만들어진 벽
	-	물(전차는 들어갈 수 없다.)
	^	위쪽을 바라보는 전차(아래는 평지이다.)
	v	아래쪽을 바라보는 전차(아래는 평지이다.)
	<	왼쪽을 바라보는 전차(아래는 평지이다.)
	>	오른쪽을 바라보는 전차(아래는 평지이다.)
  
 */

// direc = 1:상, 2:하, 3:좌, 4:우
class tank {
	int row, col, direc;
	
	public tank(int r, int c, int d) {
		this.row = r;
		this.col = c;
		this.direc = d;
	}
}

public class BAEK_1873 {
	
	static int H, W;
	static Character[][] world;
	static tank gametank;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t < T+1; t++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());   // 맵의 높이 (행)
			W = Integer.parseInt(st.nextToken());   // 맵의 너비 (열)
			
			world = new Character[H][W];
			
			for (int i = 0; i < H; i++) {
				String txt = br.readLine();
				for (int j = 0; j < W; j++) {
					char tmp =txt.charAt(j);
					world[i][j] = tmp;
					
					// 각 기호에 대한 탱크 direction을 저장해둔다. (Shoot 명령 시 방향 적용)
					     if (tmp == '^') gametank = new tank(i, j, 1);
					else if (tmp == 'v') gametank = new tank(i, j, 2);
					else if (tmp == '<') gametank = new tank(i, j, 3);
					else if (tmp == '>') gametank = new tank(i, j, 4);
				}
			}
		
		// ----------------------------------------------------------
			// 각 명령에 대한 처리 진행
			int N = Integer.parseInt(br.readLine());  // 작동 횟수
			String oper = br.readLine();
			
			for (int idx = 0; idx < N; idx++) {
				char type = oper.charAt(idx);
				
				     if (type == 'U') moveUp();
				else if (type == 'D') moveDown();
				else if (type == 'L') moveLeft();
				else if (type == 'R') moveRight();
				else if (type == 'S') Shoot();
			}
		// ------------------------------------------------------------
			
			sb.append("#"+t+" ");
			for (int r = 0; r < H; r++) {
				for (int c = 0; c < W; c++) {
					sb.append(world[r][c]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

	// 탱크가 보고 있는 상하좌우 방향에 대한 포탄 처리 진행
	private static void Shoot() {
		// 상 방향 포격
		if (gametank.direc == 1) {
			for (int r = gametank.row; r >= 0; r--) {
				if (world[r][gametank.col] == '*') {
					world[r][gametank.col] = '.';
					break;
				}
				else if (world[r][gametank.col] == '#') break;
			}
		}
		
		// 하 방향 포격
		else if (gametank.direc == 2) {
			for (int r = gametank.row; r < H; r++) {
				if (world[r][gametank.col] == '*') {
					world[r][gametank.col] = '.';
					break;
				}
				else if (world[r][gametank.col] == '#') break;
			}
		}
		
		
		// 좌 방향 포격
		else if (gametank.direc == 3) {
			for (int c = gametank.col; c >= 0; c--) {
				if (world[gametank.row][c] == '*') {
					world[gametank.row][c] = '.';
					break;
				}		
				else if (world[gametank.row][c] == '#') break;
			}
		}		
		
		
		// 우 방향 포격
		else if (gametank.direc == 4) {
			for (int c = gametank.col; c < W; c++) {
				if (world[gametank.row][c] == '*') {
					world[gametank.row][c] = '.';
					break;
				}			
				else if (world[gametank.row][c] == '#') break;
			}
		}	
	}

	// 오른쪽
	private static void moveRight() {
		int nx = gametank.row;
		int ny = gametank.col +1;
		
		if (0<=ny && ny<W && world[nx][ny] == '.') {
			world[gametank.row][gametank.col] = '.';
			world[nx][ny] = '>';
			gametank.col = ny;
		}
		else world[gametank.row][gametank.col] = '>';
		
		gametank.direc = 4; // 우 방향 
	}

	
	// 왼쪽
	private static void moveLeft() {
		int nx = gametank.row;
		int ny = gametank.col -1;
		
		if (0<=ny && ny<W && world[nx][ny] == '.') {
			world[gametank.row][gametank.col] = '.';
			world[nx][ny] = '<';
			gametank.col = ny;
		}
		else world[gametank.row][gametank.col] = '<';
		
		gametank.direc = 3; // 좌 방향 
	}

	
	// 아래쪽
	private static void moveDown() {
		int nx = gametank.row +1;
		int ny = gametank.col;
		
		if (0<=nx && nx<H && world[nx][ny] == '.') {
			world[gametank.row][gametank.col] = '.';
			world[nx][ny] = 'v';
			gametank.row = nx;
		}
		else world[gametank.row][gametank.col] = 'v';
		
		gametank.direc = 2; // 하 방향 
	}

	
	// 위쪽
	private static void moveUp() {
		int nx = gametank.row -1;
		int ny = gametank.col;
		
		if (0<=nx && nx<H && world[nx][ny] == '.') {
			world[gametank.row][gametank.col] = '.';
			world[nx][ny] = '^';
			gametank.row = nx;
		}
		else world[gametank.row][gametank.col] = '^';
		
		gametank.direc = 1;
	}
}
