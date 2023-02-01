import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = 1;
		while(true) {
			N = sc.nextInt();
			if(N == 0) break;
			boolean[] isPrime = new boolean[N * 2 + 1];
			isPrime[0] = true;
			isPrime[1] = true;
			int count = 0;
			for(int i = 2; i <= N * 2; i++) {
				if(isPrime[i] == false) {
					if(i > N) count++;
					for(int j = i + i; j <= N * 2; j += i) {
						isPrime[j] = true;
					}
				}
			}
			System.out.println(count);
			
		}
		sc.close();
    }
}
