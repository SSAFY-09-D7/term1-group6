package algorithm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int Ans = 0;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		String str = br.readLine();
		
		arr = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		HashMap<Character, Integer> hmap = new HashMap<>();
		hmap.put('A', 0);
		hmap.put('C', 0);
		hmap.put('G', 0);
		hmap.put('T', 0);

		for(int i = 0; i < M; i++) {
			hmap.put(str.charAt(i), hmap.get(str.charAt(i)) + 1);
		}
		
//		Set<Character> keys = hmap.keySet();
//		for(Character key : keys) {
//			System.out.println(hmap.get(key));
//		}
		
		if(check(hmap)) Ans++;
		
		// 슬라이딩 윈도우
		
		for(int i = 1; i + M - 1 < N; i++) {
			char del = str.charAt(i - 1);
			char add = str.charAt(i + M - 1);
			
			hmap.put(del, hmap.get(del) - 1);
			hmap.put(add, hmap.get(add) + 1);
			if(check(hmap)) Ans++;
		}
		
		System.out.println(Ans);
	}
	
	// 조건에 맞는지 확인
	private static boolean check(HashMap<Character, Integer> hmap) {
		for(char key : hmap.keySet()) {
			if(key == 'A') {
				if(hmap.get('A') < arr[0]) return false;
			}
			if(key == 'C') {
				if(hmap.get('C') < arr[1]) return false;
			}
			if(key == 'G') {
				if(hmap.get('G') < arr[2]) return false;
			}
			if(key == 'T') {
				if(hmap.get('T') < arr[3]) return false;
			}
		}
		return true;
	}
}
