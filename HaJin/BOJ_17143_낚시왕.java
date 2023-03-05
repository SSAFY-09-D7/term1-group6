import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int R, C, M;
	
	public static List<Shark> list;
	public static int[][] map;
	public static int[][] newMap;
	public static int human = 0;
	public static int SIZE = 0;
	
	public static class Shark{
		int r, c, speed, dir, size;
		
		Shark(int r, int c, int speed, int dir, int size){
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[R][C];
        // map -1로 초기화
        for(int i=0; i<R; i++) {
        	for(int j=0; j<C; j++) {
        		map[i][j] = -1;
        	}
        }
        
        list = new ArrayList<Shark>();
        
        int r, c, s, d, z;
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	r = Integer.parseInt(st.nextToken());
        	c = Integer.parseInt(st.nextToken());
        	s = Integer.parseInt(st.nextToken());
        	d = Integer.parseInt(st.nextToken());
        	z = Integer.parseInt(st.nextToken());
        	
        	// map에 상어 인덱스 저장
        	map[r-1][c-1] = i;
        	
        	// 상어 정보 ArrayList에 저장
        	list.add(new Shark(r-1, c-1, s, d, z));        
        }
        
        while(human < C) {
        	// 1. 낚시왕 한 칸 이동
        	
        	
        	// 2. 가장 가까운 상어 잡기
        	for(int i=0; i<R; i++) {
        		if(map[i][human] != -1) {
        			// 잡은 상어 위치 -1, -1로
        			int sharkIdx = map[i][human];

        			SIZE += list.get(sharkIdx).size;
        			list.set(sharkIdx, new Shark(-1, -1, -1, -1, -1));
        			break;
        		}
        	}
        	
        	// 3. 상어 이동
        	Move();
        	
        	human += 1;
        
        }
        
        System.out.println(SIZE);
        
	}
	
	public static void Move() {

		newMap = new int[R][C];
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				newMap[i][j] = -1;
			}
		}
		
		//for(Shark s : list) {
		for(int i=0; i<list.size(); i++) {
			Shark s = list.get(i);
			// 잡아먹히지 않은 상어만 탐색
			if(s.r >= 0 && s.c >= 0) {
				if(s.dir == 1 || s.dir == 2) {
					goUpDown(i);
				}
				else if(s.dir == 3 || s.dir == 4) {
					goLeftRight(i);
				}
			}
		}
		
		// new map -> map 복사하기
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				map[i][j] = newMap[i][j];
			}
		}
		
	}
	
	public static void goUpDown(int i) {
		Shark s = list.get(i);
		int newR = s.r;
		int newDir = s.dir;
		int cnt = s.speed;
		
		while(cnt > 0) {
			cnt -= 1;
			
			if(newR == 0) {
				newDir = 2;
				newR += 1;
			}
			else if(newR == R-1) {
				newDir = 1;
				newR -= 1;
			}
			else {
				if(newDir == 1)
					newR -= 1;
				else
					newR += 1;
			}
		}
		
		list.set(i, new Shark(newR, s.c, s.speed, newDir, s.size));
		
		if(newMap[newR][s.c] == -1) {
			newMap[newR][s.c] = i;
		}
		else {
			Shark ss = list.get(newMap[newR][s.c]);
			
			if(ss.size < list.get(i).size) {
				list.set(newMap[newR][s.c], new Shark(-1, -1, -1, -1, -1));
				newMap[newR][s.c] = i;
				
			}
			else {
				list.set(i, new Shark(-1, -1, -1, -1, -1));
			}
				
		}
	}
	
	public static void goLeftRight(int i) {
		
		Shark s = list.get(i);
		int newC = s.c;
		int newDir = s.dir;
		int cnt = s.speed;
		
		while(cnt > 0) {
			cnt -= 1;
			
			if(newC == 0) {
				newDir = 3;
				newC += 1;
			}
			else if(newC == C-1) {
				newDir = 4;
				newC -= 1;
			}
			else {
				if(newDir == 4)
					newC -= 1;
				else
					newC += 1;
			}
			
		}
		
		list.set(i, new Shark(s.r, newC, s.speed, newDir, s.size));
		
		if(newMap[s.r][newC] == -1) {
			// 새로운 map에 다른 상어가 없는 경우
			newMap[s.r][newC] = i;
		}
		else {
			Shark ss = list.get(newMap[s.r][newC]);
			
			if(ss.size < list.get(i).size) {
				list.set(newMap[s.r][newC], new Shark(-1, -1, -1, -1, -1));
				newMap[s.r][newC] = i;
				
			}
			else {
				list.set(i, new Shark(-1, -1, -1, -1, -1));
			}
				
		}
	}

}import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int R, C, M;
	
	public static List<Shark> list;
	public static int[][] map;
	public static int[][] newMap;
	public static int human = 0;
	public static int SIZE = 0;
	
	public static class Shark{
		int r, c, speed, dir, size;
		
		Shark(int r, int c, int speed, int dir, int size){
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[R][C];
        // map -1로 초기화
        for(int i=0; i<R; i++) {
        	for(int j=0; j<C; j++) {
        		map[i][j] = -1;
        	}
        }
        
        list = new ArrayList<Shark>();
        
        int r, c, s, d, z;
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	r = Integer.parseInt(st.nextToken());
        	c = Integer.parseInt(st.nextToken());
        	s = Integer.parseInt(st.nextToken());
        	d = Integer.parseInt(st.nextToken());
        	z = Integer.parseInt(st.nextToken());
        	
        	// map에 상어 인덱스 저장
        	map[r-1][c-1] = i;
        	
        	// 상어 정보 ArrayList에 저장
        	list.add(new Shark(r-1, c-1, s, d, z));        
        }
        
        while(human < C) {
        	// 1. 낚시왕 한 칸 이동
        	
        	
        	// 2. 가장 가까운 상어 잡기
        	for(int i=0; i<R; i++) {
        		if(map[i][human] != -1) {
        			// 잡은 상어 위치 -1, -1로
        			int sharkIdx = map[i][human];

        			SIZE += list.get(sharkIdx).size;
        			list.set(sharkIdx, new Shark(-1, -1, -1, -1, -1));
        			break;
        		}
        	}
        	
        	// 3. 상어 이동
        	Move();
        	
        	human += 1;
        
        }
        
        System.out.println(SIZE);
        
	}
	
	public static void Move() {

		newMap = new int[R][C];
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				newMap[i][j] = -1;
			}
		}
		
		//for(Shark s : list) {
		for(int i=0; i<list.size(); i++) {
			Shark s = list.get(i);
			// 잡아먹히지 않은 상어만 탐색
			if(s.r >= 0 && s.c >= 0) {
				if(s.dir == 1 || s.dir == 2) {
					goUpDown(i);
				}
				else if(s.dir == 3 || s.dir == 4) {
					goLeftRight(i);
				}
			}
		}
		
		// new map -> map 복사하기
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				map[i][j] = newMap[i][j];
			}
		}
		
	}
	
	public static void goUpDown(int i) {
		Shark s = list.get(i);
		int newR = s.r;
		int newDir = s.dir;
		int cnt = s.speed;
		
		while(cnt > 0) {
			cnt -= 1;
			
			if(newR == 0) {
				newDir = 2;
				newR += 1;
			}
			else if(newR == R-1) {
				newDir = 1;
				newR -= 1;
			}
			else {
				if(newDir == 1)
					newR -= 1;
				else
					newR += 1;
			}
		}
		
		list.set(i, new Shark(newR, s.c, s.speed, newDir, s.size));
		
		if(newMap[newR][s.c] == -1) {
			newMap[newR][s.c] = i;
		}
		else {
			Shark ss = list.get(newMap[newR][s.c]);
			
			if(ss.size < list.get(i).size) {
				list.set(newMap[newR][s.c], new Shark(-1, -1, -1, -1, -1));
				newMap[newR][s.c] = i;
				
			}
			else {
				list.set(i, new Shark(-1, -1, -1, -1, -1));
			}
				
		}
	}
	
	public static void goLeftRight(int i) {
		
		Shark s = list.get(i);
		int newC = s.c;
		int newDir = s.dir;
		int cnt = s.speed;
		
		while(cnt > 0) {
			cnt -= 1;
			
			if(newC == 0) {
				newDir = 3;
				newC += 1;
			}
			else if(newC == C-1) {
				newDir = 4;
				newC -= 1;
			}
			else {
				if(newDir == 4)
					newC -= 1;
				else
					newC += 1;
			}
			
		}
		
		list.set(i, new Shark(s.r, newC, s.speed, newDir, s.size));
		
		if(newMap[s.r][newC] == -1) {
			// 새로운 map에 다른 상어가 없는 경우
			newMap[s.r][newC] = i;
		}
		else {
			Shark ss = list.get(newMap[s.r][newC]);
			
			if(ss.size < list.get(i).size) {
				list.set(newMap[s.r][newC], new Shark(-1, -1, -1, -1, -1));
				newMap[s.r][newC] = i;
				
			}
			else {
				list.set(i, new Shark(-1, -1, -1, -1, -1));
			}
				
		}
	}

}