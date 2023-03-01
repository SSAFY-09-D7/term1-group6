import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int result;
    static int startPoint;
    static boolean[] visited;
    static class Point{
        int value, depth;
        public Point(int value, int depth){
            this.value = value;
            this.depth = depth;
        }
    }
    static HashMap<Integer, ArrayList<Integer>> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int testCase = 1; testCase <= 10; testCase++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            startPoint = Integer.parseInt(st.nextToken());

            map = new HashMap<>();
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N / 2; i++){
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                if(!map.containsKey(from)) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(to);
                    map.put(from, list);
                }
                else{
                    ArrayList<Integer> list = map.get(from);
                    list.add(to);
                    map.put(from, list);
                }
            }
            // 입력 종료
            result = -1;
            visited = new boolean[101];
            bfs(startPoint);
            System.out.println("#" + testCase + " " + result);
        }
    }

    private static void bfs(int startPoint) {
        int curMaxDepth = 0;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(startPoint, 1));
        visited[startPoint] = true;

        while(!q.isEmpty()){
            Point now = q.poll();
            if(curMaxDepth < now.depth){
                curMaxDepth = now.depth;
                result = now.value;
            }
            else if(curMaxDepth == now.depth){
                if(result < now.value) result = now.value;
            }

            if(map.get(now.value) != null){
                for(int next : map.get(now.value)){
                    if(!visited[next]){
                        visited[next] = true;
                        q.add(new Point(next, now.depth + 1));
                    }
                }
            }
        }
    }
}
