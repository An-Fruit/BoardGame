
public class seaUnit extends Unit{
	

	public seaUnit(Player p, Tile pl, String c) {
		super(p, pl);		
	}
	
	public void move(Tile t) {
		try {
			if (t instanceof landTile && ((landTile) t).isCoast && t.occupier == null) {
				t.occupier = this;
				this.place = null;
			}
			else if (t instanceof seaTile && t.occupier == null) {
				t.occupier = this;
				this.place.occupier = null;
				this.place = null;
			}
		}
		catch (Exception e) {
			
		}
	}
}
