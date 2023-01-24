// 백준 8320 직사각형을 만드는 방법
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BAEK_8320 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int ret = 0;
	
		for(int first=2; first<=N; first++)
			for(int floor=2; floor<=first; floor++)
				if(first*floor <= N)
					ret++;
		
		System.out.println(ret+N);
	}
}