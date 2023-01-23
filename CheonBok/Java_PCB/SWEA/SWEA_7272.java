import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class swea7272 {

	public static void main(String[] args) {
		// Same set
		HashSet<String> hset_one = new HashSet<>(Arrays.asList("A","D","O","P","Q","R"));
		HashSet<String> hset_no = new HashSet<>(Arrays.asList("C","E","F","G","H","I",
				"J","K","L","M","N","S","T","U","V","W","X","Y","Z"));
		
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		sc.nextLine();
		
		String[] text;
		int Alen, Blen;
		String[] Atext, Btext;
		boolean flag;
		
		for (int rnd = 1; rnd < test+1; rnd++) {
			flag = true;
			text = sc.nextLine().split(" ");
			
			Alen = text[0].length();
			Blen = text[1].length();
			
			// two text set's len are different = DIFF (case1)
			if (Alen != Blen) { System.out.println("#"+rnd+" DIFF"); continue; }
			
			Atext = text[0].split("");
			Btext = text[1].split("");
		
			// compare loop
			for (int i = 0; i < Alen; i++) {
				// two text hashsets are different (case2)
				if (hset_one.contains(Atext[i])) {
					if (!hset_one.contains(Btext[i])) {
						flag = false;
						break;
					}
				}
				
				// (case3)
				else if (hset_no.contains(Atext[i])) {
					if (!hset_no.contains(Btext[i])) {
						flag = false;
						break;
					}
				}
				
				// (case4)
				else if (Atext[i].equals("B") != Btext[i].equals("B")) {
					flag = false;
					break;
				}
			}
			if (flag) { System.out.println("#"+rnd+" SAME"); }
			else { System.out.println("#"+rnd+" DIFF"); }
		}
	}
}
