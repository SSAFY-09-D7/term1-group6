import java.util.Scanner;
import java.util.Stack;

public class swea5432 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	    int test = sc.nextInt();
	    sc.nextLine();
	    String[] input;
	    boolean flag;
	    int cnt, total, remain;
	      
	    for (int rnd = 1; rnd < test+1; rnd++) {
	    	input = sc.nextLine().split("");
	    	cnt = 0; total = 0; remain = 0;
	         
	        flag = false; // true: laser confirm,  false: stick confirm
	        for (String s : input) {
	        	// first, to start stick area
	        	if (s.equals("(") && flag == false) { 
	        		flag = true; // if the next token is ")", its laser.
	               
	        		// if stick isn't closed, next separation makes overlaps.
	        		if (cnt > 0) { remain = cnt; }
	            }
	            
	        	// "(" before declared is clearly the stick.
	            else if (s.equals("(") && flag) {
	            	cnt++;
	            }
	            
	        	// there is a laser.
	            else if (s.equals(")") && flag) {
	            	total += cnt * 2 - remain;
	            	flag = false;
	            }
	            
	        	// there is an end of stick.
	            else if (s.equals(")") && flag == false) {
	            	cnt--;
	               
	            	if (remain > 0) { remain--; } 
	            }
	        }
	        System.out.println("#"+rnd+" "+total); 
	    }
	}
}
