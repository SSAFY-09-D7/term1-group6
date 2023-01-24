// JUNGOL 1523 "º°»ï°¢Çü1"
import java.util.Scanner;

public class JUNGOL_1523 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int type = sc.nextInt();
		
		
		if (N > 100) { // exception case1
			System.out.println("INPUT ERROR!");
			System.exit(0);
		}
		
		
		if (type == 1) {
			for (int i = 1; i < N+1; i++) {
				for (int j = 0; j < i; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
		}
		
		else if (type == 2) {
			for (int i = N; i >0; i--) {
				for (int j = N; j > N-i; j--) {
					System.out.print("*");
				}
				System.out.println();
			}
		}
		
		else if (type == 3) {
			for (int i = 1; i < N+1; i++) {
				for (int j = N; j >i; j--) {
					System.out.print(" ");
				}
				
				for (int j = 1; j < i*2; j++) {
					System.out.print("*");
				}
				
				for (int j = N; j >i; j--) {
					System.out.print(" ");
				}
				System.out.println();
			}
		}
		
		// exception case2
		else {
			System.out.println("INPUT ERROR!");
			System.exit(0);
		}
	}
}
