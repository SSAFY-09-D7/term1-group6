import java.io.*;
import java.util.*;

public class DP_BOJ_12865 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
 
		int N = Integer.parseInt(st.nextToken());  // 물품의 수
		int M = Integer.parseInt(st.nextToken());  // 담아갈 수 있는 무게
		int[][] dpTable = new int[N+1][M+1];
		int[] Weights = new int[N+1];
		int[] Values  = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			
			Weights[i] = W;
			Values[i] = V;
		}
		
		for (int w = 1; w <= N ; w++) {      // 무게 탐색
			for (int v = 1; v <= M; v++) {   // 가용한 모든 무게의 경우에 최대 이익
				// 담을 수 없는 무게 상태면 이전의 최대 이익 결과를 가져온다.
				if (v < Weights[w]) dpTable[w][v] = dpTable[w-1][v];
				else {
					// 담기가 가능한 무게 상태면 2가지 경우를 고려해 최대 이익을 계산
					// 1. 담아갈 수 없어 버리는 것이 이득이면 이전 결과를 가져간다.
					// 2. 담아갈 수 있다면 이전 상태에서 담기 전의 가치에 현재 가치를 더해서 이득을 계산한다.
					dpTable[w][v] = Math.max(dpTable[w-1][v], dpTable[w-1][v- Weights[w]] + Values[w]);
				}
				
			}
		}
		System.out.println(dpTable[N][M]);
	}
}