import java.io.*;
import java.util.*;

// 백준 1446 지름길
public class BOJ1446 {
	static class node {
		int pos, d;
		public node(int pos, int d) {
			this.pos = pos;
			this.d = d;
		}
		
		@Override
		public String toString() {
			return "node [pos=" + pos + ", d=" + d + "]";
		}
	}
	
	//key=출발 위치, item=출발 위치에서 갈 수 있는 모든 지름길 (가장 짧은 거리값 저장)
	static Map<Integer, Map<Integer, Integer>> roads = new HashMap<>();
	//key=지름길(도착 위치), item=거리 (여러 입력에 대해 가장 짧은 거리 값으로 update)
	static Map<Integer, Integer> R = new HashMap<>();
	static int[] able; //해당 위치에서의 최소 거리
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //지름길 수
		int M = Integer.parseInt(st.nextToken()); //고속도로 길이
		able = new int[M+1]; // 도달한 최소 시간(거리)
		Arrays.fill(able, Integer.MAX_VALUE); // 최소 거리를 업데이트 해야 하므로 초깃값은 최댓값으로
		
		
		// Map으로 가장 짧게 이동할 수 있는 지름길 정보를 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			if (roads.containsKey(s)) { //해당 위치에 지름길이 하나라도 있음을 알고 있다면
				if (roads.get(s).containsKey(e)) {   //이전에 같은 지름길 정보를 저장했다면
					int dist = roads.get(s).get(e);
					if (dist > d) { //같은 지름길 정보 중 가장 짧은 거리로 update
						roads.get(s).put(e, d);
					}
				}
				else {  //처음 발견한 지름길이라면 -> 정보 추가
					roads.get(s).put(e, d);
				}
			}
			else {  //해당 위치에서 가장 처음으로 발견한 지름길이라면
				roads.put(s, new HashMap<>());
				roads.get(s).put(e, d);
			}
		}
		
		// BFS 목적의 Queue
		Queue<node> q = new LinkedList<>();
		q.add(new node(0, 0));
		
		int ret = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			node p = q.poll();
		
			// 도착지를 over 했다면
			if(p.pos > M) continue;
			// 도착지에 다다랐다면 (BFS지만, 거리 상 가장 짧지 않을 수 있다 -> break X)
			else if (p.pos == M) {
				if(ret > p.d) ret = p.d;
				continue;
			}
			
			//지름길이 있다면 지름길 리스트를 들어가서 거리 계산
			if(roads.containsKey(p.pos)) {
				Map<Integer, Integer> tmp = roads.get(p.pos);
				Set<Integer> kyes = tmp.keySet();
				
				// 모든 지름길 정보를 가져와서 거리(시간) 상 더 짧은 경우라면 거리 update 후 queue에 반영
				for (int k : kyes) {
					if(k <= M && able[k] > p.d + tmp.get(k)) {
						able[k] = p.d + tmp.get(k);
						q.add(new node(k, p.d + tmp.get(k)));
					}
				}
			}
			
			//지름길이 없다면 바로 다음 위치(+1)로 이동하는데, 계산된 거리 상 이득일 경우에만 처리
			if(p.pos+1 <= M && able[p.pos+1] > p.d + 1) {
				able[p.pos+1] = p.d + 1;
				q.add(new node(p.pos+1, p.d+1));
			}
		}
		System.out.println(ret);
	}
}