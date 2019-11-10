public interface Polyline extends java.lang.Iterable<Point>
{
	 Point[] getCorners ();
	 String getColor ();
	 int getWidth ();
	 double length ();
	 void setColor (String farg);
	 void setWidth (int bredd);
	 public void add (Point horn);
	 void insertBefore (Point horn, String hornNamn);
	 void remove (String hornNamn);
	 public java.util.Iterator<Point> iterator ();
}
