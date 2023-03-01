import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class cs_14889 {
    static int N;
    static int[][] map;
    static int[] sel;
    static int[] sel2;
    static int[] calFor;
    static int teamA, teamB;
    static int min, diff;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        min = Integer.MAX_VALUE;
        sel = new int[N / 2];
        comb(0, 0);
        System.out.println(min);
    }

    private static void comb(int idx, int count) {
        if(count == N / 2){
            getValue();
            diff = Math.abs(teamA - teamB);
            if(diff < min) min = diff;
            return;
        }

        for(int i = idx; i < N; i++){
            sel[count] = i;
            comb(i + 1, count + 1);
        }
    }

    private static void getValue() {
        teamA = teamB = 0;
        boolean[] isPick = new boolean[N];
        for(int i = 0; i < sel.length; i++){
            int person = sel[i];
            isPick[person] = true;
        }

        int cnt = 0;
        sel2 = new int[N / 2];
        for(int i = 0; i < N; i++){
            if(!isPick[i]) sel2[cnt++] = i;
        }

        calFor = new int[2];
        comb2(0, 0, sel, 0);

        calFor = new int[2];
        comb2(0, 0, sel2, 1);
    }

    private static void comb2(int idx, int count, int[] pick, int team) {
        if(count == 2){
            int sum = map[calFor[0]][calFor[1]] + map[calFor[1]][calFor[0]];
            if(team == 0) teamA += sum;
            else teamB += sum;
            return;
        }

        for(int i = idx; i < pick.length; i++){
            calFor[count] = pick[i];
            comb2(i + 1, count + 1, pick, team);
        }
    }
}
