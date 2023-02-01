import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BAEK_3273 {

	public static void main(String[] args) throws NumberFormatException, IOException {
	// --------------- available index size needs for insert the x value ----------------
	// --------------- intL is the Array to prove the value is existed   ----------------
		StringTokenizer st;
		int ret = 0;
		int endidx;
		int[] intL = new int[2000005];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			intL[Integer.parseInt(st.nextToken())] = 1;
		}
	// --------------------------------------------------------------------------------	
		
		int x = Integer.parseInt(br.readLine());
		
		// search the index of an operand that can be added  = endidx	
		if (x%2 == 1) endidx = x/2+1;
		else endidx = x/2;
		
		// find values which can be added in between 1 and endidx area
		for (int j = 1; j < endidx; j++) {
			if (intL[j] == 1 && intL[x-j] == 1) ret++;
		}

		System.out.println(ret);
	}
}