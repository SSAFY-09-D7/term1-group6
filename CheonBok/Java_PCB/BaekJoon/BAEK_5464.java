// BAEK 5464 "주차장"
import java.io.*;
import java.util.*;

public class BAEK_5464 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Queue<Integer> wait = new LinkedList<>();  // waiting for parking
		boolean[] parking = new boolean[N]; // parking able state
		int[] Rs = new int[N];    // fee rate per weight
		int[] used = new int[N];  // parking state
		
		// 주차 공간 무게당 요금
		for (int i = 0; i < N; i++) {
			Rs[i] = Integer.parseInt(br.readLine());
		}
		
		// 개별 차량의 무게
		int[] Wk = new int[M+1];
		for (int i = 1; i < M+1; i++) {
			Wk[i] = Integer.parseInt(br.readLine());
		}
		
		int parkable = N; // the number of available state
		int fee = 0;
		
		
		for (int i = 0; i < 2*M; i++) {
			int flg = Integer.parseInt(br.readLine());
			
			// 양수 - 차량 주차
			if (flg > 0) {
				
				// full state = add car to the stack
				if (parkable == 0) {
					wait.add(flg);
					continue;
				}
				
				// check available parking station
				// low index = high priority
				for (int j = 0; j < N; j++) {
					if (parking[j] == false) {
						used[j] = flg;
						parkable--;
						parking[j] = true;
						break;
					}
				}
			}
			// 음수 - 차량 빼기
			else {
				flg = Math.abs(flg);
				int idx = -1;
				// flg와 일치하는 차량이 주차된 공간(idx) 확인
				for (int j = 0; j < used.length; j++) {
					if (used[j] == flg) {
						idx = j;
					}
				}
				// 해당 주차 공간(idx)의 요금 비율과 해당 차량(flg)의 무게 곱
				fee += Rs[idx] * Wk[flg];
				
				// if there is a car waiting
				// first in = high priority
				if (!wait.isEmpty()) {
					used[idx] = wait.poll();
				}
				
				// there is no car waiting
				else {
					parking[idx] = false;
					parkable++;
				}
			}
		}
		System.out.println(fee);	
	}
}
