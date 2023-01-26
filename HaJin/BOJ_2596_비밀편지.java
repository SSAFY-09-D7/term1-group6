import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		boolean success = true;
		String[] str = new String[8];
		List<Integer> ans = new ArrayList<>();
		int ans_idx=0;
		int cnt_sub=0;
		
		str[0] = "000000";
		str[1] = "001111";
		str[2] = "010011";
		str[3] = "011100";
		str[4] = "100110";
		str[5] = "101001";
		str[6] = "110101";
		str[7] = "111010";
		
		int n = sc.nextInt();
		
		String s = sc.next();
		
		for(int i=0; i<n; i++) {
			cnt_sub=0;
			for(int k=0; k<8; k++) {
				int sub = 0;
				for(int j=0; j<6; j++) {
					if(str[k].charAt(j) != s.charAt(i*6 + j)) {
						sub++;
					}
				}
				if(sub <= 1) {
					ans.add(k);
					break;
				}
				else {
					cnt_sub++;
				}
			
			}
			if(cnt_sub == 8) {
				success = false;
				ans_idx = i;
				break;
			}
		}
		
		if(!success) {
			System.out.println(ans_idx+1);
		}
		else {
			for(int i=0; i<ans.size(); i++) {
				if(ans.get(i) == 0)
					System.out.print("A");
				else if(ans.get(i) == 1)
					System.out.print("B");
				else if(ans.get(i) == 2)
					System.out.print("C");
				else if(ans.get(i) == 3)
					System.out.print("D");
				else if(ans.get(i) == 4)
					System.out.print("E");
				else if(ans.get(i) == 5)
					System.out.print("F");
				else if(ans.get(i) == 6)
					System.out.print("G");
				else if(ans.get(i) == 7)
					System.out.print("H");
			}
		}
	}
}

