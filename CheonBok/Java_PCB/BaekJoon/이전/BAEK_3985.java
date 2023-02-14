// 백준 3985 롤 케이크
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BAEK_3985 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int lolsize = Integer.parseInt(br.readLine());
		int[] lolcake = new int[lolsize+1];
		int[] lolcnt = new int[lolsize+1];
		String[] text;
		int start, end;
		int maxsize = 0;
		int predict = 0;
		int ret = 0;
		
		int people = Integer.parseInt(br.readLine());
		for (int idx = 1; idx < people+1; idx++) {
			text = br.readLine().split(" ");
			start = Integer.parseInt(text[0]);
			end = Integer.parseInt(text[1]);
			
			// predict max value(people) update
			if (end-start+1 > maxsize) {
				maxsize = end-start+1;
				predict = idx;
			}
			
			// insert the people number in lolcake.
			for (int k = start; k < end+1; k++) {
				if (lolcake[k] == 0) lolcake[k] = idx;
			}
		}
		
		System.out.println(predict);
		
		// getting number in lolcnt
		for (int idx = 1; idx < lolsize+1; idx++) {
			lolcnt[lolcake[idx]]++;
		}
		
		maxsize = 0;
		// real max value(people) update
		for (int idx = 1; idx < lolsize+1; idx++) {
			if (lolcnt[idx] > maxsize) {
				maxsize = lolcnt[idx];
				ret = idx;
			}
		}
		System.out.println(ret);
	}
}
