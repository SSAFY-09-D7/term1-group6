import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static StringBuilder sb = new StringBuilder();
    static int[][] arr;
    static int[] cars;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        Queue<Integer> queue = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());    // 주차장 번호
        M = Integer.parseInt(st.nextToken());    // 차 M대

        arr = new int[N+1][2];
        cars = new int[M+1];
        int nowParking = 0;
        int money = 0;

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            cars[i] = Integer.parseInt(st.nextToken());
        }

        int a;
        for(int i=0; i<2*M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());    // 차 번호

            if(a>0) {
                // 주차장 다 차있는 경우
                if(nowParking == N) {
                    queue.add(a);
                }
                else {
                    // 자리 있는 경우
                    for(int j=1; j<=N; j++) {
                        if(arr[j][1] == 0) {
                            arr[j][1] = a;
                            nowParking += 1;
                            money += arr[j][0] * cars[a];
                            break;
                        }
                    }
                }
            }
            else {    // a<0

                for(int j=1; j<=N; j++) {
                    if(arr[j][1] == -a) {
                        if(queue.isEmpty()) {
                            arr[j][1] = 0;
                            nowParking -= 1;
                            break;
                        }
                        else {    // queue.isEmpty() == false
                            int newC = queue.remove();
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