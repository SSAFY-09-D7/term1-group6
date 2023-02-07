import java.io.*;
import java.util.*;

public class Main {

    public static int N, K;
    public static int[] arr;
    public static int cnt = 0;
    public static int[] tmp;

    public static int ans;

    public static void merge_sort(int start, int end){
        if(start < end){
            int mid = (start + end)/2 ;

            merge_sort(start, mid);
            merge_sort(mid + 1, end);
            merge(start, mid, end);
        }
    }

    public static void merge(int start, int mid, int end){
        int i = start;
        int j = mid + 1;
        int t = 1;

        while(i <= mid && j <= end){
            if(arr[i] <= arr[j])
                tmp[t++] = arr[i++];
            else
                tmp[t++] = arr[j++];
        }

        while(i <= mid)
            tmp[t++] = arr[i++];
        while(j <= end)
            tmp[t++] = arr[j++];

        i = start;
        t = 1;
        while(i<= end){
            arr[i++] = tmp[t++];
            cnt+=1;
            if(cnt == K)
                ans = arr[i-1];
        }

    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N*2];
        tmp = new int[N*2];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        merge_sort(0, N-1);

        if(cnt < K)
            System.out.println("-1");
        else
            System.out.println(ans);

    }

}

