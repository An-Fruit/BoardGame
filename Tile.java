import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile implements MouseListener{
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
	

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	

}
