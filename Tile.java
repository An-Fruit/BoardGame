import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile{
	Polygon collisionhull;
	Unit occupier;
	public int xPoint;
	public int yPoint;
	
	
	public Tile(int[] x, int[] y, int numberofpoints) {
		collisionhull = new Polygon(x, y, numberofpoints);
	}
	
	public boolean isInside(int x, int y) {
		if (collisionhull.contains(x, y));
		return false;
	}	
	

}
