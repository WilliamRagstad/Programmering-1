class DenKortasteVagen
{
 // mellanstationer returnerar en vektor med de mellanstationer som finns på den kortaste
 // vägen. Ordningsnummer av den första mellanstationen finns på index 1, och ordningsnummer
 // av den andra mellanstationen på index 2 i vektorn.
	public static int[] Stations (double[] a, double[][] b, double[] c)
	{
		int[] stations = new int[2]; // Um & Vn
		double shortestLength = Double.MAX_VALUE;
		
		int m = a.length;
		int n = c.length;
		
		for (int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				double ai = a[i];
				double bij = b[i][j];
				double cj = c[j];
				
				double len = ai + bij + cj;
				if (len < shortestLength) {
					shortestLength = len;
					stations[0] = i;
					stations[1] = j;
				}
			}
		}
		return stations;
	}
	// langd returnerar längden av den kortaste vägen.
	public static double Distance (double[] a, double[][] b, double[] c)
	{
		int[] stations = Stations(a, b, c);
		double len = 0;

		len += a[stations[0]];
		len += b[stations[0]][stations[1]];
		len += c[stations[1]];
		
		return len;
	}
}
