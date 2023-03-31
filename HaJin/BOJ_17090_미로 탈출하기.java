import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N, M;
    public static char[][] arr;
    public static boolean[][] visited;  // 방문 여부 저장
    public static boolean[][] result;   // 결과 저장

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        visited = new boolean[N][M];
        result = new boolean[N][M];

        for(int i=0; i<N; i++){
            String s = br.readLine();
            for(int j=0; j<M; j++){
                arr[i][j] = s.charAt(j);
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(!visited[i][j]){
                    result[i][j] = func(i, j);
                }
            }
        }

        int cnt = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(result[i][j])
                    cnt += 1;
            }
        }

        System.out.println(cnt);

    }

    public static boolean func(int i, int j){

        visited[i][j] = true;
        int ii = i;
        int jj = j;

        if(arr[i][j] == 'U'){
            ii -= 1;
        } else if(arr[i][j] == 'R'){
            jj += 1;
        } else if(arr[i][j] == 'D'){
            ii += 1;
        } else if(arr[i][j] == 'L'){
            jj -= 1;
        }

        // 범위 안에 들어오고
        if(ii>=0 && ii<N && jj>=0 && jj<M){
            // 방문하지 않은 곳 -> 함수 호출
            if(!visited[ii][jj]){
                return result[i][j] = func(ii, jj);
            }
            // 방문한 곳 -> 저장한 결과값 사용
            else{
                return result[i][j] = result[ii][jj];
            }
        }
        else{   // 범위 벗어나면 return true
            return result[i][j] = true;
        }

    }

}