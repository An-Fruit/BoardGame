import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Tile{
	
	String name;
	Polygon collisionhull;
	Unit occupier;
	char possessor;
	
	Point loc;
	Tile[] adjacencyList;
	boolean isHub;
	
	// a tile constructor contains the name of the Tile, the polygon which it represents, and a point
	public Tile(String name, int[] x, int[] y, int numberofpoints, int ex, int wy) {
		this.name = name;
		collisionhull = new Polygon(x, y, numberofpoints);
		possessor = 'Z';
		loc = new Point(ex,wy);
	}
	
	// whether a given point is inside the Tile
	public boolean isInside(int x, int y) {
		if (collisionhull.contains(x, y)) return true;
		return false;
	}	
	
	// adds an adjacency list to the Tile
	public void addAdj(Tile[] adj) {
		adjacencyList = adj;
	}
	
	// returns the Tile's name
	public String getName() {
		return name;
	}
}