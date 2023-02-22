import java.io.*;
import java.util.*;


public class cs_s4008{
    static int N;
    static int min, max;
    static int[] opCount;
    static int[] num;
    static char[] myOp;
    static int[] selectOp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++) {

            N = Integer.parseInt(br.readLine());

            myOp = new char[N - 1];
            opCount = new int[4];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 4; i++) {
                opCount[i] = Integer.parseInt(st.nextToken());
            }

            num = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int k = 0; k < N; k++) {
                num[k] = Integer.parseInt(st.nextToken());
            }

            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;

            selectOp = new int[N - 1];
            perm(0);

            System.out.printf("#%d %d\n", testCase , max - min);
        }
    }
    private static void perm(int count) {
        if(count == N - 1) { // 연산자 수는 N - 1
            int result = calc();
            if(result < min) min = result;
            if(max < result) max = result;
            return;
        }

        for(int i = 0; i < 4; i++) {
            if(opCount[i] > 0) {
                opCount[i]--;
                selectOp[count] = i;
                perm(count + 1);
                opCount[i]++;
            }
        }
    }

    private static int calc() {
        int sum = num[0];
        int idx = 1;
        for(int i = 0; i < N -1; i++) {
            if(selectOp[i] == 0) sum += num[idx++];
            else if(selectOp[i] == 1) sum -= num[idx++];
            else if(selectOp[i] == 2) sum *= num[idx++];
            else if(selectOp[i] == 3) sum /= num[idx++];
        }
        return sum;
    }
}