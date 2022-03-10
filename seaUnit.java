
public class seaUnit extends Unit{
	

	public boolean isConvoy;
	public boolean hasMoved;
	
	public seaUnit(char p, Tile pl) {
		super(p, pl, new Point(0,0));	
		isConvoy = false;
	}
	
	public void reset() {
		strength = 1;
	}
	
	
	public void move(Tile t) {
		
		
		try {
			
			//if the tile is a coastal tile and there are no occupiers, the tile's occupier automatically becomes this unit
			if (t instanceof landTile && ((landTile) t).isCoast && t.occupier == null) {
				t.occupier = this;
				this.place = t;
			}
			
			//same with above, but with sea tiles
			else if (t instanceof seaTile && t.occupier == null) {
				t.occupier = this;
				this.place.occupier = null;
				this.place = t;
			}
		}
		catch (Exception e) {
			//the tile you are trying to move into is already occupied, and they have greater strength
			if (t.occupier.strength >= this.strength) {
				System.out.print("you cannot move");
			}
			
		}
	}
	
	public void convoy() {
		isConvoy = true;
	}
	
	public void support(seaUnit u) {
		u.strength++;
	}
	
	
	
	
	
	
}
