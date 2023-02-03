import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

// 백준 15828 Router
public class BAEK_15828 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		Queue<String> Q = new LinkedList<>();
		String token; 
		int size = 0; // Queue Statement
	// --------------------------------------------------------------------------	
		
		while (true) {
			token = br.readLine();
			
			// "-1" is exit program
			if (token.equals("-1")) break;
			
			// if Q isn't empty, add(offer) the value without "0"
			if (size < N && !token.equals("0")) {
				Q.offer(token);
				size++;
			}
			
			// "0" is poll(remove) the value of head
			else if (token.equals("0")) {
				Q.remove();
				size--;
			}
		}
		
		// print
		if (Q.isEmpty()) bw.write("empty");
		else {
			while (true) {
				// print the value without null
				String tmp = Q.poll();
				if (tmp != null) bw.write(tmp + " ");
				else break;
			}
		}
		bw.flush();
	}
}