import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BAEK_2798 {
	
	static int MINV = Integer.MAX_VALUE;
	static int MAXI;
	static int RET;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		int N = Integer.parseInt(str[0]);
		MAXI = Integer.parseInt(str[1]);
		int[] arr = new int[N];
		boolean[] visited = new boolean[N];
		
		str = br.readLine().split(" ");
		for (int i = 0; i < N; i++) 
			arr[i] = Integer.parseInt(str[i]);		
		
		// Configure 3 cards during all cards are used.
		comb(arr, visited, 0, N, 3);
		System.out.println(RET);
	}
	
	static void comb(int[] arr, boolean[] visited, int start, int N, int cnt){
		if (cnt == 0) { // Configured 3 cards, blackjack start.
			blackjack(arr, visited, N);
			return;
		}
		
		for (int i = start; i < N; i++) {
			visited[i] = true; // using
			comb(arr, visited, i+1, N, cnt-1); // comb next card
			visited[i] = false; // end use
		}
	}
	
	static void blackjack(int[] arr, boolean[] visited, int N) {
        int total = 0;
        int diff = 0;
		for (int i = 0; i < N; i++) { // sum cards
            if (visited[i]) {
                total += arr[i];
            }
        }
        if (total <= MAXI) {
        	diff = MAXI - total;
        	
        	if (diff < MINV) {
        		MINV = diff;
        		RET = total;
        	}
        }
    }
}
