import java.io.*;

public class Main {
	static int N;
	static StringBuilder sb;
	static int[] firstPrime = {2, 3, 5, 7};
	static int[] canPrime = {1, 3, 7, 9};
	public static void main(String[] args) throws IOException {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	sb = new StringBuilder();
    	
    	N = Integer.parseInt(br.readLine());
    	
    	for(int i = 0; i < firstPrime.length; i++) {
    		func(firstPrime[i], 0);
    	}
    	
    	System.out.println(sb);
    }
	
	private static void func(int n, int k) {
		// basis part
		
		// 소수가 아니면
		if(!isPrime(n)) {
			return;
		}
		
		if(k == N - 1) {
			sb.append(n + "\n");
			return;
		}

		
		// inductive part
		for(int i = 0; i < canPrime.length; i++) {
			func(n * 10 + canPrime[i], k + 1);
		}
		
		
	}
	
	// 소수 판단
	private static boolean isPrime(int num) {
		for(int i = 2; i <= Math.sqrt(num); i++) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
}