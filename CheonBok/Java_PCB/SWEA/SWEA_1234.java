import java.util.Scanner;

public class swea1234 {
	
	static int number = 0;  // The String size of return.

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test = 10;
		int cnt, idx;
		String[] input, arr, ret;
		boolean flag, isloop;
		
		for (int rnd = 1; rnd < test+1; rnd++) {
			input = sc.nextLine().split(" ");
			cnt = Integer.parseInt(input[0]); // String size
			arr = input[1].split(""); // input String
			number = cnt; // initialization
			flag = false; // update number token
			isloop = true; // update String check token
			
			while (true) {
				isloop = false; // String check deny.
				if (flag) { cnt = number;}
				
				
				for (int i = 0; i < cnt-1; i++) {
					// Check String are equal
					if (arr[i].equals(arr[i+1]) && !arr[i].equals("-1")) {
						arr[i] = "-1";
						arr[i+1] = "-1";
						number -= 2;
						check_equal(arr, i-1, i+2, cnt);
						isloop = true; // Need to check next while loop with updated String.
						flag = true; // Need to update String size.
					}
				}
				
				if (!isloop) { break; } // Not update anymore
				
				// ret: update new String.
				idx = 0;
				ret = new String[number];
				
				for (String s : arr) {
					if (s.equals("-1") == false) {
						ret[idx++] = s;
					}
				}
				arr = ret;
			}
			
			System.out.print("#"+rnd+" ");
			for (String s : arr) {
				System.out.print(s);
			}
			System.out.println();
			
		}
	}

	private static void check_equal(String[] arr, int left, int right, int cnt) {
		if (left >= 0  && right < cnt) {
			if (arr[left].equals(arr[right])) {
				arr[left] = "-1";
				arr[right] = "-1";
				number -= 2;
				check_equal(arr, left-1, right+1, cnt);
			}
			else { return; }
		}
		else { return; }
	}
}
