import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static int N, M;
	public static int[][] arr;
	public static boolean[][] visited;
	public static int ANS = 0;
	public static int cheeseCNT = 0;
	public static boolean[][] melting;
	public static int[] nr = {-1, 1, 0, 0};
    public static int[] nc = {0, 0, -1, 1};
    public static int[][] airCnt;
	
    public static void main(String[] args) throws Exception {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
       
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N][M];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if(arr[i][j] == 1){
                    cheeseCNT += 1;
                }
                    
            }
        }
        
        while(true) {
        	// 치즈 남아있는지 확인
            if(cheeseCNT == 0) {
                break;
            }
            
            visited = new boolean[N][M];
            airCnt = new int[N][M];
            
            visited[0][0] = true;
            DFS(0, 0);
            
            for(int i=0; i<N; i++) {
            	for(int j=0; j<M; j++) {
            		if(airCnt[i][j] >= 2) {
            			arr[i][j] = 0;
            			cheeseCNT -= 1;
            		}
            	}
            }
            
            ANS += 1;
        }
        
        System.out.println(ANS);
   
    }
    
    public static void DFS(int i, int j) {
    
    for(int p=0; p<4; p++) {
        int ii = i + nr[p];
        int jj = j + nc[p];
        
        if(ii<0 || ii>=N || jj<0 || jj>=M) continue;
        
        if(arr[ii][jj] == 1)  {
            airCnt[ii][jj] += 1;
        }
        if(arr[ii][jj] == 0) {
            if(!visited[ii][jj]) {
                visited[ii][jj] = true;
                DFS(ii, jj);    
            }
        }
    }    
}
    
}