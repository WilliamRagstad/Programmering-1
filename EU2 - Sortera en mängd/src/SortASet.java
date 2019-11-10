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
	 *  ...
	 *  
	 *  Bevis för algoritmen
	 *  ¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨
	 *  
	 *  A) INRE LOOPEN
	 *  	ETT PÅSTÅENDE OM INRE LOOPEN
	 *		
	 *		När den inre loopen har utförts, gäller följande:
	 *		xi = minimum {xi, xi+1, …, xn}
	 *		
	 *		BEVIS
	 *		
	 *		För varje iteration i den inre loopen, jämförs alla element
	 *		efter och från index i+1 med det element på index i.
	 *		Om de är mindre än det element som för tillfället finns på index i,
	 *		byter de plats och fortsätter med nästa tal.
	 *
	 *		På så sätt kommer alltid det minsta talet ligga på position i efter att den inre
	 *		loopen har utförts.
	 *
	 *  B) HUVUDLOOPEN
	 *  	ETT PÅSTÅENDE OM HUVUDLOOPEN
	 *  
	 *		När huvudloopen har utförts, gäller följande:
	 *		x1 < x2 < … < xn
	 *
	 *		BEVIS
	 *
	 *		Om den inre loopen alltid slutar med att det minsta talet
	 *		av alla tal efter i mängden, ligger på position i, så måste
	 *		alla tal ligga i storleksordning ifall man utför innerloopen på
	 *		varje tal index i mängden från vänster till höger (från 0 till mängdens längd)
	 *		då minsta talet kommer läggas först på index 0, sen näst största på index 1 (minsta
	 *		av de resesterande talen), osv..
	 */
}
