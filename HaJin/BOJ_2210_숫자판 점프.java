import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static char[][] arr;
	public static Set<String> set;
	public static int[] nr = {-1, 1, 0, 0};
	public static int[] nc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		arr = new char[5][5];
		set = new HashSet<>();
		
		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				arr[i][j] = st.nextToken().charAt(0);
			}
		}
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				String s = "";
				func(i, j, s + arr[i][j]);
			}
		}
		
		System.out.println(set.size());
	}
	
	public static void func(int i, int j, String s) {
		if(s.length() == 6) {
			set.add(s);
			return;
		}
		
		for(int p=0; p<4; p++) {
			int ii = i + nr[p];
			int jj = j + nc[p];
			
			if(ii>=0 && ii<5 && jj>=0 && jj<5) {
				func(ii, jj, s+arr[ii][jj]);
			}
		}
	}

}