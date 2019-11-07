public class Polyline {
    private Point[] corners;
    private String color = "svart";
    private int width = 1;
    
    public Polyline() {
        this.corners = new Point[0];
    }
    public Polyline(Point[] corner) {
        this.corners = new Point[corner.length];
        for (int i = 0; i < corner.length; i++)
            this.corners[i] = new Point(corner[i]);
    }
    
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("{");
    	if (corners.length > 0) {
    		sb.append("[");
    		for (int i = 0; i < corners.length; i++) {
    			sb.append(corners[i]);
    		}
    		sb.append("], ");
    	}
    	sb.append(color);
    	sb.append(", ");
    	sb.append(width);
    	sb.append("}");
    	
    	return sb.toString();
    }
    
    public Point[] getCorners() { return corners; }
    public String getColor() { return color; }
    public int getWidth() { return width; }
    public void setColor(String color) { this.color = color; }
    public void setWidth(int width) { this.width = width; }
    public double length() { return corners.length; }
    public void add(Point corner) {
        Point[] c = new Point[this.corners.length + 1];
        int i = 0;
        for (i = 0; i < this.corners.length; i++) c[i] = this.corners[i];
        c[i] = new Point(corner);
        this.corners = c;
    }
    public void insertBefore(Point corner, String cornerName) {
    	Point[] c = new Point[this.corners.length + 1];
    	boolean found = false;
        for (int i = 0; i < this.corners.length; i++) {
        	if (this.corners[i].getName() == cornerName) {
        		found = true;
        		c[i] = new Point(corner);
        	}
        	if (found) c[i+1] = this.corners[i];
        	else c[i] = this.corners[i];
        }
        this.corners = c;
    }
    public void remove(String cornerName) {
    	Point[] c = new Point[this.corners.length - 1];
    	boolean found = false;
    	for(int i = 0; i < this.corners.length; i++) {
    		if (this.corners[i].getName() != cornerName) found = true;

    		if (found) 	c[i] = this.corners[i+1];
    		else 	 	c[i] = this.corners[i];
    	}
    	this.corners = c;
    }
    
    
    // Iterator
    
    public class PolylineIterator {
        private int current = -1;
        
        public PolylineIterator() {
            if (Polyline.this.corners.length > 0)
                current = 0;
        }
        public boolean cornerExists() {
            return current != -1;
        }
        public Point corner() throws java.util.NoSuchElementException {
            if (!this.cornerExists()) throw new java.util.NoSuchElementException("slut av iterationen");
            Point c = Polyline.this.corners[current];
            return c;
        }
        public void next() {
            if (current >= 0 &&
                current < Polyline.this.corners.length - 1)
                current++;
            else
                current = -1;
        }
    }
    
}