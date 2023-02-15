// 백준 2559 수열
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BAEK_2559 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
	// ---------------Case Input Area --------------
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		
		List<Integer> arr = new ArrayList<>();
		input = br.readLine().split(" ");
		
		for (int i = 0; i < input.length; i++) {
			arr.add(Integer.parseInt(input[i]));
		}
	// ----------------------------------------------
		
		// The minimum value of Integer
		int maxRet = Integer.MIN_VALUE;  
		
		int s = 0; // Prefix Sum
		
		// Initialization s
		for (int start = 0; start < K; start++) {
			s += arr.get(start);
		}
		
		// compare MIN_VALUE and s (Initialization maxRet)
		maxRet = Math.max(maxRet, s);
		
		// add next index, sub first index of s
		int idx = 0; // start index
		// next = end index
		for (int next = K; next < N; next++) {
			s += (arr.get(next) - arr.get(idx));
			maxRet = Math.max(maxRet, s);
			idx++;
		}
	
		System.out.println(maxRet);
	}
}
