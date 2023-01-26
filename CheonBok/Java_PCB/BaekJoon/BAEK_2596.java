// 백준 2596 + 월말평가 5번  "비밀편지"
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner; 

public class BAEK_2596 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int cnt = sc.nextInt();  // input1: text count
		sc.nextLine();
		String[] text = sc.nextLine().split(""); // input2: text
		
	// --------------------------------------------------------------
		// defined letter set
		Map<String, Character> mlist = new HashMap<>();
		mlist.put("000000", 'A');
		mlist.put("001111", 'B');
		mlist.put("010011", 'C');
		mlist.put("011100", 'D');
		mlist.put("100110", 'E');
		mlist.put("101001", 'F');
		mlist.put("110101", 'G');
		mlist.put("111010", 'H');
	// --------------------------------------------------------------	
		
		String[] token = new String[cnt]; // array for separated String
		List<Character> Lret = new ArrayList<>(); // save the return letter
		boolean flag = false; // true: not found letter
		int idx = 0;
		
	// ---------------------------------------------------------------
		// separate token loop
		for (int i = 0; i < cnt; i++) {
			token[i] = String.join("", Arrays.copyOfRange(text, idx, idx+6));
			idx += 6;
		}
		
		idx = 1; // return value when the letter not found.
		
		// check String and find letter loop
		for (String t : token) {
			// not found case1
			if (t.equals("111111")) {
				System.out.println(idx);
				System.exit(0);
			}
			
			// found case
			else if (mlist.containsKey(t)) {
				Lret.add(mlist.get(t));
			}
			
			// found or not
			else {
				int bstr = Integer.parseInt(t, 2); // convert to binary
				
				// compare all elements in the HashMap
				for (String mstr : mlist.keySet()) {
					int astr = Integer.parseInt(mstr, 2);
					
					// XOR of two binary (ex. 101101 ^ 100011 = 1110)
					String[] returnv = String.valueOf(Integer.toBinaryString(astr^bstr)).split("");
					
					
					int onenum = 0; // the number of '1'
					for (String s : returnv) {
						if (s.equals("1")) onenum++;
					}
					
					if (onenum == 1) {
						flag = false;
						Lret.add(mlist.get(mstr));
						break;
					}
					else {
						flag = true;
					}
				}
				
				// if can't find letter -> print idx -> exit
				if (flag) {
					System.out.println(idx);
					System.exit(0);
				}
			}
			idx++;
		}
		// print loop
		for (Character charac : Lret) {
			System.out.print(charac);
		}
		
	}
}