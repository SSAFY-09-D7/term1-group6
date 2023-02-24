package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static int R,C;
	static int max;
	static char[][] map;
	static int[] dRow = {-1, 1, 0, 0};
	static int[] dCol = {0, 0, -1, 1};
	static boolean[][] visited;
	static HashMap<Character, Integer> hashMap;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        map = new char[R][C];
        visited = new boolean[R][C];
        
        hashMap = new HashMap<>();
        for(int i = 0; i < R; i++) {
        	String str = br.readLine();
        	for(int j = 0; j < C; j++) {
        		map[i][j] = str.charAt(j);
        		hashMap.put(map[i][j], 0);
        	}
        }
        
        max = 0;
        visited[0][0] = true;
        hashMap.put(map[0][0], 1);
        dfs(0, 0, 1);
        System.out.println(max);
    }

	private static void dfs(int row, int col, int depth) {
		
		if(max < depth) max = depth;
		
		for(int i = 0; i < 4; i++) {
			int nextRow = row + dRow[i];
			int nextCol = col + dCol[i];
			
			if(nextRow < 0 || nextCol < 0 || nextRow >= R || nextCol >= C) continue;
			
			if(!visited[nextRow][nextCol] && hashMap.get(map[nextRow][nextCol]) == 0) {
				visited[nextRow][nextCol] = true;
				hashMap.put(map[nextRow][nextCol], 1);
				dfs(nextRow, nextCol, depth + 1);
				hashMap.put(map[nextRow][nextCol], 0);
				visited[nextRow][nextCol] = false;
			}
			
		}
		
	}

}