import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        HashMap<Integer, Integer> fee = new HashMap<>();
        for(int i = 1; i <= N; i++) {
        	int rs = Integer.parseInt(br.readLine());
        	fee.put(i, rs);
        }
        
        HashMap<Integer, Integer> weight = new HashMap<>();
        for(int i = 1; i <= M; i++) {
        	int wk = Integer.parseInt(br.readLine());
        	weight.put(i, wk);
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= 2 * M; i++) {
        	int order = Integer.parseInt(br.readLine());
        	q.add(order);
        }
        
        HashMap<Integer, Integer> visited = new HashMap<>();
        for(int i = 1; i <= N; i++) {
        	visited.put(i, null);
        }
        
        // 대기 큐
        Queue<Integer> waitQ = new LinkedList<>();
        int sum = 0;
        while(!q.isEmpty()) {
        	int value = q.poll();
        	// 들어가는 경우
        	L :if(value > 0) {
        		for(int key : visited.keySet()) {
        			if(visited.get(key) == null) {
        				visited.put(key, waitQ.poll());
        				break L;
        			}
        		}
        		waitQ.add(value);
        	}
        	// 나가는 경우
        	if(value < 0) {
        		for(int key : visited.keySet()) {
        			if(visited.get(key) == Integer.valueOf(-value)) {
        				sum += fee.get(Integer.valueOf(key)) * weight.get(Integer.valueOf(-value));
        				if(waitQ.size() == 0) {
        					visited.put(key, null);
        					break;
        				}
        				visited.put(key, waitQ.poll());
        				break;
        			}
        		}
        	}
        }
        System.out.println(sum);
    }
}
