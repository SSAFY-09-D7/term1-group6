import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);

        int[][] arr = new int[5][5];
        List<Integer> num = new ArrayList<>();
        int bingo = 0;
        int ans = 0;

        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                arr[i][j] = sc.nextInt();
            }
        }

        int a;
        for(int i=0; i<25 ;i++){
            a = sc.nextInt();
            num.add(a);
        }

        for(int i=0; i<25; i++){
            bingo = 0;
            int now = num.get(i);

            // 숫자 찾아 0으로
            for(int p=0; p<5; p++){
                for(int q=0; q<5; q++){
                    if(arr[p][q] == now){
                        arr[p][q] = 0;
                        break;
                    }
                }
            }
	
	   int tmp;
            // 가로줄 탐색
            for(int p=0; p<5; p++){
                tmp = 0;
                for(int q=0; q<5; q++){
                    if(arr[p][q] == 0)
                        tmp +=1;
                }
                if(tmp == 5)
                    bingo +=1;
            }

            // 세로줄 탐색
            for(int p=0; p<5; p++){
                tmp = 0;
                for(int q=0; q<5; q++){
                    if(arr[q][p] == 0)
                        tmp +=1;
                }
                if(tmp == 5)
                    bingo +=1;
            }

            // \ 탐색
            tmp = 0;
            for(int p=0; p<5; p++){
                if(arr[p][p] == 0)
                    tmp += 1;
            }
            if(tmp == 5)
                bingo +=1;


            // / 탐색
            tmp = 0;
            for(int p=0; p<5; p++){
                if(arr[p][4-p] == 0)
                    tmp += 1;
            }
            if(tmp == 5)
                bingo +=1;

            if(bingo >= 3){
                ans = i+1;
                break;
            }


        }

        System.out.println(ans);

    }
}
