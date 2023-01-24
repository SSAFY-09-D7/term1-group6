package SSAFY;

import java.io.*;
import java.util.*;

public class Algorithm {

    static char[][] arr;
    static int N;
    public static void func(int start_i, int n, int cnt){
        cnt++;
        if(cnt>N)
            return;
        for(int i=start_i; i<=start_i+n-1; i++){
            if(i==start_i || i==start_i+n-1){
                for(int j=start_i; j<=start_i+n-1; j++)
                    arr[i][j] = '*';
            }
            else{
                arr[i][start_i] = '*';
                arr[i][start_i+n-1] = '*';
            }
        }
        func(start_i+2, n-4, cnt);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new char[500][500];

        for(int i=0; i<500; i++){
            for(int j=0; j<500; j++){
                arr[i][j] = ' ';
            }
        }

        int cnt = 0;
        //시작위치 (0,0), 변길이 (N-1)*4+1
        func(0, (N-1)*4 +1, cnt);

        for(int i=0; i<(N-1)*4+1; i++){
            for(int j=0; j<(N-1)*4+1; j++){
                System.out.print(arr[i][j]);
            }
            System.out.println("");
        }
    }
}
