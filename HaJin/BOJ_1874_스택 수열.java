import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		//1874
		
		List<Character> ans = new ArrayList<>();
		List<Integer> input_num = new ArrayList<>();
		Stack<Integer> st = new Stack<>();
		boolean[] check = new boolean[100003];
		boolean success = true;
		int cnt=0;
		
		int n = sc.nextInt();
		
		for(int i=0; i<n; i++) {
			int a = sc.nextInt();
			input_num.add(a);
		}
		int idx = 0;
		
		// 첫번째 원소 처리
		int now_num = input_num.get(idx);
		
		for(int i=1; i<=now_num; i++) {
			st.push(i);
			check[i] = true;
			ans.add('+');
		}
		
		st.pop();
		ans.add('-');
		cnt++;
		
		while(true) {
			if(idx>=n-1)
				break;
			
			if(input_num.get(idx) > input_num.get(idx+1)) {
                // ==  -->  .equals
				if(st.peek().equals(input_num.get(idx+1) )) {
					st.pop();
					ans.add('-');
					cnt++;
				}
				else {
					success = false;
					break;
				}
			}
			else if(input_num.get(idx) < input_num.get(idx+1)) {
				for(int i=input_num.get(idx)+1; i<=input_num.get(idx+1); i++) {
					if(!check[i]) {
						st.push(i);
						ans.add('+');
						check[i] = true;
					}
				}
				st.pop();
				ans.add('-');
				cnt++;
			}
			idx++;
		}
		
		if(success && cnt == n) {
			for(int i=0; i<ans.size(); i++) {
				System.out.println(ans.get(i));
			}
		}
		else
			System.out.println("NO");
			
			
		
	}
}

