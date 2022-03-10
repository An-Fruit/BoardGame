import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoardGame extends JPanel implements MouseListener, Runnable{
	
	
	private ArrayList<Tile> TileList;
	
	public BoardGame() {
		setBackground(Color.WHITE);
		
		new Thread(this).start();
	}
	
	public void paintComponent( Graphics window )
	{
		
		
		
		
	}
	
	
	public int[] mouseLoc(int x, int y) {
		int[] num = {x, y};
		return num;
		
	}

	
	
	
	
	
	
	
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouseClick");
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mousePressed");
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouseReleased");
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouseEntered");
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouseExited");
	}
	 
	public void run()
	{
		// TODO Auto-generated method
		try
		{
			while( true )
			{	
			   Thread.sleep(50);
			   repaint();
			}
		}
		catch( Exception e )
		{
			System.out.println("error:");
		}

	}
}
