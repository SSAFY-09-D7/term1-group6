import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int N;
	// 테두리 부분 구하기 위해 배열 크기 101*101로 설정
	public static int[][] arr = new int[101][101];
	public static int[] nr = {-1, 1, 0, 0};
	public static int[] nc = {0, 0, -1, 1};
	public static int cnt = 0;

	
	public static void func(int i, int j) {
		
		for(int p=0; p<4; p++) {
			int ii = i + nr[p];
			int jj = j + nc[p];
			
			// 4방탐색 후 101*101 범위 벗어나지 않고 값이 1이면 +1
			if(ii>=0 && ii<101 && jj>=0 && jj<101 && arr[ii][jj]==1) {
				cnt += 1;
			}
		}
		
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 검은색 천 부분만 1로 초기화
			for(int j=b; j<b+10; j++) {
				for(int k=a; k<a+10; k++) {
					arr[j][k] = 1;
				}
			}
		}
		
		// arr 0인 부분만 func 함수 실행
		for(int i=0; i<101; i++) {
			for(int j=0; j<101; j++) {
				if(arr[i][j]==0)
					func(i, j);
			}
		}

		System.out.println(cnt);

	}

}
