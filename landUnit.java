import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;

public class landUnit extends Unit{
	
	Polygon symbol;
	public landUnit(char p, Tile pl) {
		super(p, pl);

	}
	
	public void reset() {
		strength = 1;
	}
	
	public void move(Tile endTile) {
		//if the ending tile is a landtile, you can move the troops
		if (endTile instanceof landTile) {
			//if the ending tile is occupied 
			try {
				Unit enemyUnit = endTile.occupier;
				if (enemyUnit.strength >= this.strength) {
					System.out.println("You cannot move a unit onto a tile where the occupying strength is greater than the unit strength");
				}
				else {
					//subtract the enemy unit strength from ours
					this.strength -= enemyUnit.strength;
					//displace the original end tile occupying unit
					enemyUnit.place = null;
					//clear out the current tile so we don't duplicate units
					this.place.occupier = null;
					//set the occupying unit of the endtile to this unit
					endTile.occupier = this;
					//reciprocate on both ends
					this.place = endTile;
				}
				
			}
			//if the ending tile is not occupied
			catch (Exception e) {
				endTile.occupier = this;
				this.place.occupier = null;
				this.place = endTile;
			}
		}
		else {
			System.out.println("You cannot move a land unit into or on a sea tile");
		}
	}
	
	public void support(Unit u) {
		u.strength ++;
	}
	
	public void paintComponent(Graphics window, Color col) {
//		Image landunitimg = Toolkit.getDefaultToolkit().getImage("ArmyUnit.png");
//		window.drawImage(landunitimg, place.loc.x-50, place.loc.y-50, 50, 50, this);
		window.setColor(col);
		symbol = new Polygon(new int[] {place.loc.x - 7, place.loc.x - 7, place.loc.x + 7, place.loc.x + 7}, new int[] {place.loc.y - 7, place.loc.y + 7, place.loc.y - 7, place.loc.y + 7}, 4);
		window.fillPolygon(symbol);
	}
	
	
}