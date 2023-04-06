import java.io.*;
import java.util.*;

public class Main {
	static int R, C, N;
	static StringBuilder sb = new StringBuilder();
	static char[][] map;
	static int[][] timer;
	static int[] dRow = {-1, 1, 0, 0};
	static int[] dCol = {0, 0, -1, 1};
	
	static class Point{
		int row, col;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        map = new char[R][C];
        timer = new int[R][C];
        for(int i = 0; i < R; i++) {
        	String str = br.readLine();
        	for(int j = 0; j < C; j++) {
        		char c =  str.charAt(j);
        		if(c == 'O') timer[i][j] = 3;
        		else timer[i][j] = -1;
        	}
        }
        
        if(N == 1) {
        	remakeMap();
        	print();
        	System.exit(0);
        }
        
        int count = 0;
        count++;
        timerMinus();
//    	System.out.println("Time : " + count);
//    	printTimer();
//    	remakeMap();
//    	print();
        
        
        if(N % 2 == 0) {
        	N = 2;
        }
        else if(N % 4 == 3) {
        	N = 3;
        }
        else if(N % 4 == 1) {
        	N = 5;
        }
    	
        // 루프 한 번 돌 때마다 
        while(count < N) {
        	count++;
        	timerMinus();
        	if(count % 2 == 0) {
        		// 모든 칸에 폭탄 설치	
        		putBomb();
        	}
        	else {
        		// 3초가 지난 폭탄 터지기
        		BooooomB();
        	}
//        	System.out.println("Time : " + count);
//        	printTimer();
//        	remakeMap();
//        	print();
        }
        
        remakeMap();
        print();
	}

	private static void printTimer() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(timer[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void BooooomB() {
		// 처음 터지는 폭탄들 모아놓기
		ArrayList<Point> list = new ArrayList<>();
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				// 터질 시간인 폭탄입니다!
				if(timer[i][j] == 0) {
					list.add(new Point(i, j));
				}
			}
		}
		
		// 모아놨던 폭탄들 터트리면서 인접폭탄도 다 터트리기
		for(int i = 0; i < list.size(); i++) {
			Point now = list.get(i);
			for(int d = 0; d < 4; d++) {
				int nextRow = now.row + dRow[d];
				int nextCol = now.col + dCol[d];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= R || nextCol >= C) continue;
				
				if(timer[nextRow][nextCol] >= 0) {
					timer[nextRow][nextCol] = -1;
				}
			}
			timer[now.row][now.col] = -1; 
		}
		
	}

	private static void remakeMap() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(timer[i][j] >= 0) map[i][j] = 'O';
				else map[i][j] = '.';
			}
		}
		
	}

	private static void putBomb() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(timer[i][j] < 0) timer[i][j] = 3;
			}
		}
		
	}

	private static void timerMinus() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(timer[i][j] >= 1) timer[i][j]--;
			}
		}
		
	}

	private static void print() {
        for(int i = 0; i < R; i++) {
        	for(int j = 0; j < C; j++) {
        		sb.append(map[i][j]);
        	}
        	sb.append("\n");
        }
        System.out.println(sb);
    }
}