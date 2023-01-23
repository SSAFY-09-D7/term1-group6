import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int cnt = sc.nextInt();

        ArrayList<Integer> row = new ArrayList<>();
        ArrayList<Integer> col = new ArrayList<>();

        for(int i = 0; i < cnt; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();

            if(a==0){
                row.add(b);
            }
            else col.add(b);
        }

        row.add(0);
        col.add(0);
        row.add(m);
        col.add(n);

        Collections.sort(row);
        Collections.sort(col);

        int max_col = 0;
        int max_row = 0;

        for(int i=1; i<row.size(); i++){
            int a = row.get(i) - row.get(i-1);
            if(a > max_row){
                max_row = a;
            }
        }

        for(int i=1; i<col.size(); i++){
            int a = col.get(i) - col.get(i-1);
            if(a > max_col){
                max_col = a;
            }
        }

        System.out.println(max_row * max_col);
        
    }
}
