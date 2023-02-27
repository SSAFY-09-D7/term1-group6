import java.io.*;
import java.util.*;

public class Main{
    static int N, M;
    static int[] set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++) {
            StringBuilder sb = new StringBuilder();
            sb.append("#");
            sb.append(testCase);
            sb.append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            set = new int[N + 1];
            for(int i = 1; i <= N; i++) {
                set[i] = i;
            }

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int kind = Integer.parseInt(st.nextToken());
                int first = Integer.parseInt(st.nextToken());
                int second = Integer.parseInt(st.nextToken());
                if(kind == 0) {
                    union(first, second);
                }
                else if(kind == 1) {
                    int left = find(first);
                    int right = find(second);
                    if(left == right) sb.append(1);
                    else sb.append(0);
                }
            }
            System.out.println(sb);
        }
    }
    private static void union(int first, int second) {
        int rootA = find(first);
        int rootB = find(second);

        if(rootA != rootB) {
            set[rootB] = rootA;
        }
    }
    private static int find(int point) {
        if(set[point] == point) return point;
        else return set[point] = find(set[point]);
    }

}