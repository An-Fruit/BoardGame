import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;

public class seaUnit extends Unit{
	
	Polygon symbol;
	public boolean isConvoy;
	public boolean hasMoved;
	
	public seaUnit(char p, Tile pl) {
		super(p, pl);	
		isConvoy = false;
		

	}
	
	public void reset() {
		strength = 1;
	}
	
	
	//move the sea unit from its current position to Tile t
	
	
	public void move(Tile t) {		
			//if the tile is a coastal tile and the occupier's strength is less than that of this unit, move our unit to the endtile and subsequently deduct strength
			//use try catch to avoid having to check for whether occupier is null
		if((t instanceof landTile && ((landTile)t).isCoast) || (t instanceof seaTile)) {
			try {
				Unit enemyUnit = t.occupier;
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
					t.occupier = this;
					t.possessor = this.id;
					//reciprocate on both ends
					this.place = t;
					
				}
			}
			catch(Exception e) {
				t.occupier = this;
				t.possessor = this.id;
				this.place.occupier = null;
				this.place = t;
			}
		}
		else {
			System.out.println("You cannot move a sea unit onto a non-coastal or non-sea tile");
		}
					
	}
	public boolean valid(Tile t) {
		if((t instanceof landTile && ((landTile)t).isCoast) || (t instanceof seaTile)) {
			try {
				Unit enemyUnit = t.occupier;
				if (enemyUnit.strength >= this.strength) {
					return false;
				}
				else {
					return true;
					
				}
			}
			catch(Exception e) {
				return true;
			}
		}
		else {
			return false;
		}
	}
	
	public void convoy() {
		isConvoy = true;
	}
	
	public void support(seaUnit u) {
		u.strength++;
	}
	
	public void paintComponent(Graphics window, Color col) {
//		Image seaunitimg = Toolkit.getDefaultToolkit().getImage("seaUnit.png");
//		window.drawImage(seaunitimg, place.loc.x, place.loc.y,  25, 25, this);
		window.setColor(col);
		symbol = new Polygon(new int[] {place.loc.x - 7, place.loc.x, place.loc.x + 7}, new int[] {place.loc.y - 7, place.loc.y, place.loc.y - 7}, 3);
		window.fillPolygon(symbol);
	}
	
	
	
	
}