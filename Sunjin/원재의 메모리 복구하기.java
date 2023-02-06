import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    static char[] chs;
    static char[] myChs;
    static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++){
            String str = br.readLine();
            chs = str.toCharArray();
            myChs = new char[chs.length];
            Arrays.fill(myChs, '0');
            min = Integer.MAX_VALUE;
            recursive(0, 0);
            System.out.println(min);
        }
    }

    private static void recursive(int idx, int count) {
        if (idx == chs.length) {
            System.out.println(idx + " " + count);
            if(min >= count){
                min = count;
            }
            return;
        }

        if (chs[idx] == myChs[idx]) {
            // 인덱스만 증가
            System.out.println(idx + " " +count);
            recursive(idx + 1, count);
        }
        else{
            if(myChs[idx] == '1') {
                for(int i = idx; i < myChs.length; i++) {
                    myChs[i] = '0';
                }
            }
            else if(myChs[idx] == '0') {
                for(int i = idx; i < myChs.length; i++){
                    myChs[i] = '1';
                }
            }
            System.out.println(idx + " " +count);
            recursive(idx + 1, count + 1);
        }
    }
}
