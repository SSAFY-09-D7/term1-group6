package algorithm;

import java.io.*;
import java.util.*;

public class Main2 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st ;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
        	q.add(i);
        }
        
        Queue<Integer> result = new LinkedList<>();
        L:while(true) {
        	for(int i = 0; i < K; i++) {
        		if(q.size() == 1) {
        			result.add(q.poll());
        			break L;
        		}
        		if(i == K - 1) {
        			int value = q.poll();
        			result.add(value);
        		}
        		else {
        			int value = q.poll();
        			q.add(value);
        		}
        	}
        }
        
        sb.append('<');
        while(true) {
        	if(result.size() == 1) break;
        	sb.append(result.poll() + ", ");
        }
        sb.append(result.poll() + ">");
        System.out.println(sb);
    }
}