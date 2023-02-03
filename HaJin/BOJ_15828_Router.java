import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Queue<Integer> que = new LinkedList<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
	
		while(true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			
			if(a == -1)
				break;
			
			if(a == 0)
				que.remove();
			
			else {
				if(que.size()>=n)
					continue;
				else {
					que.add(a);
				}
			}
		}
		
		if(que.isEmpty())
			System.out.println("empty");
		else {
			Iterator iter = que.iterator();
			while(iter.hasNext())
				System.out.print(iter.next() + " ");
		}
	}
}
