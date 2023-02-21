// BOJ 16457 단풍잎 이야기

import java.io.*;
import java.util.*;
public class BAEK_16457 {
	
	static int N, M, K, ret;
	static int[][] quests;
	static boolean[] used;
	static int MaxN;
	static Set<Integer> skills = new HashSet<>();
	static Iterator<Integer> iter;
	static int[] limit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());  
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		quests = new int[M][K];
		used = new boolean[2*N+1];  // 스킬 등록 여부
		MaxN = 0;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int[] tmp = new int[K];

			for(int j = 0; j < K; j++) {
				int value = Integer.parseInt(st.nextToken());
				skills.add(value);
				tmp[j] = value;
				if (MaxN < value) MaxN = value;
			}
			quests[i] = tmp;
		}
		
//		for (int[] string : quests) {
//			System.out.println(Arrays.toString(string));
//		}

		int idx = 0;
		limit = new int[skills.size()];
		iter = skills.iterator();
		
		while(iter.hasNext()) {
			limit[idx++] = iter.next();
		}
		
		//System.out.println(Arrays.toString(limit));
		
		MakeSkillSet(0, 0);
		System.out.println(ret);
	}

	private static void MakeSkillSet(int start, int cnt) {
		if (cnt == N) {
			//System.out.println(Arrays.toString(used));
			checkAble();
			return;
		}
//		for (int i = 1; i < MaxN+1; i++) {
//			if (!skills.contains(i)) continue;
//			
//			if (!used[i]) {
//				used[i] = true;
//				MakeSkillSet(cnt+1);
//				used[i] = false;
//			}
//		}
		for (int i = start; i < limit.length; i++) {
			int value = limit[i];
			if (!used[value]) {
				used[value] = true;
				MakeSkillSet(i+1, cnt+1);
				used[value] = false;
			}
		}
		
		
//		while(iter.hasNext()) {
//			int value = iter.next();
//			System.out.println(value);
//			if (!used[value]) {
//				used[value] = true;
//				MakeSkillSet(cnt+1);
//				used[value] = false;
//			}
//		}
	}
	private static void checkAble() {
		int tmp = 0; // 적용할 수 있는 퀘스트 수
		
		// 퀘스트 목록 하나씩 꺼내서 used랑 비교하기
		L: for (int i = 0; i < M; i++) {
			int[] Q = quests[i];
			
			for (int j = 0; j < K; j++) {
				if (!used[Q[j]]) break L;
			}
			tmp += 1;
		}
		if (tmp > ret) ret = tmp;
	}
}
