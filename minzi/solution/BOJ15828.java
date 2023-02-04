import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ15828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //선입선출 - 큐 구현
        Queue<Integer> queue = new LinkedList<>();

        int sizeN = Integer.parseInt(br.readLine());

        while(true) {
            int n = Integer.parseInt(br.readLine());
            //0일 때 맨 처음 값 제거
            if (n == 0) {
                queue.remove();
            } else if (n == -1) {   //-1일 때 큐 안의 값 반환하고 종료
                if (queue.isEmpty()) {
                    System.out.println("empty");
                }
                else {
                    for(int i : queue)
                        System.out.println(i);
                }
                System.exit(0);
            } else if(sizeN > queue.size()) {   //큐 사이즈가 입력받은 값보다 작을 때 까지 큐에 추가
                queue.add(n);
            }
        }

    }

}
