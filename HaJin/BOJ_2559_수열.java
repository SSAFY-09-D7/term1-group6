import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr= new int[100003];
        int[] ans = new int[100003];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // ans[0] 구하기
        for(int i=0; i<k; i++){
            ans[0] += arr[i];
        }

        int max = ans[0];

        // 나머지 항 구하기
        for(int i=1; i<n-k+2; i++){
            ans[i] = ans[i-1] - arr[i-1] + arr[i+k-1];
        }

        for(int i=0; i<n+1-k; i++){
            if(max < ans[i])
                max = ans[i];
        }

        System.out.println(max);
    }
}

