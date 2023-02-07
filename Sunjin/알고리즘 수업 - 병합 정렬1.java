import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class cs_24060 {
    static int N, K, count, result;
    static int[] tmp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        tmp = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        merge_sort(arr, 0, N - 1);

        if(count < K) System.out.println(-1);
        else System.out.println(result);
    }
    private static void merge_sort(int[] arr, int start, int end){
        if(start < end) {
            int mid = (start + end) / 2;
            merge_sort(arr, start, mid);
            merge_sort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    private static void merge(int[] arr, int start, int mid, int end){
        int i = start;
        int j = mid + 1;
        int t = 0;

        while(i <= mid && j <= end){
            if(arr[i] <= arr[j]){
                tmp[t++] = arr[i++];
            }
            else{
                tmp[t++] = arr[j++];
            }
        }
        while(i <= mid){
            tmp[t++] = arr[i++];
        }
        while(j <= end){
            tmp[t++] = arr[j++];
        }
        i = start;
        t = 0;
        while(i <= end){
            count++;
            if(count == K){
                result = tmp[t];
                break;
            }
            arr[i++] = tmp[t++];
        }
    }
}
