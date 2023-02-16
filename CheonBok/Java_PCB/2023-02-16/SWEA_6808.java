// SWEA 6808 규영이와 인영이의 카드게임
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class SWEA_6808
{
	static int[] mycards;  // 규영의 카드 Set
	static int win, lose;  // 최종 승 패 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int Tcase = Integer.parseInt(br.readLine());
		int idx;
		
		for (int T = 1; T < Tcase+1; T++) {
			
		// ---------------------------------------
			// 테스트 케이스 때마다 초기화
			boolean[] cards = new boolean[19]; // 카드 번호는 1~18. 인덱스를 카드 번호와 매칭
			int[] others = new int[9];         // 인영이의 카드 Set
			mycards = new int[9];              // 규영이의 카드 Set
			win = 0;
			lose = 0;
		// ---------------------------------------
			st = new StringTokenizer(br.readLine());
			idx = 0;
			for (int i = 0; i < 9; i++) {
				int tmp = Integer.parseInt(st.nextToken());
				cards[tmp] = true;     // 규영이가 가지고 있는 카드는 true 처리
				mycards[idx++] = tmp;
			}
			
			idx = 0;
			// 인영은 규영이가 가지고 있지 않은 카드를 가진다.
			// cards에서 false를 가진 인덱스(카드번호)는 인영의 카드 Set으로 추가한다.
			for (int i = 1; i < 19; i++) {
				if (!cards[i]) others[idx++] = i;
			}
			
			
			/*
			 *   permutation() : 인영이가 가진 카드에서 뽑아낼 수 있는 모든 경우의 순열을 찾아낸다.
			 *   
			 *   Next Permutation을 완벽히 사용하기 위해서는 (모든 경우의 수를 전부 뽑아내기 위해서는)
			 *   정렬을 먼저 수행하고 진행해야 한다.  -> 위에서 others에 값을 넣는 방식이 자연스럽게 정렬을 포함한다.
			 *   
			 *   gameStart() : 규영이의 카드와 순열로 뽑아낸 인영의 카드를 바탕으로 게임 진행
			 *
			 */
			do {
				gameStart(others);
				//System.out.println(Arrays.toString(others));
			}
			while (permutation(others));
			
			sb.append("#"+T+" "+win+" "+lose+"\n");
		}
		
		System.out.println(sb);
	}


	/*
	 *   others : 인영이가 가지고 있는 카드 리스트에서 만들어진 순열 중 하나
	 *   
	 *   my  : 규영이가 게임에서 이긴 경우 갖게 되는 점수의 합
	 *   you : 인영이가 게임에서 이긴 경우 갖게 되는 점수의 합
	 */
	private static void gameStart(int[] others) {
		int my = 0;
		int you = 0;
		for (int i = 0; i < 9; i++) {
			if (mycards[i] > others[i]) my += mycards[i] + others[i];
			else you += mycards[i] + others[i];
		}
		
		if (my > you) win++;
		else lose++;
		
	}

	// 총 9개의 카드에서 뽑아낼 수 있는 순열을 찾아낸다.
	// Next Permutation 방식은 입력으로 들어온 리스트(cardSet)를 바탕으로
	// 다음으로 추출할 수 있는 순열을 계산한다.
	private static boolean permutation(int[] cardSet) {
		// 오름차순 정렬되어 있는 리스트에서 가능한 모든 순열 계산하기
		
		int topidx = 8; // 뒤에서부터 시작 (마지막 index)
		// 현재 값이 앞의 값보다 작거나 같으면 index를 이동  =  가장 큰 값을 가지는 index를 찾는 과정
		while(topidx > 0 && cardSet[topidx-1] >= cardSet[topidx]) --topidx;
		if (topidx == 0) return false; // 가장 큰 순열의 형태라면 종료
		
		int sideidx = 8;
		// 가장 큰 값이 있는 인덱스(topidx)를 기준으로 앞에 있는 값보다 큰 값의 인덱스를 찾는다.
		while(sideidx > 0 && cardSet[topidx-1] >= cardSet[sideidx]) --sideidx;
		
		// 선택된 두 인덱스의 카드를 바꾼다.
		swap(cardSet, topidx-1, sideidx);
		
		// top index를 기준으로 우측을 오름차순 정렬
		int lastidx = 8;
		while (topidx < lastidx) {
			swap(cardSet, topidx++, lastidx--);
		}
		
		return true;

	}

	private static void swap(int[] cardSet, int topidx, int sideidx) {
		int tmp = cardSet[topidx];
		cardSet[topidx] = cardSet[sideidx];
		cardSet[sideidx] = tmp;
	}
}