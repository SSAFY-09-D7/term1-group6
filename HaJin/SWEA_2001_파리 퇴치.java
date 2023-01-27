import java.util.Scanner;
import java.io.FileInputStream;
import java.util.ArrayList;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int n, m, a;
            int max_fly=0;
			ArrayList<Integer>[] arr = new ArrayList[15];
            
            for(int i=0;i<15;i++){
            	arr[i] = new ArrayList<Integer>();
            }
            
            n=sc.nextInt();
            m=sc.nextInt();
            
            for (int i=0;i<n;i++){
            	for(int j=0;j<n;j++){
                    a=sc.nextInt();
                    arr[i].add(a);
                }
            }
            
            for(int i=0;i<n;i++){
                if(i+m>n)
                	break;
                for(int j=0;j<n;j++){
                    if(j+m>n)
                        break;
                    
                    int tmp=0;
                    
                    for(int k=0;k<m;k++){
                        for(int l=0;l<m;l++){
                            tmp+=arr[i+k].get(j+l);
                        }
                    }
                    
                    if(tmp>max_fly)
                        max_fly=tmp;
                }
            }
            
            System.out.println("#"+test_case+" "+max_fly);
		}
	}
}