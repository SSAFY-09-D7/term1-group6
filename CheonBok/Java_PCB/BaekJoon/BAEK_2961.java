// 백준 2961 도영이가 만든 맛있는 음식
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// material class that stores N resources
// sour = 신맛,  pain = 쓴맛
class material {
	int sour;
	int pain;
	
	public material(int x, int y) {
		this.sour = x;
		this.pain = y;
	}
}

public class BAEK_2961 {
	
	// 최소 차이 저장 (출력)
	static int gap = Integer.MAX_VALUE;
	//static int round = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		
	// --------------------------------------------------------------------
	// resources are stored in the material Array.
		material[] mat = new material[N];
		String[] line;
		
		for (int i = 0; i <N; i++) {
			line = br.readLine().split(" ");
			mat[i] = new material(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
		}
		
	// ---------------------------------------------------------------------
		comb(mat, 0, new boolean[N]);
		
		System.out.println(gap);
		//System.out.println("계산에 사용된 boolean 조합 개수 = "+round);
		
	}

	/*
	 *    mat  : material Array
	 *    idx  : start index of mat
	 *    used : Check selected resources.
	 */
	private static void comb(material[] mat, int idx, boolean[] used) {
		// return when idx is last index
		if (idx == mat.length) {
			return;
		}
		
		// 재료들을 최소 1개 이상씩 담아보고, 즉각적으로 신맛과 쓴맛의 차이를 확인
		for (int i = idx; i < mat.length; i++) {
			if (used[i] == false) {
				used[i] = true;
				
				//subtraction of sour and bitter
				checkValue(mat, used);   
				
				comb(mat, i+1, used);  // 다음 재료 추가해보기 (재귀)
				used[i] = false;
			}
		}
	}


	private static void checkValue(material[] mat, boolean[] used) {
		//round++;
		//System.out.println("검사를 위한 used = "+Arrays.toString(used));
		int s_points = 1;  // sour points
		int p_points = 0;  // pain points
		
		for (int i = 0; i < used.length; i++) {
			if (used[i] == true) {
				s_points *= mat[i].sour;
				p_points += mat[i].pain;
			}
		}
		
		int points = Math.abs(s_points - p_points); 
		
		//System.out.println("계산 결과 = "+points);
		gap = Math.min(gap, points);
	}
}
