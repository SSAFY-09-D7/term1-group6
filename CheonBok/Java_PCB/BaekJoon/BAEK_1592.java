import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BAEK_1592 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] text = br.readLine().split(" ");
		int N = Integer.parseInt(text[0]); // number of player
		int M = Integer.parseInt(text[1]); // number of catch
		int L = Integer.parseInt(text[2]); // depth
		int[] player = new int[N];
		player[0] = 1; // start player
		
		int cnt = 0; // throw count
		int maxcnt = 1; // archive catch.
		int idx = 0; // start idx
		
		while (player[idx] != M) {
			cnt += 1;
			if (player[idx] % 2 == 0) { // reverse direction
				if (idx < L) { idx = N+idx-L; }
				else { idx -= L; }
			}
			else if (player[idx] % 2 != 0) {
				idx = (idx+L)%N;
			}
			player[idx]++;
		}
		System.out.println(cnt);
	}
}