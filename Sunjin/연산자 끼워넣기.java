import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int max, min;
	static int[] arr;
	static int[] opCount;
	static int[] sel;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		opCount = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			opCount[i] = Integer.parseInt(st.nextToken());
		}
		
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		sel = new int[N - 1];
		perm(0);
		
		System.out.println(max);
		System.out.println(min);
		
	}
	private static void perm(int count) {
		if(count == sel.length) {
			int result = calc();
			if(result < min) min = result;
			if(max < result) max = result;
			return;
		}
		
		for(int i = 0; i < opCount.length; i++) {
			if(opCount[i] > 0) {
				opCount[i]--;
				sel[count] = i;
				perm(count + 1);
				opCount[i]++;
			}
		}
		
	}
	private static int calc() {
		int sum = arr[0];
		
		for(int i = 0; i < sel.length; i++) {
			int op = sel[i];
			int opr = arr[i + 1];
			// 더하기
			if(op == 0) {
				sum += opr;
			}
			else if(op == 1) {
				sum -= opr;
			}
			else if(op == 2) {
				sum *= opr;
			}
			else if(op == 3) {
				if(sum < 0) {
					// 음수로 바꾸고
					int tmp = -sum;
					tmp /= opr;
					sum = -tmp;
				}
				else {
					sum /= opr;
				}
			}
		}
		return sum;
	}
}
