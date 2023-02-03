import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> queue = new LinkedList<>();
		int curPacketCount = 0;
		while(true) {
			int info = Integer.parseInt(br.readLine());
			if(info == -1) break;
			// info 번호의 패킷이 입력으로 들어옴
			if(info > 0) {
				if(curPacketCount < N) {
					queue.add(info);
					curPacketCount++;
				}
			}
			if(info == 0) {
				// 패킷 하나 처리함
				queue.remove();
				curPacketCount--;
			}
		}
		
		if(queue.isEmpty()) {
			sb.append("empty");
		}
		else {
			while(!queue.isEmpty()) {
				sb.append(queue.poll() + " ");
			}
		}
		System.out.println(sb);
	}
}
