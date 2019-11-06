import java.util.Arrays;
import java.util.Scanner;

public class BestamDenKortasteVagen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		
		
		
		Scanner in = new Scanner(System.in);
		System.out.print("m=");int m = in.nextInt();
		System.out.print("n=");int n = in.nextInt();

		/*    Z1  |  ai  |  Z2  |  bij  |  Z3  |  cj  |  Z4  |  Längd
		 *   ---------------------------------------------------------
		 *     X  |      |  U1  |       |  V1  |      |   Y  |
		 *     X  |      |  U1  |       |  V2  |      |   Y  |
		 *     X  |      |  U2  |       |  V1  |      |   Y  |
		 *     X  |      |  U2  |       |  V2  |      |   Y  |
		 *     X  |      |  ..  |       |  ..  |      |   Y  |
		 *     X  |      |  Um  |       |  Vn  |      |   Y  |
		 */

		double[]   a = new double[m];
		double[][] b = new double[m][n];
		double[]   c = new double[n];
		
		for (int i = 0; i < m; i++) {
			System.out.println("X till U" + i + ": ");
			a[i] = in.nextDouble();
		}
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.println("U" + i + " till V" + j + ": ");
				b[i][j] = in.nextDouble();
			}
		}
		
		for (int j = 0; j < m; j++) {
			System.out.println("V" + j + " till Y: ");
			c[j] = in.nextDouble();
		}
		
		
		double pathLength = DenKortasteVagen.Distance(a, b, c);
		System.out.println("Kortaste vägen är " + pathLength + " [le] lång.");
		System.out.println("Och går genom stationerna: ");

		int[] mellanStationer = DenKortasteVagen.Stations(a, b, c);
		for (int j = 0; j < mellanStationer.length; j++) {
			System.out.print(mellanStationer[j]);
			if (j != mellanStationer.length - 1) System.out.print(" -> ");
		}
		
	}
}
