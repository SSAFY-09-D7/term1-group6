package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	static int G, P;
	static int[] set;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        
        set = new int[G + 1];
        for(int i = 1; i <= G; i++) {
        	set[i] = i;
        }
        int count = 0;
        boolean flag = true;
        for(int i = 0; i < P; i++) {
        	int comp = Integer.parseInt(br.readLine());
        	if(flag) {
            	if(find(comp) != 0) {
            		count++;
            		union(comp - 1, comp);
            	}
            	else flag = false;
        	}
        }
        System.out.println(count);
	}
	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
	
		if(pa != pb) {
			set[pb] = pa;
		}
		if(pa == pb) {
			set[pa] = set[pa - 1];
			set[pb] = set[pa - 1];
		}
	}
	private static int find(int a) {
		if(set[a] == a) return a;
		else return set[a] = find(set[a]);
	}
}