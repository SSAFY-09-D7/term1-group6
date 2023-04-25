// 프로그래머스 "아이템 줍기"
import java.io.*;
import java.util.*;
class Solution {
    static int[][] roadMap;
    
    static class node{
        int x, y, d;
        public node(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
    
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        roadMap = new int[101][101];
        int answer = 0;
        
        for(int i=0; i< rectangle.length; i++){
        	// 도형의 크기 범위를 넓혀두지 않는다면 떨어져 있음에도 좌표상 붙어있는 값이 나온다.
        	// 이는 4방 탐색에 혼란을 주는 환경이 된다.  -> 그래서 최소 2배로 거리를 둔다.
        	// ex. 테두리(3,5)와 테두리(3,6)과는 붙어 있지 않음에도 4방 탐색하면 보게 되는 위치다.
        	// 이를 2배 하면 (6,10), (6,12)가 되는데, 4방 탐색 범위에 들어오지 못한다. (4방탐색은 +1/-1이므로)
            int x = rectangle[i][0]*2;
            int y = rectangle[i][1]*2;
            int xsize = rectangle[i][2]*2;
            int ysize = rectangle[i][3]*2;
            
            // 지도를 그린다.
            // 단, 테두리는 1로, 도형의 내부는 3으로 채운다(값이 서로 다르기만 하면 된다).
            // 이는 4방탐색 시 내부에 들어가지 않게 하기 위함이다.
            for(int row=x; row<=xsize; row++){
                for(int col=y; col<=ysize; col++){
                	// 테두리 라인에 해당하는 좌표 값을 가지고 있다 + 다른 도형에 종속되지 않는(값이 0인) 위치다.
                    if (row==x || row==xsize || col==y || col==ysize){
                        if(roadMap[row][col] == 0) roadMap[row][col] = 1;
                    }
                    
                    // 도형의 테두리에 속하지 않는 좌표다.
                    else {
                        roadMap[row][col] = 3;
                    }                    
                }
            }
        }
        
        // BFS 목적의 Queue
        Queue<node> q = new LinkedList();
        roadMap[characterX*2][characterY*2] = 4; // 시작 위치를 알고 싶어서 4로 두었다.(자유)
        q.add(new node(characterX*2, characterY*2, 0));  //(x축, y축)
        
        while(!q.isEmpty()){
            node pos = q.poll();
            //System.out.println(pos.x +" "+ pos.y +" "+ pos.d);
            
            // 아이템을 회수했다면
            if (pos.x == itemX*2 && pos.y == itemY*2){
               //System.out.println(pos.d);
                answer = pos.d;
                break;
            }
            
            for(int r=0; r< 4; r++){
                int nx = pos.x + dx[r];
                int ny = pos.y + dy[r];
                
                if(nx<0 || nx>100 || ny<0 || ny>100) continue;
                
                // 값이 1인 것이 아직 처리하지 않은 "테두리"이므로 따로 방문 처리 X.
                if (roadMap[nx][ny] == 1){
                    roadMap[nx][ny] = 2;
                    q.add(new node(nx, ny, pos.d+1));
                }
            }           
        }
        
        // 생성-탐색-거리 계산 모두 2배씩 이루어지므로 결과는 /2
        return answer/2;
    }
}