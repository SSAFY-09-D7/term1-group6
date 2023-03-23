import java.io.*;
import java.util.*;
public class Impl_BOJ_14500 {
	static int N, M, ret;
	static int[][] arr;
	static int[][] delta = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	static int[][] type2 = {{0,1}, {1,0}, {0,-1}};
	static int[][] type1 = {{0,1}, {0,1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //row
		M = Integer.parseInt(st.nextToken()); //col
		arr = new int[N][M];
		ret = 0; //최댓값 (출력)
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
	// -----------------------------------------------------------------------------
		// 1. 정사각형(2x2)
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				checkType2(r, c);
			}
		}
		
		// 2. 직선 (우4 하4)
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				checkType1(r, c);
			}
		}
		
		// 3. ㄱ자
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				checkType3(r, c);
			}
		}
	
		// 4. z 모양
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				checkType4(r, c);
			}
		}
		
		// 철 모양
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				checkType5(r, c);
			}
		}
        System.out.println(ret);
	}
    
	
	//delta --> 0:하, 1:상, 2:우, 3:좌
    // 철 모양
    private static void checkType5(int r, int c) {
        
        if (0 <= r+delta[0][0]*2 && r+delta[0][0]*2 < N) {
            int nr = r + delta[0][0];
            //ㅏ
            if (0<= c+delta[2][1] && c+delta[2][1] < M) {
                int nc = c + delta[2][1];
                int t = arr[r][c] + arr[nr][c] + arr[nr++][nc] + arr[nr][c];
                ret = Math.max(ret, t);
            }
            //ㅓ
            nr = r + delta[0][0];
            if (0<= c+delta[3][1] && c+delta[3][1] < M) {
                int nc = c + delta[3][1];
                int t = arr[r][c] + arr[nr][c] + arr[nr++][nc] + arr[nr][c];
                ret = Math.max(ret, t);
            }
        }
        
        if (0 <= c+delta[2][1]*2 && c+delta[2][1]*2 < M) {
            int nc = c + delta[2][1];
            //ㅜ
            if (0<= r+delta[0][0] && r+delta[0][0] < N) {
                int nr = r + delta[0][0];
                int t = arr[r][c] + arr[r][nc] + arr[nr][nc++] + arr[r][nc];
                ret = Math.max(ret, t);
            }
            //ㅗ
            nc = c + delta[2][1];
            if (0<= r+delta[1][0] && r+delta[1][0] < N) {
                int nr = r + delta[1][0];
                int t = arr[r][c] + arr[r][nc] + arr[nr][nc++] + arr[r][nc];
                ret = Math.max(ret, t);
            }
        }
    }
    
    
	// z 모양
	private static void checkType4(int r, int c) {
		 if (0 <= r+delta[0][0]*2 && r+delta[0][0]*2 < N) {
			 int nr = r+delta[0][0];
             // T1
			 if (0<= c+delta[2][1] && c+delta[2][1] < M) {
			    int nc = c + delta[2][1];
				int t = arr[r][c] + arr[nr][c] + arr[nr++][nc] + arr[nr][nc];
				ret = Math.max(ret, t);
			 }
			
			 nr = r + delta[0][0];
             // T2
			 if (0<= c+delta[3][1] && c+delta[3][1] < M) {
			    int nc = c + delta[3][1];
			    int t = arr[r][c] + arr[nr][c] + arr[nr++][nc] + arr[nr][nc];
				ret = Math.max(ret, t);
			 }	
		}
		
		
		if (0<= c+delta[2][1]*2 && c+delta[2][1]*2 < M) {
			int nc = c+delta[2][1];
			// T3
			if (0 <= r+delta[0][0] && r+delta[0][0] < N) {
				int nr = r+delta[0][0];
				int t = arr[r][c] + arr[r][nc] + arr[nr][nc++] + arr[nr][nc];
				ret = Math.max(ret, t);
			}
			
            nc = c+delta[2][1];
            // T4
			if (0 <= r+delta[1][0] && r+delta[1][0] < N) {
				int nr = r+delta[1][0];
				int t = arr[r][c] + arr[r][nc] + arr[nr][nc++] + arr[nr][nc];
				ret = Math.max(ret, t);
			}
		}		
	}

    // ㄱ자
	private static void checkType3(int r, int c) {
        // 세로 긴 (아래)
		if (0 <= r+delta[0][0]*2 && r+delta[0][0]*2 < N) {
            int nr = r + delta[0][0];
            if (0<= c+delta[2][1] && c+delta[2][1] < M){
                int nc = c + delta[2][1];
                int t = arr[r][c] + arr[r][nc] + arr[nr++][nc] + arr[nr][nc];
                ret = Math.max(ret, t);
            }
            
            nr = r + delta[0][0];
            if (0<= c+delta[3][1] && c+delta[3][1] < M) {
                int nc = c + delta[3][1];
                int t = arr[r][c] + arr[r][nc] + arr[nr++][nc] + arr[nr][nc];
                ret = Math.max(ret, t);
            } 
        }
        
        // 세로 긴 (위)
        if (0 <= r+delta[1][0]*2 && r+delta[1][0]*2 < N) {
            int nr = r + delta[1][0];
            if (0<= c+delta[2][1] && c+delta[2][1] < M){
                int nc = c + delta[2][1];
                int t = arr[r][c] + arr[r][nc] + arr[nr--][nc] + arr[nr][nc];
                ret = Math.max(ret, t);
            }
            
            nr = r + delta[1][0];
            if (0<= c+delta[3][1] && c+delta[3][1] < M) {
                int nc = c + delta[3][1];
                int t = arr[r][c] + arr[r][nc] + arr[nr--][nc] + arr[nr][nc];
                ret = Math.max(ret, t);
            } 
        }
        
        
        // 가로 긴 (오른)
        if (0 <= c+delta[2][1]*2 && c+delta[2][1]*2 < M) {
            int nc = c + delta[2][1];
            if (0<= r+delta[1][0] && r+delta[1][0] < N){
                int nr = r + delta[1][0];
                int t = arr[r][c] + arr[nr][c] + arr[nr][nc++] + arr[nr][nc];
                ret = Math.max(ret, t);
            }
            
            nc = c + delta[2][1];
            if (0<= r+delta[0][0] && r+delta[0][0] < N) {
                int nr = r + delta[0][0];
                int t = arr[r][c] + arr[nr][c] + arr[nr][nc++] + arr[nr][nc];
                ret = Math.max(ret, t);
            } 
        }
        
        // 가로 긴 (왼)
        if (0 <= c+delta[3][1]*2 && c+delta[3][1]*2 < M) {
            int nc = c + delta[3][1];
            if (0<= r+delta[0][0] && r+delta[0][0] < N){
                int nr = r + delta[0][0];
                int t = arr[r][c] + arr[nr][c] + arr[nr][nc--] + arr[nr][nc];
                ret = Math.max(ret, t);
            }
            
            nc = c + delta[3][1];
            if (0<= r+delta[1][0] && r+delta[1][0] < N) {
                int nr = r + delta[1][0];
                int t = arr[r][c] + arr[nr][c] + arr[nr][nc--] + arr[nr][nc];
                ret = Math.max(ret, t);
            } 
        }
	}
	
	// ㅣ, ㅡ
	private static void checkType1(int r, int c) {
        // ㅡ
	    if (0 <= c+delta[2][1]*3 && c+delta[2][1]*3 < M){
            int nc = c + delta[2][1];
            int t = arr[r][c] + arr[r][nc++] + arr[r][nc++] + arr[r][nc];
             ret = Math.max(ret, t);
        }
        // ㅣ
         if (0 <= r+delta[0][0]*3 && r+delta[0][0]*3 < N){
            int nr = r + delta[0][0];
            int t = arr[r][c] + arr[nr++][c] + arr[nr++][c] + arr[nr][c];
             ret = Math.max(ret, t);
        }
	}
	
    // 2x2 정사각형
	private static void checkType2(int r, int c) {
		if (0<= r+1 && r+1 <N && 0<= c+1 && c+1 <M){
            int t = arr[r][c]+ arr[r+1][c] + arr[r][c+1] + arr[r+1][c+1];
            ret = Math.max(ret, t);
        }
	}
}