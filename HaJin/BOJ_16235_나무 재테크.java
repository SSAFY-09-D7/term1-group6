import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    public static int N, M, K;
    public static int[][] A;    // 겨울에 추가되는 양분
    public static int[][] arr;  // 현재 양분
    public static List<Tree> treeList;
    public static int YEAR = 0;
    public static int[] nr = {-1, -1, -1, 0, 0, 1, 1, 1};
    public static int[] nc = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static class Tree implements Comparable<Tree>{
        int i;
        int j;
        int age;
        Tree(int i, int j, int age){
            this.i = i;
            this.j = j;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o){
            return this.age - o.age;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N+1][N+1];
        arr = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
                arr[i][j] = 5;
            }
        }

        treeList = new ArrayList<>();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            treeList.add(new Tree(x, y, z));
        }

        while(true){
            if(YEAR == K){
                break;
            }
            
            Collections.sort(treeList);

            // 봄
            List<Tree> newTreeList = new ArrayList<>();
            List<Tree> deadTree = new ArrayList<>();
            for(Tree tree : treeList){
                int ii = tree.i;
                int jj = tree.j;
                int ageage = tree.age;

                // 양분 부족한 경우
                if(ageage > arr[ii][jj]){
                    deadTree.add(new Tree(ii, jj, ageage));
                }
                else{
                    arr[ii][jj] -= ageage;
                    newTreeList.add(new Tree(ii, jj, ageage+1));
                }
            }

            treeList = newTreeList;

            // 여름
            for(Tree tree : deadTree){
                arr[tree.i][tree.j] += tree.age / 2;
            }

            // 가을
            newTreeList = new ArrayList<>();
            for(Tree tree : treeList){
                int ii = tree.i;
                int jj = tree.j;
                int ageage = tree.age;

                if(ageage % 5 == 0) {
                    for (int p = 0; p < 8; p++) {
                        int iii = ii + nr[p];
                        int jjj = jj + nc[p];

                        if (iii >= 1 && iii <= N && jjj >= 1 && jjj <= N) {
                            newTreeList.add(new Tree(iii, jjj, 1));
                        }
                    }
                }
            }

            for(Tree tree : newTreeList){
                treeList.add(tree);
            }

            // 겨울
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    arr[i][j] += A[i][j];
                }
            }

            YEAR += 1;
        }

        System.out.println(treeList.size());
    }
}