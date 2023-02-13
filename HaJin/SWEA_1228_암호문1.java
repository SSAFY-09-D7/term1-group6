import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        for(int test_case = 1; test_case <= 10; test_case++){
            LinkedList<Integer> list = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                list.add(Integer.parseInt(st.nextToken()));
            }

            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<num; i++){
                if(st.nextToken().equals("I")){
                    int x = Integer.parseInt(st.nextToken());   // x 위치 다음
                    int y = Integer.parseInt(st.nextToken());   // y개 숫자 삽입

                    for(int j=0; j<y; j++){
                        list.add(x, Integer.parseInt(st.nextToken()));
                        x++;
                    }
                }
            }

            System.out.printf("#%d ", test_case);
            for(int i=0; i<10; i++){
                System.out.print(list.get(i) + " ");
            }
            System.out.println();
        }
    }
}