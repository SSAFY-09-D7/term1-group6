import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class BAEK_11279 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pQ = new PriorityQueue<>();
		int command;
		int round = Integer.parseInt(br.readLine());
		
		// The purpose of final return values
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		
		for (int i = 0; i < round; i++) {
			command = Integer.parseInt(br.readLine());
			
			
			if (command == 0) {
				// PriorityQueue.poll = pop (return and remove the root node)
				if (pQ.size() == 0) sb.append("0\n");
				else sb.append(-pQ.poll()+"\n");
			}
			else {
				pQ.add(-command);
			}
		}
			bw.write(sb.toString());
			bw.close();
	}

}
