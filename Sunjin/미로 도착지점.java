import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class test {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int[][] map = new int[N + 1][N + 1];

            int startRow = Integer.parseInt(st.nextToken());
            int startCol = Integer.parseInt(st.nextToken());

            int jumperCount = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < jumperCount; i++){
                int jumperRow = Integer.parseInt(st.nextToken());
                int jumperCol = Integer.parseInt(st.nextToken());
                // jumper 지역은 1로
                map[jumperRow][jumperCol] = 1;
            }

            int moveCount = Integer.parseInt(br.readLine());
            int[][] move = new int[moveCount][2];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < moveCount; i++){
                // 방향, 이동칸수
                move[i][0] = Integer.parseInt(st.nextToken());
                move[i][1] = Integer.parseInt(st.nextToken());
            }

            boolean jump = false;

            for(int i = 0; i < moveCount; i++) {
                int dir = move[i][0];
                int distance = move[i][1];

                for(int j = 0; j < distance; j++){
                    if(dir == 1){
                        startRow -= 1;
                    }
                    else if(dir == 2){
                        startCol += 1;
                    }
                    else if(dir == 3){
                        startRow += 1;
                    }
                    else if(dir == 4){
                        startCol -= 1;
                    }
                    if(startRow <= 0 || startCol <=0 || startRow > N || startCol > N){
                        jump = true;
                        break;
                    }

                    if(map[startRow][startCol] == 1){
                        jump = true;
                        break;
                    }
                }
            }
            int resultRow = 0;
            int resultCol = 0;

            if(jump){
                resultRow = 0;
                resultCol = 0;
            }
            else{
                resultRow = startRow;
                resultCol = startCol;
            }

            System.out.println("#" + test_case + " " + resultRow + " " + resultCol);

        }
    }
}
