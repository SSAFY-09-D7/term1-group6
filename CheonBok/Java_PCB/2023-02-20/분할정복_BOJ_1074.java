import java.io.*;
import java.util.*;
/*
 *   2x2 크기의 사각형 공간에서 Z자로 순서 번호가 붙는 NxN 크기의 사각형 구조.
 *   2x2를 기준으로 확장되는 구조이며 확장 기준은 N^2*(rnd 최소 1에서 최대 3)이다.
 *   총 3번의 확장을 끝내면 다음 N은 N*2인 4로 변경.
 *   
 *   ex. 첫 2x2는 [0, 1, 2, 3]으로 구성된다. 확장할 경우 다음 2x2는 N^2*1 = 2^2*1인 4부터 시작해 [4, 5, 6, 7]이다.
 *   	마찬가지로 N^2*2부터 시작하는 [8, 9, 10, 11],  N^2*3부터 시작하는 [12, 13, 14, 15]가 하나의 확장 섹터이다.
 *    
 *     -> 이는 문제 예시에서 좌측 상단의 16개 칸을 구성한다.
 *     
 *     
 *   ※ 같은 문제(N=3 일때의 문제 예시 그림)에서 특정 좌표의 값을 얻는 과정.
 *   (시간초과): 일반적인 Divide&Conquer로 해결하려하면 out. -> 하나씩 값을 찾는 과정을 0.5초 안에 불가.
 *       -->  1 <= N <= 15.  2^15 = 32,768. 
 *       -->  만들어지는 최대 사각형 크기 = 32,768^2 = 약 10억.  1억 = 1초임을 생각하면 턱없이 부족하다.
 *   
 *   
 *   ★ 해당 좌표에 값이 매겨지는 규칙을 정의할 수 있다면 굳이 전부를 찾지 않아도 가능.
 *   1. N=3이고, 처음 시작 좌표가 (0,0)이라고 하자. Z는 N=1인 2x2에서만 만들어지므로 N을 쪼갤 필요가 있다.
 *   2. N을 쪼개면 N=2가 되고, 4개의 분할점이 생긴다. 이 4개의 분할점에 해당하는 값은 무엇인가? -> [0, 16, 32, 48]
 *   3. 하나의 분할점 0을 기준으로 N=2임으로 한 번 더 분할해야 한다. (다른 분할점들도 마찬가지)
 *   4. 그래서 N=1로 쪼개고, 0을 기준으로 한 4개의 분할점에 대한 값은? -> [0, 4, 8, 12]
 *   5. 16이 기준이었을 때 N=1에 대한 4개의 분할은? -> [16, 20, 24, 28]
 *   
 *   
 *   분할 후 접근 순서는 좌측 상단(0) -> 우측 상단(1) -> 좌측 하단(2) -> 우측 하단(3) 순서이다. 
 *   -> 규칙: 첫 시작점이 좌측 상단이며 이를 T라 했을 때, 다른 분할점들의 값 = T + N^2*(분할 후 접근 순서)
 *   
 *   ex. N=1인 상태에서 좌측 상단 = 16일 때(T), 우측 상단 => 16 + N^2*1 = 16 + 2^2*1 = 20.
 *   ex. N=1인 상태에서 좌측 상단 = 16일 때(T), 우측 하단 => 16 + N^2*3 = 16 + 2^2*3 = 28.
 *   ex. N=0인 상태에서 좌측 상단 = 36일 때(T), 좌측 하단 => 36 + N^2*2 = 36 + 1^2*2 = 38.
 *   ex. N=3인 상태에서 좌측 상단 = 0일  때(T), 우측 하단 => 0  + N^2*3 = 0  + 4^2*3 = 48.   
 *   
 */
public class BAEK_1074 {	
	static int r, c;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int qua = Integer.parseInt(st.nextToken());
		
		int N = (int)  Math.pow(2, qua);
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		//N=3, 8x8, 4x4, 2x2
		CheckNumber(1, 1, N, 0);
		
//		for (int[] string : map) {
//			System.out.println(Arrays.toString(string));
//		}

	}

	private static void CheckNumber(int row, int col, int size, int addsize) {
		if (row == r+1 && col == c+1) {
			System.out.println(addsize);
			return;
		}
		
		int Nsize = size/2;
		if (row+Nsize <= r+1 && col+Nsize <= c+1) {
			CheckNumber(row+Nsize, col+Nsize, Nsize, addsize + Nsize*Nsize*3);
		}
		
		else if (row+Nsize <= r+1 && col <= c+1) {
			CheckNumber(row+Nsize, col, Nsize, addsize + Nsize*Nsize*2);
		}
		
		else if (row <= r+1 && col+Nsize <= c+1) {
			CheckNumber(row, col+Nsize, Nsize, addsize + Nsize*Nsize);
		}
		

		else if (row <= r+1 && col <= c+1) {
			CheckNumber(row, col, Nsize, addsize);
		}
	}
}
