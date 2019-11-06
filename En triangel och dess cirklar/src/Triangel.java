public class Triangel {
	
	/*      /'*-._  C
	 *   A /      ¨-_
	 *    /__________¨:.
	 *         B
	 */
	
	// Längder
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
		
		// Räkna ut vinklar 
		this.ab = Vinkel(C, A, B);
		this.bc = Vinkel(A, B, C);
		this.ca = Vinkel(B, C, A);
		System.out.println("Triangel(Längder: " + A + ", " + B + ", " + C + ". Vinklar: " + deg(ab) + ", " + deg(bc) + ", " + deg(ca) + ")");
		
		// Räkna ut birektriser
		Bisektris_AB = BisektrisLength(A, B, ab);
		Bisektris_BC = BisektrisLength(B, C, bc);
		Bisektris_CA = BisektrisLength(C, A, ca);
	}
	
	public double Vinkel(double motstaende, double vs, double hs) {
		// vs = vänster sida, hs = höger sida
		// Beräknar omvända cosinus-satsen a^2 = b^2 + c^2 - 2bc*cos(a)
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
		return AreaAB(); // Kunde varit vilken av de ovandstående Area funktionerna som helst
	}
	
	public double Omkrets() { return Omkrets(this); }
	
	// --- Statiska metoder ---
	
	public static double deg(double rad) { return rad * 180/Math.PI; }

	public static double Area(int b, int h) { return b * h / 2; }
	// 	Heron's Formula for the area of a triangle
	public static double Area(Triangel t) { double p = Omkrets(t)/2; return Math.sqrt( p*(p-t.A)*(p-t.B)*(p-t.C) ); }
	public static double Omkrets(Triangel t) { return t.A + t.B + t.C; }
	
	// I denna formel är vs och hs längder av två sidor i en triangel,
	// a är vinkeln mellan dessa sidor.
	// vs = vänster sida, hs = höger sida
	// returnerar längden av den bisektris som delar vinkeln mellan sidorna i två lika delar.
	public static double BisektrisLength(double vs, double hs, double alfa) { return (2*vs*hs * Math.cos(alfa/ 2)) / (vs + hs);}
	
	public static double omskrivenCirkel_Radie(Triangel t) {
		/*  Den omskrivna cirkelns centrum ligger i skärningspunkten
		 *  av sidornas mittpunktsnormaler och dess radie är:
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
		/*  För att räkna ut den inskrivna cirkelns radius, delar vi upp triangeln i tre delar
		 *  vars höjder är cirkelns radie. Då kan vi summera dessa små areor och återigen få den
		 *  totala arean.
		 *  
		 *  A = a*r/2 + b*r/2 + c*r/2
		 *  A = r/2 * ( a + b + c )
		 *  A = r/2 * O     där O är omkretsen av triangeln.
		 *  
		 *  Då kan vi skriva om uttrycket och lösa ut r:
		 *  
		 *  r = 2*A/O
		 */		
		return 2 * t.Area() / t.Omkrets();
	}
}
