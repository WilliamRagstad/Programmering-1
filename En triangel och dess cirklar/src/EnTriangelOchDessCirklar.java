
public class EnTriangelOchDessCirklar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Triangel t1 = new Triangel(3, 4, 5);

		System.out.println("Bis. BC = " + t1.Bisektris_BC);
		System.out.println("Bis. AB = " + t1.Bisektris_AB);
		System.out.println("Bis. CD = " + t1.Bisektris_CA);

		System.out.println("Area = " + t1.Area());

		System.out.println("Insk. Cirkel radie = " + Triangel.inskrivenCirkel_Radie(t1));
		System.out.println("Omsk. Cirkel radie = " + Triangel.omskrivenCirkel_Radie(t1));
	}
}
