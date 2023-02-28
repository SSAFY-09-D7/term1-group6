// 백준 17471 게리맨더링
import java.io.*;
import java.util.*;

public class BAEK_17471 {
	
	static ArrayList<Integer>[] nodes; // 인접 구역 정보를 담는 인접 리스트
	static int[] people;  // 각 구역의 인구수
	static int ret, N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());  //총 노드(구역) 수
		people = new int[N+1];      // 1번 인덱스부터 시작하는 인구수 저장
		nodes = new ArrayList[N+1]; // 1번 인덱스부터 담아내는 인접 리스트
		
		// 인구수 정보 담기
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int value = Integer.parseInt(st.nextToken());
			people[i] = value;
		}
		//System.out.println(Arrays.toString(people));
		
		// 각각의 노드에 대한 인접 정보 담기.
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			
			nodes[i] = new ArrayList<>();
			for (int n = 0; n < t; n++) {
				nodes[i].add(Integer.parseInt(st.nextToken()));
			}
		}
//		for (int i = 1; i <=N; i++) {
//			System.out.println(nodes[i]);
//		}
		
		
		
	// ############################################################################################	
		// Step1. Parents(이웃관계) 배열을 통해 지역이 분리되는 구간이 있는지 확인한다.
		// ex) 문제의 예시(백준시)처럼 모든 구역이 붙어 있다면 [0, 1, 1, 1, 1] 형식이 나타나며
		//     특정 구역이 연결되어 있지 않다면 [0, 1, 1, 2, 2]같은 결과가 나타난다.
		// 이를 통해 2개의 구역으로 구분되는지 분별할 수 있다.
		// [0, 1, 1, 2, 3, 3]처럼 3개 이상의 구역이 나타난다면 2개의 구역으로 구분할 수 없으므로 이는 제외(-1) 대상이다.
		int[] parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		
		// 자신이 부모라면 자신을 기준으로 이웃한 노드들을 통합한다.
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if (parents[i] == i) {
				cnt++;
				// cnt(구역)이 3개 이상인 상태는 결과를 만들 수 없는 상태 = -1 출력
				if (cnt == 3) {
					System.out.println("-1");
					System.exit(0);
				}
				neighborCheck(i, parents); // i노드와 붙어있는 모든 노드들의 부모를 i로 맞춤
			}
		}
		//System.out.println(Arrays.toString(parents));
	// #############################################################################################
		
		
	// ###########################################################################
		// Step2. 구역 구분(cnt) 결과, 2개가 나왔다 = 구역이 2개로 명확히 분리되었으므로
		// 두 구역의 인구수 합계를 각각 계산해 그 차이를 출력한다.
		if (cnt == 2) {
			int teamA = 0, teamB = 0;
			for (int i = 1; i <= N; i++) {
				if (parents[i] == 1) teamA += people[i];
				else teamB += people[i];
			}
			
			System.out.println(Math.abs(teamB - teamA));
			System.exit(0);
		}
	// ###########################################################################
		
	
		// Step3. 여기부터는 모든 구역이 연결된 구조이다. [0, 1, 1, 1, 1, 1]처럼.
		// 최소 1개 이상의 구역을 다른 소속으로 구분해야 한다.
		// 부분 집합을 통해 최소 1개 이상의 구역을 다른 소속으로 만들 수 있는지 확인하고,
		// 만들 수 있는 경우라면 그 차이를 계산한다. --> 모든 경우를 계산해 차이의 최솟값을 최종 출력으로 마무리한다.
		
		ret = Integer.MAX_VALUE;
		// 전체 노드(구역) 수에서 절반까지만 부분 집합을 구성해도 모든 경우 판독 가능 (A, B의 두 그룹으로만 구분되므로)
		for (int group = 1; group <= N/2+1; group++) {
			comb(1, 0, group, new boolean[N+1]);  // teamA에 group 수만큼 구역을 소속시킨다.
		}
		
		System.out.println(ret); // 최종 출력
	}

	
	/*
	 *   start: 구성에 포함하는 시작 노드(구역)
	 *     cnt: 구성된 부분 집합의 원소 개수
	 *   group: 총 구성할 수 있는 부분 집합의 원소 개수
	 *   check: 사용한 노드(구역)를 사용 처리.
	 */
	private static void comb(int start, int cnt, int group, boolean[] check) {
		// 소속시킬 개수만큼 구역을 선택했다면, 해당 경우의 구역이 실제 만들 수 있는 경우인지 확인한다.
		// 실제 구성할 수 있는 구역의 부분 집합이라면 각 인구수를 계산해 차이를 비교한다.
		if (cnt == group) {
			//System.out.println(Arrays.toString(check));
			
			if (checkAble(check, group)) {
				int gap = calculation(check);
				if (ret > gap) ret = gap;
			}
			return;
		}

		for (int i = start; i <= N ; i++) {
			check[i] = true;
			comb(i+1, cnt+1, group, check);
			check[i] = false;
		}
	}


	
	/*
	 *   check: 사용한 노드(구역)에 대한 사용 처리 배열. A, B 그룹을 구분하는 역할 (true or false)
	 *   group: 하나의 그룹(A)에 포함되는 원소 수.  다른 그룹은 (N-group)이 된다.
	 */
	private static boolean checkAble(boolean[] check, int group) {
		int teamA = 0, teamB = 0, cnt = 0;;
		int teamAidx = 0, teamBidx = 0; 
		boolean[] used = new boolean[N+1];
		Queue<Integer> queue = new LinkedList<>();
		
		// true, false 각각 시작 노드를 찾아서 배치하고, 이후의 노드들을 검사
		for (int i = 1; i <= N; i++) {
			if (check[i] && teamAidx == 0)  teamAidx = i;
			if (!check[i] && teamBidx == 0)  teamBidx = i;
		}
		
		// teamA에 대한 검사 (cnt가 group과 같지 않다면 선거구를 만들 수 없는 것)
		queue.add(teamAidx);
		while(!queue.isEmpty() && cnt != group) {
			int target = queue.poll();
			
			if (!used[target] && check[target]) {
				used[target] = true;
				cnt++;
				queue.addAll(nodes[target]);
			}
		}
		if (cnt != group) return false;
		
		queue.clear();
		queue.add(teamBidx);
		cnt = 0;
		// teamB에 대한 검사
		while(!queue.isEmpty() && cnt != N-group) {
			int target = queue.poll();
			
			if (!used[target] && !check[target]) {
				used[target] = true;
				cnt++;
				queue.addAll(nodes[target]);
			}
		}
		if (cnt != N-group) return false;
		
		return true;
	}


	// 만들 수 있는 경우의 수라면 차이를 계산하고 return (ret와 비교해 업데이트)
	private static int calculation(boolean[] check) {
		int teamA = 0, teamB = 0;
		for (int i = 1; i <= N; i++) {
			if (check[i]) teamA += people[i];
			else teamB += people[i];
		}
		return Math.abs(teamB-teamA);
	}


	/*
	 *       idx: 최상위 부모가 될 노드(구역)
	 *   parents: 각 노드(구역)의 최상위 부모 정보를 담는 배열 (연결된 구역을 확인하는 역할로 사용)
	 *   
	 *    모든 구역이 서로 연결되어 있다면 최상위 노드(구역) 1개에 모두 속해있다.
	 *    하지만, 연결이 끊어진 상태면 각각의 구역이 나누어진다.
	 */
	private static void neighborCheck(int idx, int[] parents) {
		boolean[] used = new boolean[N+1];
		Queue<Integer> queue = new LinkedList<>();
		queue.addAll(nodes[idx]);
		used[idx] = true;
		
		while(!queue.isEmpty()) {
			int target = queue.poll();
			
			if (!used[target]) {
				used[target] = true;
				parents[target] = idx;
				queue.addAll(nodes[target]);
			}
		}
	}
}
