import java.util.Scanner;

public class BOJ2578 {

    static boolean[][] flag = new boolean[5][5];

    public static void main(String[] args) {
        //5x5 이차원 배열 생성
        int[][] map = new int[5][5];

        //하나씩 입력받음
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        int countTrue;
        //입력받은 수와 일치하는 칸을   flag=true
        for (int n = 0; n < 25; n++) {
            int num = sc.nextInt();
            int cntBingo = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if(num == map[i][j]) {
                        flag[i][j] = true;
                        break;
                    }
                }
            }
            //열체크
            for (int i = 0; i < 5; i++) {
                countTrue=0;
                for (int j = 0; j < 5; j++) {
                    if(flag[j][i] ) countTrue++;
                }
                if(countTrue==5) cntBingo++;

            }
            //행체크
            for (int i = 0; i < 5; i++) {
                countTrue=0;
                for (int j = 0; j < 5; j++) {
                    if(flag[i][j]) countTrue++;
                }
                if(countTrue==5) cntBingo++;

            }
            //대각선1체크 0,0 1,1 2,2 3,3 4,4
            countTrue=0;
            for (int i = 0; i < 5; i++) {
                if(flag[i][i]) countTrue++;
                if(countTrue==5) cntBingo++;

            }
            //대각선2체크
            countTrue=0;
            for (int i = 0; i < 5; i++) {
                if(flag[i][4-i] ) countTrue++;
                if(countTrue==5) cntBingo++;
            }
            //체크될때마다 cnt++. 3   3개되면    i+1 출력
            if(cntBingo>=3){
                System.out.println(n+1);
                System.exit(0);
            }

        }
    }
}
