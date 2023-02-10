package algorithm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static char[] chs;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str;
		
		while((str = br.readLine()) != null) {
			int N = Integer.parseInt(str);
			chs = new char[(int) Math.pow(3, N)];
			for(int i = 0; i < chs.length; i++) {
				chs[i] = '-';
			}
			recursive(0, chs.length);
			for(char ch : chs) {
				sb.append(ch);
			}
			sb.append("\n");
			System.out.print(sb);
			sb.delete(0, sb.length());
		}
	}
	
	private static void recursive(int start, int length) {
		if(length < 3) {
			return;
		}
		for(int i = start + length / 3; i < start + length / 3 * 2; i++) {
			chs[i] = ' ';
		}
		recursive(start, length / 3);
		recursive(start + length / 3 * 2, length / 3);
	}
}