/*
막힌 부분
1. init[] 초기화는 char형이므로 null임. 0으로 착각함.
2. 재귀함수에서 리턴 전까지는 count맞다가 return count 할때 main에 0을 달고 나옴. ㅡㅡ
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA1289 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int T = Integer.parseInt(br.readLine());


        for (int tc = 1; tc <= T; tc++) {
            String str = br.readLine();     //메모리의 원래값
            char[] og = str.toCharArray();

            //초기값 배열
            char[] init = new char[og.length];
            for (int i = 0; i < init.length; i++) {
                init[i]='0';
            }

            int count = changeBit(og, init, 0,0);

            sb.append("# ").append(tc).append(" ").append(count).append("\n");

        }
        System.out.println(sb);
        br.close();
    }
    //idx는 문자열의 인덱스 -> 하나씩 늘린다
    //count 바뀐 횟수
    private static int changeBit(char[] og, char[] init, int idx, int count) {
        //========== basis part =========
        if(Arrays.toString(og).equals(Arrays.toString(init))) {
//            System.out.println(count);

            return count;   //여기서 리턴하면 0으로 나감
        }
        //========== inductive part =========

        //둘이 다르면 temp 바꾼다
        if(og[idx] != init[idx]) {
            char c = og[idx];
            for (int i = idx; i < init.length; i++) {
                init[i] = c;
            }
            System.out.println(Arrays.toString(init));
            changeBit(og,init,idx+1,count+1);
        } else {
            changeBit(og,init, idx+1,count);
        }
        return count;

    }
}
