import java.io.*;
import java.util.*;
public class BFS_BOJ_9205 {
	static class node {
		int x, y, idx;
		
		// x, y.  visited 인덱스를 위한 idx
		public node(int x, int y, int idx) {
			this.x = x;
			this.y = y;
			this.idx = idx;
		}
	}
	
	static ArrayList<node> nodes = new ArrayList(); //편의점들
	// 상근이가 위치할 수 있는 좌표 (맥주가 고갈되지 않고 갈 수 있는 좌표들)
	static Queue<node> nq = new LinkedList(); 
	static boolean[] visited;  // 같은 편의점은 그만가자

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for (int T = 0; T < t; T++) {
			nodes.clear(); // 테케 초기화1
			nq.clear();    // 테케 초기화2
			int conv = Integer.parseInt(br.readLine()); // 편의점 개수
			visited = new boolean[conv]; // 이미 방문한 편의점 표시
			boolean flg = false; // happy, sad 구분
			
			
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken()); //상근X
			int startY = Integer.parseInt(st.nextToken()); //상근Y
			nq.add(new node(startX, startY, -1)); // 시작 위치
			
			
			for (int i = 0; i < conv; i++) {
				st = new StringTokenizer(br.readLine());
				int tx = Integer.parseInt(st.nextToken());
				int ty = Integer.parseInt(st.nextToken());
				nodes.add(new node(tx, ty, i)); //편의점 좌표 추가
			}
			
			
			st = new StringTokenizer(br.readLine());
			int lastX = Integer.parseInt(st.nextToken()); //도착지X
			int lastY = Integer.parseInt(st.nextToken()); //도착지Y
			
			
			int dist; // 거리
			//가능한 경로는 nq에 들어가 다음을 탐색한다.
			while (!nq.isEmpty()) {
				node next = nq.poll();
				
				dist = Math.abs(next.x - lastX) + Math.abs(next.y - lastY);
				// 목적지에 다다를 수 있다면
				if (dist <= 1000) {
					flg = true;
					break;
				}
				
				
				// 아직 방문하지 않은 편의점 + 도달할 수 있는 거리면 경유 가능
				for (node nn : nodes) {
					if (!visited[nn.idx]) {
						dist = Math.abs(next.x - nn.x) + Math.abs(next.y - nn.y);
						if (dist <= 1000) {
							nq.add(nn);
							visited[nn.idx] = true;
						}
					}
				}
			}
			if (flg) System.out.println("happy");
			else System.out.println("sad");
		}
	}
}