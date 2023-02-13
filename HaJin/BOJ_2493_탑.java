import java.io.*;
import java.util.*;

class Info{
    int idx;
    int height;

    Info(int idx, int height){
        this.idx = idx;
        this.height = height;
    }
}

public class Main {

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        Stack<Info> stack = new Stack<>();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            int h = Integer.parseInt(st.nextToken());

            if(stack.isEmpty()){
                sb.append("0 ");
                stack.add(new Info(i, h));
            }
            else{
                while(true){
                    if(stack.isEmpty()){
                        sb.append("0 ");
                        stack.push(new Info(i, h));
                        break;
                    }

                    Info info = stack.peek();

                    if(info.height > h){
                        sb.append(info.idx).append(" ");
                        stack.push(new Info(i, h));
                        break;
                    }
                    else{
                        stack.pop();
                    }
                }
            }
        }
        System.out.println(sb);
    }
}