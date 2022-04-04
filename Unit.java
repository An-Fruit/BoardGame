import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Unit extends JPanel{
	Tile place;
	char id;
	public int strength;
	Rectangle rect;
	boolean hasMoved;
	/*
	 * @param p - the identifier used to find the player the unit belongs to
	 * @param pl - the tile the current unit is on
	 * @param loc - the top right corner of the rectangular "collision hull" of the unit
	 * 
	 * 
	 * 
	 * 
	 */
	public Unit(char p, Tile pl) {
		id = p;
		place = pl;
		strength = 1;
		rect = new Rectangle(place.loc.x, place.loc.y, 5,5);
		pl.occupier = this;
		hasMoved = false;
	}
	
	public void fortify() {
		strength++;
	}
	//rectangle of what you're making
	public void paintComponent( Graphics window )
	{
		window.setColor(new Color(255,0,0,100));
		window.drawRect(place.loc.x, place.loc.y, 50, 50);

	}

}