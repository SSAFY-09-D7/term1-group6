import java.util.Scanner;

public class BOJ1244 {

    static int switchNum(int n) {
        int num = (n == 1) ? 0 : 1 ;
        return num;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  //스위치 개수
        int[] swi = new int[n];
        for (int i = 0; i < n; i++) {
            swi[i] = sc.nextInt();
        }
        int student = sc.nextInt();
        for (int i = 0; i < student; i++) {
            int gender = sc.nextInt();
            int selected = sc.nextInt();
            int num; //내가 고른 스위치 번호의 배열 인덱스

            switch (gender) {
                case 1:
                    num = selected-1;
                    for (int j = num; j < swi.length; j+=selected) {
                        swi[j]=1;
                    }
                    break;
                case 2:
                    num = selected-1;
                    int left = num-1;
                    int right = num+1;

                    swi[num] = switchNum(swi[num]);

                    //배열 넘어가지 않을때까지 반복
                    while(left>=0 && right < n) {
                        //같으면 숫자 바꿔줌
                        if(swi[left] == swi[right]) {
                            swi[left] = switchNum(swi[left]);
                            swi[right] = switchNum(swi[right]);
                            left--;
                            right++;
                        } else {
                            break;  //다르면 while문 탈출
                        }
                    }
                    break;
            }
        }

        for(int i : swi) {
            System.out.print(i + " ");
        }
    }

}
