import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static int N;
	public static int[] arr;
	public static List<Integer> list;
	public static boolean[] visited1;
	public static boolean[] visited2;
	public static boolean[] ans;
	public static StringBuilder sb = new StringBuilder();
	
    public static void main(String[] args) throws Exception {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
  
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        list = new ArrayList<>();
        
        for(int i=1; i<=N; i++) {
        	arr[i] = Integer.parseInt(br.readLine());
        }
        
        ans = new boolean[N+1];
        int cnt = 0;
        
        for(int i=1; i<=N; i++) {
        	visited1 = new boolean[N+1];
        	visited2 = new boolean[N+1];
        	
        	boolean flag = true;
        	if(!ans[i]) {
        		
        		DFS(i);
        	}

        	for(int j=1; j<=N; j++) {
        		if(visited1[j] != visited2[j]) {
        			flag = false;
        		}
        	}
        	
        	if(flag) {
        		for(int j=1; j<=N; j++) {
        			if(visited1[j])
        				ans[j] = true;
        		}
        	}
        	
        }
        
        for(int i=1; i<=N; i++) {
        	if(ans[i]) {
        		cnt += 1;
        		sb.append(i).append("\n");
        	}
        		
        }
        System.out.println(cnt);
        System.out.println(sb);
        
    }
    
    public static void DFS(int i) {
    	
    	if(!visited1[i]) {
    		visited1[i] = true;
        	
        	if(!visited2[arr[i]]) {
        		visited2[arr[i]] = true;
        		DFS(arr[i]);
        	}
    	}
    	
    }
}