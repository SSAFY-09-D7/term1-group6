// 백준 1789 수들의 합
import java.util.Scanner;

public class BAEK_1789 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long number = sc.nextLong();
		long start = 1;
		int cnt = 0;
		
		while (true) {
			number -= start++;
			
			// Find the last number.
			if (number < 0){
				System.out.println(cnt);
				break;
			}
			cnt++;
		}
	}
}
