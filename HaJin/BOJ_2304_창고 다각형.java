import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int[] arr = new int[1005];
		List<Integer> findStartEnd = new ArrayList<>();
		
		int n = sc.nextInt();
		int a, b;
		int ans = 0;
		
		for(int i=0; i<n; i++) {
			a = sc.nextInt();
			b = sc.nextInt();
			
			arr[a] = b;
			
			findStartEnd.add(a);
		}
		
		Collections.sort(findStartEnd);
	
		int start = findStartEnd.get(0);
		int end = findStartEnd.get(n-1);
		
		for(int i=start; i<=end; i++) {
			int leftMax=0;
			int rightMax=0;
			
			// 왼쪽 중 최대 구하기
			for(int j=start; j<i; j++) {
				if(arr[j] > leftMax)
					leftMax = arr[j];
			}
			// 오른쪽 중 최대 구하기
			for(int j=i+1; j<=end; j++) {
				if(arr[j] > rightMax)
					rightMax = arr[j];
			}
			
			int tmp = Math.min(leftMax, rightMax);
			
			int p = Math.max(tmp,  arr[i]);
			ans += p;
		}
		
		
		System.out.println(ans);
		
		
	}
}

