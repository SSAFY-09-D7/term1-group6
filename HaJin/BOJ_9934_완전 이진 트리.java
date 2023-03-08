import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {
    
    public static int K, N;
    public static int[] arr;
    public static List<Integer>[] list;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());
        N = (int)Math.pow(2, K) - 1;
        arr = new int[N];
        list = new ArrayList[1024];
        for(int i=0; i<1024; i++)
            list[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N ;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        func(0, N, 0);

        for(int i=0; i<K; i++){
            for(int j=0; j<list[i].size(); j++){
                System.out.print(list[i].get(j) + " ");
            }
            System.out.println();
        }

    }

    public static void func(int start, int end, int level){
        if(level == K){
            return;
        }

        int mid = (start + end) / 2;
        list[level].add(arr[mid]);

        func(start, mid-1, level + 1);
        func(mid+1, end, level+1);
    }

}