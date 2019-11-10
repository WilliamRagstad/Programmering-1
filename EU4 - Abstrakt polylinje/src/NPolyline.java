import java.util.Arrays;
import java.util.Iterator;

public class NPolyline implements Polyline {
    private Node corners;
    private String color = "svart";
    private int width = 1;
    
    public NPolyline() {
        this.corners = null;
    }
    public NPolyline(Point[] corners) {
    	if (corners.length > 0)
	    {
	    	Node node = new Node (new Point (corners[0]));
	    	this.corners = node;
	    	int pos = 1;
	    	while (pos < corners.length)
	    	{
		    	node.nextNode = new Node (new Point (corners[pos++]));
		    	node = node.nextNode;
	    	}
    	}
    }
    
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("{");
    	if (corners != null) {
    		sb.append("[");
    		Node n = corners;
    		while(n != null) {
    			sb.append(n.corner);
    			n = n.nextNode;
    		}
    		sb.append("], ");
    	}
    	sb.append(color);
    	sb.append(", ");
    	sb.append(width);
    	sb.append("}");
    	
    	return sb.toString();
    }
    
    public Point[] getCorners() {
    	int points = 0;
    	Node n = corners;
    	
    	while(n != null) {
    		points++;
    		n = n.nextNode;
    	}
    	
    	Point[] c = new Point[points];
    	n = corners;
    	for(int i = 0; i < points; i++) {
    		c[i] = n.corner;
    		n = n.nextNode;
    	}
    	
    	return c;
    }
    public String getColor() { return color; }
    public int getWidth() { return width; }
    public void setColor(String color) { this.color = color; }
    public void setWidth(int width) { this.width = width; }
    public double length() {
    	int points = 0;
    	Node n = corners;
    	
    	while(n != null) {
    		points++;
    		n = n.nextNode;
    	}
    	return points;
    }
    public void add(Point corner) {
    	if (corners == null) {
    		corners = new Node(corner);
    	}
    	else {    		
    		Node n = corners;
    		while(n.nextNode != null) {
    			n = n.nextNode;
    		}
    		
    		n.nextNode = new Node(corner);
    	}
    }
    public void insertBefore(Point corner, String cornerName) {
    	Node n = corners;
    	while(n != null) {
    		
    		if (n.nextNode.corner.getName() == cornerName) {
    			
    			Node buff = n.nextNode;
    			n.nextNode = new Node(corner);
    			n.nextNode.nextNode = buff;
    			
    			return;
    		}
    		
    		n = n.nextNode;
    	}
    }
    public void remove(String cornerName) {
    	Node n = corners;
    	while(n != null) {
    		
    		if (n.nextNode.corner.getName() == cornerName) {
    			
    			n.nextNode = n.nextNode.nextNode;
    			
    			return;
    		}
    		
    		n = n.nextNode;
    	}
    }
    
    
    // Iterator
	public Iterator<Point> iterator () {
		return Arrays.asList( this.getCorners() ).iterator();
    }
	
	
	
	private static class Node
	{
		public Point corner;
		public Node nextNode;
		public Node (Point corner)
		{
			this.corner = corner;
			nextNode = null;
		}
	}

}