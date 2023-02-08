import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Info{
    private int taste1;
    private int taste2;

    public Info(int taste1, int taste2){
        this.taste1 = taste1;
        this.taste2 = taste2;
    }

    public int getTaste1(){
        return taste1;
    }

    public int getTaste2(){
        return taste2;
    }
}

public class Main {
    static int N;
    static int min;
    static ArrayList<Info> infos;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        infos = new ArrayList<>();
        min = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int taste1 = Integer.parseInt(st.nextToken());
            int taste2 = Integer.parseInt(st.nextToken());
            Info info = new Info(taste1, taste2);
            infos.add(info);
        }

        for(int i = 1; i <= N; i++){
            int[] infoNums = new int[i];
            comb(infoNums, 0, 0, i);
        }
        System.out.println(min);
    }

    // sel : 선택 배열
    // idx : 원본 도는 인덱스
    // k : 선택 배열 인덱스
    // limit : 선택 배열 크기
    private static void comb(int[] infoNums, int idx, int k, int limit) {
        if(k == limit){
//            System.out.println(Arrays.toString(infoNums));
            // 신맛
            int s = 1;
            // 쓴맛
            int p = 0;
            for(int i = 0; i < infoNums.length; i++){
                s *= infos.get(infoNums[i]).getTaste1();
                p += infos.get(infoNums[i]).getTaste2();
            }
            int result = Math.abs(s - p);
            if(result < min) min = result;
            return;
        }

        for(int i = idx; i < infos.size(); i++){
            infoNums[k] = i;
            comb(infoNums, i + 1, k + 1, limit);
        }
    }
}
