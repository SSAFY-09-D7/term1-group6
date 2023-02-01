import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
public static char[][] arr = new char[1025][1025];
	
	public static void func(int start_i,int start_j,int n) {
		
		if(n==1)
			return;
		
		for(int i=0; i<n; i++) {
			// 윗면
			arr[start_i][start_j+i] = '*';
			// 옆면
			arr[start_i+i][start_j] = '*';
			// 대각선
			arr[start_i+i][start_j+n-i -1] = '*';
		}
		
		func(start_i, start_j, n/2);
		func(start_i + n/2, start_j, n/2);
		func(start_i, start_j + n/2, n/2);
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
        // 초기화
		for(int i=0; i<1025; i++) {
			for(int j=0; j<1025; j++)
				arr[i][j] = ' ';
		}
		
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		
		if(n==0) {
			System.out.print("*");
		}
		else {
			double a = Math.pow(2.0, n);
			int b = (int)a;
			
			func(0, 0, b);
			
			for(int i=0; i<b; i++) {
				for(int j=0; j<b-i; j++){
                    System.out.print(arr[i][j]);
                }
				System.out.println();
			
			}
		}
	}
}

