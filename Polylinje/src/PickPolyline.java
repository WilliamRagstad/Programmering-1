import java.util.Random;
import static java.lang.System.out;

public class PickPolyline {

	public static final Random rand = new Random();
	public static final int Polylines_COUT = 10;
	
	public static void main3(String[] args) {
	    // skapa ett antal slumpm�ssiga Polyliner
	    Polyline[] Polylines = new Polyline[Polylines_COUT];
	    for (int i = 0; i < Polylines_COUT; i++) Polylines[i] = randomPolyline();
	    
	    // visa Polylinerna
	    for(int i = 0; i < Polylines_COUT; i++) out.println(Polylines[i]);
	    
	    // best�m den kortaste av de Polyliner som �r gula
	    Polyline shortestYellowLine = shortestYellow(Polylines);

	    // visa den valda Polylinen
	    out.println("\nShortest Yellow line is: " + shortestYellowLine);
	}

	// slumpPoint returnerar en Point med ett slumpm�ssigt namn, som �r en stor bokstav i
	// det engelska alfabetet, och slumpm�ssiga koordinater.
	public static Point randomPoint() {
	    String n = "" + (char)(65 + rand.nextInt(26));
	    int x = rand.nextInt(11);
	    int y = rand.nextInt(11);
	    return new Point(n, x, y);
	}
	
	// slumpPolyline returnerar en slumpm�ssig Polyline, vars f�rg �r antingen bl�, eller r�d
	// eller gul. Namn p� Polylinens h�rn �r stora bokst�ver i det engelska alfabetet. Tv� h�rn
	// kan inte ha samma namn.
	public static Polyline randomPolyline() {
	    // skapa en tom Polyline, och l�gg till h�rn till den
	    Polyline pl = new Polyline();
	    int totalCorners = 2 + rand.nextInt(7);
	    int currentCorners = 0;
	    //boolean[] pickedNames = new boolean[26]; // ett och samma namn kan inte f�rekomma flera g�nger
	    Point choosenPoint;
	    //char choosenChar = 0;
	    while (currentCorners < totalCorners) {
	    	choosenPoint = randomPoint();
	    	while(polylineContains(pl, choosenPoint.getName())) choosenPoint = randomPoint(); // Inga dubletter av namn
	    	pl.add(choosenPoint);
	    	currentCorners++;
	    }
	    
	    // s�tt f�rg
	    switch(rand.nextInt(3)) {
		    case 0:
		    	pl.setColor("blue");
		    	break;
		    case 1:
		    	pl.setColor("red");
		    	break;
		    case 2:
		    	pl.setColor("yellow");
		    	break;
	    }
	    
	    pl.setWidth(rand.nextInt(3)+1);
	    
	    return pl;
	}
	
	public static boolean polylineContains(Polyline line, String name) {
		Point[] c = line.getCorners();
		for(int i = 0; i < c.length; i++) {
			if (c[i].getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
	
	public static Polyline shortestYellow(Polyline[] lines) {
		Polyline shortestLine = null;
		double shortestLineLength = Double.POSITIVE_INFINITY;
		for(int i = 0; i < lines.length; i++) {
			if (lines[i].getColor() == "yellow") {
				// G�r faktiskt n�got

				if (shortestLine == null) shortestLine = lines[i];
				else {
					double currentLineLength = 0;
					Point[] currentLineCorners = lines[i].getCorners();
					for (int j = 0; j < currentLineCorners.length - 1; j++)
						currentLineLength += currentLineCorners[j].distance(currentLineCorners[j+1]);
					
					if (currentLineLength < shortestLineLength) {
						shortestLine = lines[i];
						shortestLineLength = currentLineLength;
					}
				}
			}
		}
		return shortestLine;
	}
}
