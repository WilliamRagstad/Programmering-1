
public class Polylines {
	public static Polyline shortestYellow(Polyline[] lines) {
		Polyline shortestLine = null;
		double shortestLineLength = Double.POSITIVE_INFINITY;
		for(int i = 0; i < lines.length; i++) {
			if (lines[i].getColor() == "yellow") {
				// Gör faktiskt något

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
