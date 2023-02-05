import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class BAEK_1268 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] maparr = new int[N][5]; 
		int[][] friends = new int[N][N]; 
		
		for (int i = 0; i < N; i++) {
			String[] tmp = br.readLine().split(" ");
			for (int j = 0; j < 5; j++) {
				maparr[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		
		int idx = 0;
		while (idx < N) {
			
			for (int col = 0; col < 5; col++) {
				int S = maparr[idx][col];
			
				for (int row = 0; row < N; row++) {
					if (idx == row) friends[idx][row] = 0;
					else if (friends[idx][row] != 0) continue;
					else {
						if (S == maparr[row][col]) {
							friends[idx][row] = 1;
							friends[row][idx] = 1;
						}
					}
				}
			}
			idx++;
		}
	
		int max = 0;
		int ret = 0;
		for (int i = 0; i < friends.length; i++) {
			int tmp = 0 ;
			for (int j = 0; j < friends.length; j++) {
				if (friends[i][j] == 1) tmp++;
			}
			if (max < tmp) {
				max = tmp;
				ret = i+1;
			}
		}
		
		if (ret == 0) System.out.println("1");
		else System.out.println(ret);
	}
}
