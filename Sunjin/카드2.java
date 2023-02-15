package algorithm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
        	q.offer(i);
        }
        
        int result = 0;
        int value = 0;
        while(true) {
        	if(q.size() == 1) {
        		result = q.poll();
        		break;
        	}
        	if(q.size() == 2) {
        		q.poll();
        		result = q.poll();
        		break;
        	}
        	q.poll();
        	value = q.poll();
        	q.offer(value);
        }
        System.out.println(result);
    }
}package algorithm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
        	q.offer(i);
        }
        
        int result = 0;
        int value = 0;
        while(true) {
        	q.poll();
        	if(q.size() == 1) {
        		result = q.poll();
        		break;
        	}
        	value = q.poll();
        	q.offer(value);
        }
        System.out.println(result);
    }
}