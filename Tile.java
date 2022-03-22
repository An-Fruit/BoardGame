import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Tile{
	Polygon collisionhull;
	Unit occupier;
	Point loc;
	ArrayList<Tile> adjacencyList;
	int[] RGB;
	boolean isHub;
	
	public Tile(int[] x, int[] y, int numberofpoints) {
		collisionhull = new Polygon(x, y, numberofpoints);
		adjacencyList = new ArrayList<>();
	}
	
	public Tile(int[] x, int[] y, int numberofpoints, Tile[] adj) {
		collisionhull = new Polygon(x, y, numberofpoints);
		adjacencyList = new ArrayList<>();
		for (Tile t: adj) {
			adjacencyList.add(t);
		}
	}
	
	public boolean isInside(int x, int y) {
		if (collisionhull.contains(x, y)) return true;
		return false;
	}	
	

}
