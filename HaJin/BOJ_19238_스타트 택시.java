import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static int N, M, fuel;
	public static int[][] arr;				// 지도
	public static int now_x, now_y;			// 택시 현재 위치
	public static int[][] start;			// 승객 출발 위치
	public static int[][] end;				// 승객 도착 위치
	public static boolean success;	// 성공 여부 
	public static int[] minDist;			// 현재 택시 위치 ~ 손님 출발 위치 최솟값 저장
	public static int minCustomerIdx;		// 최솟값 손님 인덱스 
	public static boolean[][] visited;		// bfs 방문처리
	public static int[] nr = {-1, 1, 0, 0};
	public static int[] nc = {0, 0, -1, 1};
	
	public static class Node{
		int i;
		int j;
		int cnt;
		Node(int i, int j, int cnt){
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		now_x = Integer.parseInt(st.nextToken()) - 1;
		now_y = Integer.parseInt(st.nextToken()) - 1;
		
		start = new int[M][2];
		end = new int[M][2];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			start[i][0] = Integer.parseInt(st.nextToken()) - 1;
			start[i][1] = Integer.parseInt(st.nextToken()) - 1;
			end[i][0] = Integer.parseInt(st.nextToken()) - 1;
			end[i][1] = Integer.parseInt(st.nextToken()) - 1;
		}
		int CNT = M;
		
		while(true) {
			
			// 종료조건
			if(CNT == 0) {
				success = true;
				break;
			}
			if(fuel <= 0) {
				success = false;
				break;
			}
			
			// 남은 승객 중 최단거리 최솟값 찾기
			int d = findMinDist();
			
			if(fuel <= d) {
				success = false;
				break;
			}
			
			// 승객 출발 위치까지 사용된 연료 빼기
			fuel -= d;
			
			// 택시 위치 이동
			now_x = start[minCustomerIdx][0];
			now_y = start[minCustomerIdx][1];
			
			// 승객 도착지까지 이동 거리
			int dist = BFS(end[minCustomerIdx][0], end[minCustomerIdx][1]);
			
			if(fuel < dist) {
				success = false;
				break;
			}
			
			fuel += dist;
			
			// 끝난 승객 출발 위치 -1로
			start[minCustomerIdx][0] = -1;
			start[minCustomerIdx][1] = -1;
			
			now_x = end[minCustomerIdx][0];
			now_y = end[minCustomerIdx][1];
			
			CNT -= 1;
		}
		
		if(success) {
			System.out.println(fuel);
		}
		else
			System.out.println("-1");
	}
	
	public static int findMinDist() {
		minDist = new int[M];
		
		// bfs로 각 손님까지 최단거리 찾기
		for(int i=0; i<M; i++) {
			if(start[i][0] == -1)
				minDist[i] = Integer.MAX_VALUE;
			else {
				minDist[i] = BFS(start[i][0], start[i][1]);
			}
		}
		
		// minDist 중 최단거리 찾기 (minCustomerIdx에 저장)
		int min = Integer.MAX_VALUE;
		int min_i = 0;
		int min_j = 0;
		for(int i=0; i<M; i++) {
			if(min > minDist[i]) {
				min = minDist[i];
				minCustomerIdx = i;
				min_i = start[i][0];
				min_j = start[i][1];
			}
			else if(min == minDist[i]) {
				if(min_i > start[i][0]) {
					minCustomerIdx = i;
					min_i = start[i][0];
					min_j = start[i][1];
				}
				else if(min_i == start[i][0]) {
					if(min_j > start[i][1]) {
						minCustomerIdx = i;
						min_i = start[i][0];
						min_j = start[i][1];
					}
				}
			}
		}
		
		return min;
	}
	
	public static int BFS(int end_x, int end_y) {
		Queue<Node> queue = new LinkedList<>();
		visited = new boolean[N][N];
		int cnt = Integer.MAX_VALUE;
		
		queue.add(new Node(now_x, now_y, 0));
		visited[now_x][now_y] = true;
		
		while(!queue.isEmpty()) {
			Node now = queue.poll();
			
			if(now.i == end_x && now.j == end_y) {
				cnt = now.cnt;
				break;
			}
			
			for(int p=0; p<4; p++) {
				int next_i = now.i + nr[p];
				int next_j = now.j + nc[p];
				
				if(next_i>=0 && next_i<N && next_j>=0 && next_j<N && !visited[next_i][next_j] && arr[next_i][next_j]==0) {
					visited[next_i][next_j] = true;
					queue.add(new Node(next_i, next_j, now.cnt + 1));
				}
			}
		}
		
		return cnt;
	}
}