import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[100][100];
		
		int count = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			for(int j = row; j < row + 10; j++) {
				for(int k = col; k < col + 10; k++) {
					map[j][k] = 1;
				}
			}
		}
		for(int myRow = 0; myRow < 100; myRow++) {
			for(int myCol = 0; myCol < 100; myCol++) {
				if(map[myRow][myCol] == 1) count++;
			}
		}
		System.out.println(count);
	}

}
