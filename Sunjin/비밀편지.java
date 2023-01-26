import java.util.Scanner;

public class test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int N = sc.nextInt();

        String str = sc.next();

        String[] value = { "000000", "001111", "010011", "011100", "100110", "101001", "110101", "111010"};
        String[] alpha = { "A", "B", "C", "D", "E", "F", "G", "H"};

        // 비교 단어 N번
        boolean flag = false;
        for(int i = 0; i < N; i++) {
            flag = false;
            String tmp = str.substring(i * 6, i * 6 + 6);
            // 어떤 알파벳과 같은건지 A~H까지
            for(int j = 0; j < value.length; j++) {
                // 알파벳이 모두 같다면
                if(tmp.equals(value[j])) {
                    sb.append(alpha[j]);
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                for(int j = 0; j < value.length; j++) {
                    int count = 0;
                    // 알파벳이 한자리만 틀리다면
                    // 알파벳 내부가 몇개 같은지 6번
                    for(int k = 0; k < value[j].length(); k++) {
                        if(value[j].substring(k, k+1).equals(tmp.substring(k, k+1))) {
                            count++;
                        }
                    }
                    if(count >= 5) {
                        sb.append(alpha[j]);
                        flag = true;
                        break;
                    }
                }
                if(!flag) {
                    System.out.println(i + 1);
                    System.exit(0);
                }
            }
        }
        System.out.println(sb);

        sc.close();
    }

}