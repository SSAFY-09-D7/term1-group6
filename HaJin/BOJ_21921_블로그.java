import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[] arr = new int[250005];
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		
		
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int sum = 0;
		
		
		for(int i=0; i<x; i++) {
			sum += arr[i];
		}
		int max = sum;
		int cnt = 1;
		
		for(int i=x; i<n; i++) {
			sum = sum - arr[i-x] + arr[i];
			
			if(sum > max) {
				max = sum;
				cnt = 1;
			}
			else if(sum == max) {
				cnt += 1;
			}
		}
		
		if(max == 0)
			System.out.println("SAD");
		else {
			System.out.println(max);
			System.out.println(cnt);
		}
			
		
	}
}

