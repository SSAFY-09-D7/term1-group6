import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int min;
    static int firstA, firstB;
    static int[] personCount;
    static boolean[] visited;
    static ArrayList<Integer>[] list;
    static boolean[] isAreaA;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        personCount = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            personCount[i] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            for(int j = 0; j < count; j++) {
                int to = Integer.parseInt(st.nextToken());
                if(!list[i].contains(to)) list[i].add(to);
                if(!list[to].contains(i)) list[to].add(i);
            }
        }

        isAreaA = new boolean[N + 1];
        min = Integer.MAX_VALUE;
        powerSet(1);

        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    private static void powerSet(int idx) {
        if(idx == N + 1){
            // 모두 연결되어있고 하나만 있는 곳이 없어서 조건 만족이라면
            if(isNotOne() && isConnect()){
                // 최소값 판단
                int personA = 0;
                int personB = 0;
                for(int i = 1; i <= N; i++){
                    if(isAreaA[i]) personA += personCount[i];
                    else personB += personCount[i];
                }
                int diff = Math.abs(personA - personB);
                if(diff < min) min = diff;
            }
            return;
        }

        isAreaA[idx] = true;
        powerSet(idx + 1);

        isAreaA[idx] = false;
        powerSet(idx + 1);

    }

    private static boolean isConnect() {
        visited = new boolean[N + 1];

        Queue<Integer> q = new LinkedList<>();
        q.add(firstA);
        visited[firstA] = true;

        while(!q.isEmpty()){
            int now = q.poll();
            for(int next : list[now]){
                // 방문하지 않은 A영역이라면
                if(isAreaA[next] && !visited[next]){
                    q.add(next);
                    visited[next] = true;
                }
            }
        }

        q = new LinkedList<>();
        q.add(firstB);
        visited[firstB] = true;

        while(!q.isEmpty()){
            int now = q.poll();
            for(int next : list[now]){
                // 방문하지 않은 B영역이라면
                if(!isAreaA[next] && !visited[next]){
                    q.add(next);
                    visited[next] = true;
                }
            }
        }

        for(int i = 1; i <= N; i++){
            if(!visited[i]) return false;
        }

        return true;
    }

    private static boolean isNotOne() {
        firstA = 0;
        firstB = 0;

        for(int i = 1; i <= N; i++){
            if(isAreaA[i]) {
                firstA = i;
                break;
            }
        }

        for(int i = 1; i <= N; i++){
            if(!isAreaA[i]){
                firstB = i;
                break;
            }
        }

        if(firstA == 0 || firstB == 0) return false;
        return true;
    }
}