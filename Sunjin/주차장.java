import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 1][2];
        int[]cars = new int[M + 1];
        int parkCount = 0;
        int money = 0;

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            cars[i] = Integer.parseInt(st.nextToken());
        }
        Queue<Integer> waitQ = new LinkedList<>();
        int num;
        for(int i=0; i<2*M; i++) {
            num = Integer.parseInt(br.readLine());  

            if(num>0) {
                if(parkCount == N) {
                    waitQ.add(num);
                }
                else {
                    for(int j=1; j<=N; j++) {
                        if(arr[j][1] == 0) {
                            arr[j][1] = num;
                            parkCount += 1;
                            money += arr[j][0] * cars[num];
                            break;
                        }
                    }
                }
            }
            else {    
                for(int j=1; j<=N; j++) {
                    if(arr[j][1] == -num) {
                        if(waitQ.isEmpty()) {
                            arr[j][1] = 0;
                            parkCount -= 1;
                            break;
                        }
                        else {
                            int newC = waitQ.remove();
                            arr[j][1] = newC;
                            money += arr[j][0] * cars[newC];
                        }
                    }
                }
            }
        }
        System.out.println(money);
    }
}
