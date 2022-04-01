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
		pl.occupier = this;
		strength = 1;
		rect = new Rectangle(place.loc.x, place.loc.y, 5,5);
	}
	
	public void fortify() {
		strength++;
	}
	public void move(Tile endTile) {
		if (endTile instanceof landTile) {
			try {
				if (endTile.occupier == null) {
					endTile.occupier = this;
					this.place = endTile;
				}
				else {
					endTile.occupier.fortify();
				}
			}
			catch (Exception e) {
				
			}
		}
		else {
			System.out.println("You cannot move a land unit into or on a sea tile");
		}
	}
	//rectangle of what you're making
	public void paintComponent( Graphics window )
	{
		window.setColor(new Color(0,0,0,100));
		window.fillRect(place.loc.x, place.loc.y, place.loc.x + 5, place.loc.y + 5);
		
			
	}
	
	public String toString() {
		return "ID: " + id + " Strength: " + strength + " Tile: " + place.getName();
	}

}
