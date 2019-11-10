
public class Point {
	private int x;
	private int y;
	private String name;
	
	public Point(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}
	public Point(Point p) {
		name = p.getName();
		x = p.getX();
		y = p.getY();
	}
	
	public String toString() {
		return "(" + name + " " + x + " " + y + ")";
	}
	
	public double distance(Point p) {
		return Math.sqrt(  Math.pow(x - p.getX(), 2) + Math.pow(y - p.getY(), 2)  );
	}
	public boolean equal(Point p) {
		return p.getX() == x && p.getY() == y;
	}

	public String getName() { return this.name; }
	public int getX() { return x; }
	public int getY() { return y; }
	public void setX(int v) { x = v; }
	public void setY(int v) { y = v; }
}
