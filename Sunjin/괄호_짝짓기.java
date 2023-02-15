package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;


public class Solution{
	static int N, M;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		
		for(int testCase = 1; testCase <= 10; testCase++) {
			int N = Integer.parseInt(br.readLine());
			String str = br.readLine();
			
			
			HashMap<Character, Character> hashMap = new HashMap<>();
			hashMap.put('(', ')');
			hashMap.put('[', ']');
			hashMap.put('{', '}');
			hashMap.put('<', '>');
			Stack<Character> stack = new Stack<>();
			
			int result = 1;
			for(int i = 0; i < N; i++) {
				char ch = str.charAt(i);
				if(ch == '(' || ch == '[' || ch =='{' || ch == '<') {
					stack.push(ch);
				}
				if(ch == ')' || ch == ']' || ch == '}' || ch == '>') {
					char pick = stack.pop();
					if(ch != hashMap.get(pick)) {
						result = 0;
						break;
					}
				}
			}	
			System.out.printf("#%d %d\n", testCase, result);
		}
	}
}