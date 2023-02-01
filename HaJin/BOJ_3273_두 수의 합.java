import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] arr = new int[2000005];
        int cnt = 0;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int a;
        for(int i=0; i<n; i++){
            a = Integer.parseInt(st.nextToken());
            arr[a] += 1;
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
  
        for(int i=0; i<2000005; i++){
            if(i > x)
                break;
            if(arr[i] != 0){
                int need = x - i;
                if(arr[need] != 0)
                    cnt += 1;
            }
        }
        System.out.println(cnt / 2);
    }
}

