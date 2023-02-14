// BAEKJOON 17413 "단어 뒤집기2"
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BAEK_17413 {
	// if stack not empty, print elements.
	private static void stk_check(Stack<String> stk) {
		int cnt;
		if (!stk.empty()) {
			cnt = stk.size();
			for (int i = 0; i < cnt; i++) {
				System.out.print(stk.pop());
			}
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] text = br.readLine().split("");
		Stack<String> stk = new Stack<>();
		
		boolean flag = false; // true: "<" is alive.
		int cnt;
		
		for (String str  : text) {
			if (str.equals("<")) {
				stk_check(stk); 
				flag = true;
				System.out.print(str);
				continue;
			}
			
			else if (str.equals(">")) {
				flag = false;
				System.out.print(str);
				continue;
			}
			
			// flag true = not reverse.
			if (flag) {
				System.out.print(str);
			}
			else {
				// if string is space => print stack elements.
				if (str.equals(" ")) {
					cnt = stk.size();
					for (int i = 0; i < cnt; i++) {
						System.out.print(stk.pop());
					}
					System.out.print(" ");
				}
				else {
					stk.add(str);
				}
			}
		}
		stk_check(stk);
	}
}