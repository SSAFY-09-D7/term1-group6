import java.util.Scanner;

public class BOJ3985 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int L = sc.nextInt();
        int N = sc.nextInt();
        int[] cake = new int[L];

        int expectN = 0;
        int maxExpect=0;

        int realN=0;
        int maxReal=0;

        //방청객 수만큼
        for (int i = 0; i < N; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            //가장 많이 받을 예상 방청객
            int n = end-start+1;
            if(maxExpect < n){
                maxExpect=n;
                expectN = i+1;
            }
            int cnt=0;  // 실제로 받는 롤케이크 수
            //롤케익 배열에 방청객번호(i+1)을 넣는다
            for (int j = start-1 ; j <=end-1 ; j++) {
                if(cake[j] != 0) continue; //이미 차있으면 넘어감 (!=0)
                cake[j] = i+1;
                cnt++; //3
            }

            //실제 가장 많이 받은 방청객
            if(maxReal < cnt) {
                maxReal = cnt;
                realN = i + 1;
            }
        }
        System.out.println(expectN);
        System.out.println(realN);
    }
}
