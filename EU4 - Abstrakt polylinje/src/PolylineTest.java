import static java.lang.System.out;

public class PolylineTest {

	public static void main(String[] args) {
		Polyline polylinje = null;
		polylinje = new NPolyline (); // (1)
		//polylinje = new VPolyline (); // (2)

		polylinje.add(new Point("A", 2, 4));
		polylinje.add(new Point("B", 6, 1));
		polylinje.add(new Point("C", 8, 12));
		polylinje.add(new Point("Z", 4, 2));
		polylinje.add(new Point("E", 14, 23));
		
		out.println(polylinje);
		
		polylinje.remove("Z");
		polylinje.insertBefore(new Point("D", 5, 4), "E");

		polylinje.setColor("yellow");
		
		out.println(polylinje);
		
		for (Point horn : polylinje)
			 out.println (horn);
		
		out.println(Polylines.shortestYellow(new Polyline[] { polylinje }));
		
	}

}
