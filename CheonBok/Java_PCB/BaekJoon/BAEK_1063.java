import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek1063 {
	/**
	 * stoneidx : 돌의 마지막 idx
	 * kingidx : king의 마지막 idx
	 */
	static int[] stoneidx = new int[2];
	static int[] kingidx = new int[2];
	
	/**
	 * 
	 * @param arridx : target idxs Array
	 * @param i : row delta 
	 * @param j : col delta
	 */
	private static void moving(int[] arridx, int i, int j) {
		if (arridx[0]+i >= 1 && arridx[0]+i < 9 && arridx[1]+j >= 1  && arridx[1]+j < 9) {
			arridx[0] += i;
			arridx[1] += j;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] first_in = br.readLine().split(" "); // the input of first line
		String[] movement = new String[Integer.parseInt(first_in[2])];
		String[] cols = {"0", "A", "B", "C", "D", "E", "F", "G", "H"}; // column idx
		String[] rows = {"0", "8", "7", "6", "5", "4", "3", "2", "1"}; // row idx
		int[] idxs = {0, 0}; //The king's delta to return when the same position as stone.
		
		for (int i = 0; i < movement.length; i++) { // Array of Movement Command
			movement[i] = br.readLine();
		}
		
		
		String[] king = first_in[0].split("");  //King Start Point
		kingidx[1] = Arrays.asList(cols).indexOf(king[0]);
		kingidx[0] = Arrays.asList(rows).indexOf(king[1]);
		
		
		String[] stone = first_in[1].split(""); //Stone Start Point
		stoneidx[1] = Arrays.asList(cols).indexOf(stone[0]);
		stoneidx[0] = Arrays.asList(rows).indexOf(stone[1]);
		
		for (String m : movement) { // Command Start
			if (m.equals("R")) { // to right
				moving(kingidx, 0, 1); // arridx, row, col
				idxs[0] = 0; 
				idxs[1] = 1;
			}
			else if (m.equals("L")) { // to left
				moving(kingidx, 0, -1);
				idxs[0] = 0;
				idxs[1] = -1;
			}
			else if (m.equals("B")) { // to bottom
				moving(kingidx, 1, 0);
				idxs[0] = 1;
				idxs[1] = 0;
			}
			else if (m.equals("T")) { // to top
				moving(kingidx, -1, 0);
				idxs[0] = -1;
				idxs[1] = 0;
			}
			else if (m.equals("RT")) { // right top
				moving(kingidx, -1, 1);
				idxs[0] = -1;
				idxs[1] = 1;
			}
			else if (m.equals("LT")) { // left tp
				moving(kingidx, -1, -1);
				idxs[0] = -1;
				idxs[1] = -1;
			}
			else if (m.equals("RB")) { // right bottom
				moving(kingidx, 1, 1);
				idxs[0] = 1;
				idxs[1] = 1;
			}
			else if (m.equals("LB")) { // left bottom
				moving(kingidx, 1, -1);
				idxs[0] = 1;
				idxs[1] = -1;
			}
			
			// if the king's position and the stone position are the same?
			if (kingidx[0]==stoneidx[0] && kingidx[1]==stoneidx[1]) {
				moving(stoneidx, idxs[0], idxs[1]);
				// if the stone doesn't move => the king return back.
				if (kingidx[0]==stoneidx[0] && kingidx[1]==stoneidx[1]) {
					kingidx[0] -= idxs[0];
					kingidx[1] -= idxs[1];
				}
			}
		}
		System.out.println(cols[kingidx[1]]+rows[kingidx[0]]);
		System.out.println(cols[stoneidx[1]]+rows[stoneidx[0]]);
	}
}
