public class Triangel {
	
	/*      /'*-._  C
	 *   A /      �-_
	 *    /__________�:.
	 *         B
	 */
	
	// L�ngder
	public double A;
	public double B;
	public double C;
	
	// Vinklar
	public double ab;
	public double bc;
	public double ca;
	
	public Triangel(double A, double B, double C) {
		this.A = A;
		this.B = B;
		this.C = C;
		
		// R�kna ut vinklar 
		this.ab = Vinkel(C, A, B);
		this.bc = Vinkel(A, B, C);
		this.ca = Vinkel(B, C, A);
		System.out.println("Triangel(L�ngder: " + A + ", " + B + ", " + C + ". Vinklar: " + deg(ab) + ", " + deg(bc) + ", " + deg(ca) + ")");
		
		// R�kna ut birektriser
		Bisektris_AB = BisektrisLength(A, B, ab);
		Bisektris_BC = BisektrisLength(B, C, bc);
		Bisektris_CA = BisektrisLength(C, A, ca);
	}
	
	public double Vinkel(double motstaende, double vs, double hs) {
		// vs = v�nster sida, hs = h�ger sida
		// Ber�knar omv�nda cosinus-satsen a^2 = b^2 + c^2 - 2bc*cos(a)
		double p = motstaende * motstaende - vs * vs - hs * hs;
		return Math.acos( p/(-2 * vs * hs) );
	}

	public double Bisektris_AB;
	public double Bisektris_BC;
	public double Bisektris_CA;

	public double AreaAB() {
		return A * B * Math.sin(ab) / 2;
	}
	public double AreaBC() {
		return B * C * Math.sin(bc) / 2;
	}
	public double AreaCA() {
		return C * A * Math.sin(ca) / 2;
	}
	
	public double Area() {
		return AreaAB(); // Kunde varit vilken av de ovandst�ende Area funktionerna som helst
	}
	
	public double Omkrets() { return Omkrets(this); }
	
	// --- Statiska metoder ---
	
	public static double deg(double rad) { return rad * 180/Math.PI; }

	public static double Area(int b, int h) { return b * h / 2; }
	// 	Heron's Formula for the area of a triangle
	public static double Area(Triangel t) { double p = Omkrets(t)/2; return Math.sqrt( p*(p-t.A)*(p-t.B)*(p-t.C) ); }
	public static double Omkrets(Triangel t) { return t.A + t.B + t.C; }
	
	// I denna formel �r vs och hs l�ngder av tv� sidor i en triangel,
	// a �r vinkeln mellan dessa sidor.
	// vs = v�nster sida, hs = h�ger sida
	// returnerar l�ngden av den bisektris som delar vinkeln mellan sidorna i tv� lika delar.
	public static double BisektrisLength(double vs, double hs, double alfa) { return (2*vs*hs * Math.cos(alfa/ 2)) / (vs + hs);}
	
	public static double omskrivenCirkel_Radie(Triangel t) {
		/*  Den omskrivna cirkelns centrum ligger i sk�rningspunkten
		 *  av sidornas mittpunktsnormaler och dess radie �r:
		 *  
		 *  r = a*b*c / ( 4 * SQRT( s(s-a)(s-b)(s-c) ) )
		 *  
		 *  https://sv.wikipedia.org/wiki/Triangel#Omskrivna_cirkeln
		 */
		double s = t.Omkrets()/2;
		double k = Math.sqrt(s * (s - t.A) * (s - t.B) * (s - t.C));
		return (t.A * t.B * t.C) / (4 * k);
	}
	public static double inskrivenCirkel_Radie(Triangel t) {
		/*  F�r att r�kna ut den inskrivna cirkelns radius, delar vi upp triangeln i tre delar
		 *  vars h�jder �r cirkelns radie. D� kan vi summera dessa sm� areor och �terigen f� den
		 *  totala arean.
		 *  
		 *  A = a*r/2 + b*r/2 + c*r/2
		 *  A = r/2 * ( a + b + c )
		 *  A = r/2 * O     d�r O �r omkretsen av triangeln.
		 *  
		 *  D� kan vi skriva om uttrycket och l�sa ut r:
		 *  
		 *  r = 2*A/O
		 */		
		return 2 * t.Area() / t.Omkrets();
	}
}
