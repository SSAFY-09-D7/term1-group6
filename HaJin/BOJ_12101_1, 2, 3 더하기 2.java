import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int n, k;
    public static List<String>[] list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        list = new ArrayList[11];
        for(int i=0; i<=10; i++){
            list[i] = new ArrayList<>();
        }

        list[1].add("1");
        list[2].add("11");
        list[2].add("2");
        list[3].add("12");
        list[3].add("111");
        list[3].add("21");
        list[3].add("3");

        for(int i=4; i<=n; i++){
            for(int j=0; j<list[i-1].size(); j++){
                list[i].add(list[i-1].get(j) + "1");
            }
            for(int j=0; j<list[i-2].size(); j++){
                list[i].add(list[i-2].get(j) + "2");
            }
            for(int j=0; j<list[i-3].size(); j++){
                list[i].add(list[i-3].get(j) + "3");
            }
        }

        Collections.sort(list[n]);

        if(k > list[n].size()){
            System.out.println(-1);
        }
        else{
            String s = list[n].get(k-1);
            for(int i=0; i<s.length(); i++){
                System.out.print(s.charAt(i));
                if(i != s.length()-1)
                    System.out.print("+");
            }
        }
    }
}