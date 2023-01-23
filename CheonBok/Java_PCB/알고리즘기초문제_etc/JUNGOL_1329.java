// JUNGOL 1329 "º°»ï°¢Çü3"
import java.util.Scanner;

public class JUNGOL_1329 {
	
	public static void print_star(int cnt) {
		for (int j = 1; j < cnt; j++) {
			System.out.print(" ");
		}
		
		for (int j = 0; j < cnt*2-1; j++) {
			System.out.print("*");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		if (N%2 == 0 || N > 100) {
			System.out.println("INPUT ERROR!");
			System.exit(0);
		}
		

		for (int i = 1; i < N/2+2; i++) {
			print_star(i);
		}
		
		for (int i = N/2; i>0; i--) {
			print_star(i);
		}
	}
}
