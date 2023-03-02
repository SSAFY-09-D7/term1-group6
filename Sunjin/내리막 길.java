package algorithm;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int result;
    static int[][] map;
    static int[][] count;
    static boolean[][] visited;
    static int[] dRow = {-1, 1, 0, 0};
    static int[] dCol = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        count = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
        visited[0][0] = true;
        dfs(0, 0);
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < M; j++) {
        		System.out.print(count[i][j] + " ");
        	}
        	System.out.println();
        }
        System.out.println(count[0][0]);
    }

    private static int dfs(int row, int col) {
    	if(row == N - 1 && col == M - 1) {
    		count[row][col] += 1;
    		return 1;
    	}
    	for(int i = 0; i < 4; i++) {
    		int nextRow = row + dRow[i];
    		int nextCol = col + dCol[i];
    		
    		if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) continue;
    		
    		if(map[nextRow][nextCol] < map[row][col]) {
    			if(!visited[nextRow][nextCol]) {
    				visited[nextRow][nextCol] = true;
        			count[row][col] += dfs(nextRow, nextCol);
    			}
    			else {
    				count[row][col] += count[nextRow][nextCol];
    			}
    		}	
    	}
    	return count[row][col];
    	
    }
}
