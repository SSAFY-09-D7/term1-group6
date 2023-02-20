import java.io.*;
import java.util.*;

public class Main {

    public static int N;
    public static int[][] arr;

    public static int func(int i, int j, int n){
        if(n == 2){
            List<Integer> tmpArr = new ArrayList<>();
            tmpArr.add(arr[i][j]);
            tmpArr.add(arr[i][j+1]);
            tmpArr.add(arr[i+1][j]);
            tmpArr.add(arr[i+1][j+1]);

            Collections.sort(tmpArr);
            return tmpArr.get(2);
        }

        List<Integer> tmpArr2 = new ArrayList<>();
        tmpArr2.add(func(i, j, n/2));
        tmpArr2.add(func(i, j + n/2, n/2));
        tmpArr2.add(func(i + n/2, j, n/2));
        tmpArr2.add(func(i + n/2, j + n/2, n/2));

        Collections.sort(tmpArr2);

        return tmpArr2.get(2);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(func(0, 0, N));

    }
}