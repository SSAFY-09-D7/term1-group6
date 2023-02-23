import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution{

	public static int T, N, K;
	public static int[][] arr;
	public static boolean[][] visited;
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0};
	public static int MAX = 0;
	
	public static void func(int x, int y, int cnt, boolean cut){
	
	    int now = arr[x][y];
	
	    for(int i=0; i<4; i++){
	        int xx = x + dx[i];
	        int yy = y + dy[i];
	
	        if(xx>=0 && xx<N && yy>=0 && yy<N && !visited[xx][yy]){
	            if(now > arr[xx][yy]){
	                visited[xx][yy] = true;
	                func(xx, yy, cnt+1, cut);
	                visited[xx][yy] = false;
	            }
	            else if(!cut){
	                if(now > arr[xx][yy] - K){
	                    visited[xx][yy] = true;
	                    int tmp = arr[xx][yy];
	                    arr[xx][yy] = arr[x][y] - 1;
	                    func(xx, yy, cnt+1, true);
	                    visited[xx][yy] = false;
	                    arr[xx][yy] = tmp;
	                }
	
	            }
	        }
	
	    }
	    if(cnt > MAX)
	        MAX = cnt;
	}

	public static void main(String[] args) throws IOException{
	
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st ;
	
	    st = new StringTokenizer(br.readLine());
	    T = Integer.parseInt(st.nextToken());
	
	    for(int test_case = 1; test_case <= T; test_case++){
	
	        MAX = 0;
	
	        st = new StringTokenizer(br.readLine());
	        N = Integer.parseInt(st.nextToken());
	        K = Integer.parseInt(st.nextToken());
	
	        arr = new int[N][N];
	        visited = new boolean[N][N];
	
	        int max = 0;
	
	        for(int i=0; i<N; i++){
	            st = new StringTokenizer(br.readLine());
	            for(int j=0; j<N; j++){
	                arr[i][j] = Integer.parseInt(st.nextToken());
	                if(max < arr[i][j])
	                    max = arr[i][j];
	            }
	        }
	
	        for(int i=0; i<N; i++){
	            for(int j=0; j<N; j++){
	                if(max == arr[i][j]){
	                    visited[i][j] = true;
	                    func(i, j, 1, false);
	                    visited[i][j] = false;
	                }
	            }
	        }
	        System.out.printf("#%d %d\n", test_case, MAX);
	    }
	}
}