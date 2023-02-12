import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 5464. 주차장
 * 구현 - 큐, 계수정렬
 */
public class BOJ5464_주차장 {
    static int N, M, rev;
    static int[] pPrice, wCar;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Queue<Integer> waiting = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   //공간개수. 주차공간 번호 1~N
        M = Integer.parseInt(st.nextToken());   //차량 수

        //어느 주차 공간에 어떤 차가 들어갔는지
        int[] isFull = new int[N+1];

        pPrice = new int[N+1];
        for (int i = 1; i <= N; i++) {
            pPrice[i] = Integer.parseInt(br.readLine());
        }
        wCar = new int[M+1];
        for (int i = 1; i <= M; i++) {
            wCar[i] = Integer.parseInt(br.readLine());
        }

        //비어있는지 확인 -> 없으면 대기 / 생기면 바로 주차하게 함 / 여러 개이면 번호가 가장 작은 주차공간에 주차하도록
        //여러 대 차량 도착하면 순서대로 대기장소에서 대기- 큐로 구현
        //주차료는 차량의 무게에 비례 - 주차료=차량의 무게*주차공간의 단위 무게당 요금
        //수입 주차료들의 합

        String str;
        while((str = br.readLine()) != null && !str.isEmpty()) {
            int tmp = Integer.parseInt(str);  //차 넘버
            //양수를 받으면 일단 대기열에 넣는다
            if(tmp > 0) {
                waiting.offer(tmp);

                //주차 공간이 비었으면 poll
                for (int i = 1; i <= N ; i++) {
                    if (isFull[i] == 0) {
                        //작은 인덱스부터 탐색 - 자리가 비었으면 대기열에서 꺼내 넣는다
                        isFull[i] += waiting.poll();
                        break;
                    }
                    //꽉차있으면 pass.
                }
                //System.out.println(waiting);
                //System.out.println(Arrays.toString(isFull));
              // 음수를 받으면 차를 뺀다
            } else {
                tmp = Math.abs(tmp);  //빼야할 차 넘버
                for (int i = 1; i <= N ; i++) {
                    if (isFull[i] == tmp) {
                        rev += pPrice[i] * wCar[tmp];
                        isFull[i] = 0;
                        //대기열에 차가 있으면 주차자리에 넣는다
                        if(!waiting.isEmpty()) {
                            isFull[i] += waiting.poll();
                        }
                    }

                }
                //System.out.println(waiting);
                //System.out.println(Arrays.toString(isFull));

            }
        }
        System.out.println(rev);

    }
}
