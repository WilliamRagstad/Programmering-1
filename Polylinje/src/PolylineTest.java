import static java.lang.System.out;

public class PolylineTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		Polyline pl1 = new Polyline(new Point[] {
				new Point("A", 1, 2),
				new Point("B", 3, 0),
				new Point("C", 5, 4),
		});
		out.println(pl1);
		*/
		
		// Iterator
		Polyline pl1 = new Polyline(new Point[] {
				new Point("A", 1, 2),
				new Point("B", 3, 0),
				new Point("C", 5, 4),
		});
		Polyline.PolylineIterator iterator = pl1.new PolylineIterator();   // https://www.w3schools.com/java/java_inner_classes.asp
		
		while(iterator.cornerExists()) {
			out.println(iterator.corner());
			iterator.next();
		}
	}

}
