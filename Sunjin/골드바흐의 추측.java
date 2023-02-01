import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine());
			// false : 소수
			boolean[] isPrime = getPrime(n);
			int[] result = getResult(isPrime, n / 2);
			System.out.println(result[0] + " " + result[1]);
		}
	}

	private static int[] getResult(boolean[] isPrime, int n) {
		int[] result = new int[2];
		
		int left = n;
		int right = n;
		
		for(int i = 0; i < n; i++) {
			if(!isPrime[left - i] && !isPrime[right + i]) {
				result[0] = left - i;
				result[1] = right + i;
				break;
			}
		}
		return result;
		
	}

	private static boolean[] getPrime(int n) {
		boolean[] isPrime = new boolean[n + 1];
		isPrime[0] = isPrime[1] = true;
		for(int i = 2; i <= n; i++) {
			if(isPrime[i] == false) {
				for(int j = i + i; j <= n; j += i) {
					isPrime[j] = true;
				}
			}
		}
		return isPrime;
	}
}
