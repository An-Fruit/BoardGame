import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class landUnit extends Unit{

	
	
	public landUnit(char p, Tile pl, Point pt) {
		super(p, pl);
	}
	
	public void reset() {
		strength = 1;
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
	
	public void paintComponent(Graphics window) {
		Image landunitimg = Toolkit.getDefaultToolkit().getImage("ArmyUnit.png");
		window.drawImage(landunitimg, loc.x, loc.y, loc.x + 5, loc.y + 5, this);
	}
	
	
}
