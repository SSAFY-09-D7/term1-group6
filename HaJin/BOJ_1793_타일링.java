import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		BigInteger[] dp = new BigInteger[251];
		dp[0] = new BigInteger("1");
		dp[1] = new BigInteger("1");
		dp[2] = new BigInteger("3");
        
		for(int i=3; i<=250; i++) {
			dp[i] = dp[i-1].add(dp[i-2].multiply(BigInteger.valueOf(2)));
		}
		
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()) {
			int n = sc.nextInt();
			System.out.println(dp[n]);	
		}
	}
}