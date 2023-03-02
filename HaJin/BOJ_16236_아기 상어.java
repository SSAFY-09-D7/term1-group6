import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int N;
	public static int[][] arr;
	public static int nowX, nowY;
	public static int SIZE = 2;
	public static int ANS = 0;
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<N; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        		if(arr[i][j] == 9) {
        			nowX = i;
        			nowY = j;
        			arr[i][j] = 0;
        		}
        	}
        }
        
        int tmpCNT = 0;
        
        while(true) {
            
        	gotoX = Integer.MAX_VALUE;
        	gotoY = Integer.MAX_VALUE;
        	gotoCNT = Integer.MAX_VALUE;
        	visited = new boolean[N][N];
        			
        	bfs(nowX, nowY);
        	
        	if(gotoX == Integer.MAX_VALUE) {	// 갈 노드가 정해지지 않았으면 더이상 갈 곳 X : 반복문 종료
        		break;	
        	}
        	
        	ANS += gotoCNT;		// ANS += 목적지까지 거리
        	tmpCNT += 1;
        	
        	if(tmpCNT == SIZE) {
        		tmpCNT = 0;
        		SIZE += 1;
        	}
        	
        	// 먹은 물고기 위치 0으로 변경
        	arr[gotoX][gotoY] = 0;	
        	
        	// 현재 위치 먹은 물고기 위치로 변경
        	nowX = gotoX;
        	nowY = gotoY;
        }
        
        System.out.println(ANS);
	}

	public static boolean[][] visited;
	
	public static class bfsNode{
		int x;
		int y;
		int cnt;
		bfsNode(int x, int y, int cnt){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	public static int[] nr = {-1, 1, 0, 0};
	public static int[] nc = {0, 0, -1, 1};
	public static int gotoX, gotoY, gotoCNT;
	
	public static int bfs(int i, int j) {
		int cnt = 0;
		
		Queue<bfsNode> queue = new LinkedList<>();
		
		queue.add(new bfsNode(i, j, 0));
		visited[i][j] = true;
		
		while(!queue.isEmpty()) {
			bfsNode node = queue.poll();
			int ii = node.x;
			int jj = node.y;
			
			if(arr[ii][jj] < SIZE && arr[ii][jj]!=0) {   // 먹을 수 있는 물고기 나오면
				
				if(gotoCNT > node.cnt) {	// 거리 확인 : 더 가까울 때
					gotoCNT = node.cnt;
					gotoX = ii;
					gotoY = jj;
				}
				else if(gotoCNT == node.cnt) {	// 거리 확인 : 같은 거리일때
					if(gotoX > ii) {
						gotoX = ii;
						gotoY = jj;
					}
					else if(gotoX == ii) {	// 거리 확인 : 거리 같고 같은 행에 있을 때
						if(gotoY > jj)		// 거리 확인 : 거리 같고 같은 행에 있을 때 열 비교
							gotoY = jj;
					}
				}
			}
			
			for(int p=0; p<4; p++) {
				int iii = ii + nr[p];
				int jjj = jj + nc[p];
				if(iii>=0 && iii<N && jjj>=0 && jjj<N && !visited[iii][jjj] && arr[iii][jjj]<=SIZE) {
					queue.add(new bfsNode(iii, jjj, node.cnt+1));
					visited[iii][jjj] = true;
				}
			}
			
		}
		
		return cnt;
	}
}