import static java.lang.System.out;

import java.util.Arrays;

public class SortASet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] arrayToSort = new double[] { 5, 0, 1, -2, 12, 53, 42.4 };
		out.println(Arrays.toString(arrayToSort));
		arrayToSort = sort(arrayToSort);
		out.println(Arrays.toString(arrayToSort));
	}

	public static double[] sort(double[] set) {
		for(int i = 0; i < set.length; i++) {
			for(int j = i + 1; j < set.length; j++) {
				if (set[j] < set[i]) {
		        	double b = set[j]; 	// Spara i en buffer
		        	set[j] = set[i];	// Byt ut xj mot xi
		        	set[i] = b;			// och xi mot xj
		        }
			}
		}
		return set;
	}
	
	/*  Elementjämförelser
	 *  ¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨
	 *  Algoritmens tidskomplexitet när det
	 *  gäller antalet elementjämförelser är θ(n) * θ(n/2),
	 *  vilket motsvarar en tidskomplxitet av θ(n²).
	 *  
	 *  Elementutbyten
	 *  ¨¨¨¨¨¨¨¨¨¨¨¨¨¨
	 *  Bästa fall:
	 *    (Sannolikheten för ett utbyte i bästa fall är 0)
	 *    0 => O(1)
	 *    
	 *  Värsta fall:
	 *    (Sannolikheten för ett utbyte i värsta fall är 1)
	 *    O(n²/2-n/2) => O(n²)
	 *    
	 *  Genomsnittligt fall:
	 *    (Sannolikheten för ett utbyte i ett genomsnittligt fall är 1/2)
	 *    O(n) * O(n/2) => O(n²/2) => O(n²)
	 *  
	 *  
	 *  Utbytessorteringen vs urvalssorteringen
	 *  ¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨
	 *  
	 */
}
