
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ24060 {
    static int N, K;
    static int cnt = 0;
    static int[] tmp;
    static int i, j, t;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] og = new int[N];
        tmp = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            og[i] = Integer.parseInt(st.nextToken());
        }

        merge_sort(og,0,og.length-1);
        if(cnt < K) System.out.println(-1);
    }

    private static void merge_sort(int[] og, int p, int r) {
        int q;
        if(p<r) {
            q = (p+r)/2;
            merge_sort(og,p,q);
            merge_sort(og,q+1,r);
            merge(og,p,q,r);
        }
    }
    private static void merge(int[] og, int p, int q, int r) {
        i = p;
        j = q+1;
        t = 0;

        while(i<=q && j <=r) {
            if(og[i] <= og[j]) {
                 tmp[t++] = og[i++];
            } else {
                tmp[t++] = og[j++];
            }
        }
        while (i<=q)
            tmp[t++] = og[i++];
        while (j<=r) {
            tmp[t++] = og[j++];
        }
        i = p;
        t = 0;
        while(i<=r) {
            cnt++;
            if(cnt == K) {
                System.out.println(tmp[t]);
                break;
            }
            og[i++] = tmp[t++];
        }
    }
}
