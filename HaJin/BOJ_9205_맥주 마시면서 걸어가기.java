import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int test_case, n;
    public static int nowX, nowY, storeX, storeY, fesX, fesY;
    
    public static class Store{
        int x;
        int y;
        Store(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static List<Store> list;

    public static Queue<Store> queue;
    public static boolean flag;
    public static boolean[] visited;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        test_case = Integer.parseInt(br.readLine());
        for(int T=0; T<test_case; T++){

            list = new ArrayList<>();
            flag = false;

            n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            nowX = Integer.parseInt(st.nextToken());
            nowY = Integer.parseInt(st.nextToken());

            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                storeX = Integer.parseInt(st.nextToken());
                storeY = Integer.parseInt(st.nextToken());
                list.add(new Store(storeX, storeY));
            }
            visited = new boolean[list.size()];

            st = new StringTokenizer(br.readLine());
            fesX = Integer.parseInt(st.nextToken());
            fesY = Integer.parseInt(st.nextToken());

            func();

            if(flag)
                System.out.println("happy");
            else System.out.println("sad");

        }

    }
    public static void func(){
        queue = new LinkedList<>();

        queue.add(new Store(nowX, nowY));

        while(!queue.isEmpty()){
            Store now = queue.poll();

            if(Math.abs(now.x - fesX) + Math.abs(now.y - fesY) <= 1000){
                flag = true;
                break;
            }

            for(int i=0; i<list.size(); i++){
                if(!visited[i]){

                    Store next = list.get(i);
                    if(Math.abs(now.x - next.x)+Math.abs(now.y - next.y) <= 1000){
                        queue.add(new Store(next.x, next.y));
                        visited[i] = true;
                    }
                }
            }

        }
    }

}