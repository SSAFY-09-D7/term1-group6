import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int max;
	static int[][] arr;
	static int[] sel;
	static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
       
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][10];
        
        for(int i = 1; i <= N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 1; j <= 9; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
     
        // 타석에 몇번 선수가 들어갈 것인지
        sel = new int[10];
        sel[4] = 1;
        
        // 선수가 뽑혔는지 여부 확인
        visited = new boolean[10];
        visited[1] = true;
        
        max = Integer.MIN_VALUE;
        // sel이라는 타석에 누가 들어갈 것인지 뽑는 과정
        perm(1);
        System.out.println(max);

        // test
//        int sum = game();
//        System.out.println(sum);
        
    }
    
	private static int game() {
		int score = 0;
		int inning = 0;
		int currentHitIdx = 0;
		while(true) {
			// 이닝이 시작될 때마다 베이스는 초기화 됨 잔루가 삭제되는 개념
			boolean[] base = new boolean[4];
			for(int i = 0; i < base.length; i++) {
				base[i] = false;
			}
			inning++; // 이닝 증가, 이닝이 다 찼을 경우 종료
			if(inning == N + 1) break;
			
			// 이닝마다 아웃카운트 초기화
			int outCount = 0;
			while(true) {
				// 타석 증가, 9번 타자까지 다 쳤으면 다시 1번 타자로
				currentHitIdx++;
				if(currentHitIdx == 10) currentHitIdx = 1;
				
				// 현재 타석에 있는 선수가 진루할 베이스 수 
				int cur = arr[inning][sel[currentHitIdx]];
				// test
//				int cur = arr[inning][currentHitIdx];
				
				// 아웃 처리
				if(cur == 0) outCount++;
				
				// 홈런일 경우
				else if(cur == 4){
					// 1루부터 3루까지
					for(int i = 1; i < base.length; i++) {
						// 루상에 누가 존재한다면
						if(base[i]) {
							score++;
							base[i] = false;
						}
					}
					// 타자 득점
					score += 1;
				}
				// 진루하는 상황 홈런은 아님
				else {
					ArrayList<Integer> goList = new ArrayList<>();
					// 타자도 진루 해야함
					goList.add(0);
					// 1~3루에 주자가 있다면 진루 리스트에 추가
					for(int i = 1; i < base.length; i++) {
						if(base[i] == true) {
							// 진루 리스트에 추가하고
							goList.add(i);
							// 나중에 다시 싹 다 채워줄 거니까 false로 변경
							base[i] = false;
						}
					}
					// 진루 리스트에 있는 녀석들 진루
					for(int i = 0; i < goList.size(); i++) {
						int tmp = goList.get(i);
						// 지금 타자가 쳐서 진루할 베이스만큼 추가
						tmp += cur;
						// 진루하는 녀석이 홈까지 가는 경우라면 점수 올리고 다른 작업 ㄴㄴ
						if(tmp >= 4) score++;
						// 홈까지 못 오는 경우 그 베이스에 true 처리
						else {
							base[tmp] = true;
						}
					}	
				}
//				System.out.println("cur : " + cur);
//				for(int i = 0; i < base.length; i++) {
//					System.out.println("base " + i + " : " + base[i]);
//				}
//				System.out.println(" idx : " + currentHitIdx + " score : " + score );
				// 아웃이 3개 찼을 경우 이닝 종료
				if(outCount == 3) break;
			}
		}
		return score;
	}
    
    // idx : 몇번 타석인지 sel[idx] : idx번 타석에 누가 들어갈 것인지
	private static void perm(int idx) {
		if(idx == 10) {
//			System.out.println(Arrays.toString(sel));
			int sum = game();
			max = Math.max(max, sum);
			return;
		}
		
		// 4번 타석은 이미 뽑혀있으니 패스
		if(idx == 4) {
			perm(idx + 1);
		}
		else {
			// 2번 선수부터 들어가야함
			for(int i = 2; i <= 9; i++) {
				if(!visited[i]) {
					sel[idx] = i;
					visited[i] = true;
					perm(idx + 1);
					visited[i] = false;
				}
			}
		}
	}


}