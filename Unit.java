import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Unit extends JPanel{
	Tile place;
	char id;
	public int strength;
	Point loc;
	
	public Unit(char p, Tile pl, Point loc) {
		id = p;
		place = pl;
		strength = 1;
		this.loc = loc;
	}
	public void paintComponent( Graphics window )
	{

		
		
		//this rectangle shows you the boundaries of what you are drawing
		window.setColor( Color.RED );
		window.drawRect(loc.x, loc.y, loc.x + 5, loc.y + 5);
			
	}

}
