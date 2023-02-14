package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


public class Solution{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for(int testCase = 1; testCase <= 10; testCase++) {
			int N = Integer.parseInt(br.readLine());
			
			Queue<Integer> q = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 8; i++) {
				int input = Integer.parseInt(st.nextToken());
				q.offer(input);
			}
			
			int idx = 1;
			while(true) {
				int value = q.poll();
				value -= idx;
				
				if(value <= 0) {
					q.offer(0);
					break;
				}
				else q.offer(value);
				
				if(idx == 5) idx = 1;
				else idx++;
			}
			
			System.out.printf("#%d",testCase);
			while(!q.isEmpty()) {
				System.out.print(" " + q.poll());
			}
			System.out.println();
		}
	}
}