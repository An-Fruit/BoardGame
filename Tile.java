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
	
	public Tile(String name, int[] x, int[] y, int numberofpoints, int ex, int wy) {
		this.name = name;
		collisionhull = new Polygon(x, y, numberofpoints);
		possessor = 'Z';
		loc = new Point(ex,wy);
	}
	
	public Tile(int[] x, int[] y, int numberofpoints, Tile[] adj) {
		collisionhull = new Polygon(x, y, numberofpoints);
		adjacencyList = adj;
	}
	
	public boolean isInside(int x, int y) {
		if (collisionhull.contains(x, y)) return true;
		return false;
	}	
	public void addAdj(Tile[] adj) {
		adjacencyList = adj;
	}
	public String getName() {
		return name;
	}
}