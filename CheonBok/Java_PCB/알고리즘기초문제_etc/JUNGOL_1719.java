// JUNGOL 1719  "º°»ï°¢Çü2"
import java.util.Scanner;

public class JUNGOL_1719 {
	// general print of star
	private static void print_star(int cnt) {
		for (int j = 0; j < cnt; j++) {
			System.out.print("*");
		}
	}
	// general print of space
	private static void print_space(int cnt) {
		for (int j = 0; j < cnt; j++) {
			System.out.print(" ");
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int type = sc.nextInt();
		
		
		if (N%2 == 0) { // exception case1
			System.out.println("INPUT ERROR!");
			System.exit(0);
		}
		
		
		if (type == 1) {
			for (int i = 1; i < N/2+2; i++) {
				print_star(i);
				System.out.println();
			}
			
			for (int i = N/2; i > 0; i--) {
				print_star(i);
				System.out.println();
			}
			
		}
		
		else if (type == 2) {
			for (int i = 1; i < N/2+2; i++) {
				for (int j = N/2+1-i; j > 0; j--) {
					System.out.print(" ");
				}
				
				print_star(i);
				System.out.println();
			}
			
			for (int i = N/2; i > 0; i--) {
				for (int j = N/2+1-i; j>0; j--) {
					System.out.print(" ");
				}
				
				print_star(i);
				System.out.println();
			}
			
		}
		
		else if (type == 3) {
			for (int i = 0; i < N/2+1; i++) {
				print_space(i);
				
				for (int j = 0; j < N-i*2; j++) {
					System.out.print("*");
				}
				
				print_space(i);
				System.out.println();
			}
			
			for (int i = N/2-1; i > -1; i--) {
				print_space(i);
				
				for (int j = 0; j < N-i*2; j++) {
					System.out.print("*");
				}
				
				print_space(i);
				System.out.println();
			}
			
		}
		
		else if (type == 4) {
			for (int i = 0; i < N/2+1; i++) {
				print_space(i);
				
				for (int j = 0; j<N/2+1-i; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			
			for (int i = 2; i < N/2+2; i++) {
				for (int j = 0; j < N/2; j++) {
					System.out.print(" ");
				}
				
				print_star(i);
				System.out.println();
			}
		}
		
		// exception case2
		else {
			System.out.println("INPUT ERROR!");
		}
	}
}
