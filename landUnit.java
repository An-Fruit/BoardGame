
public class landUnit extends Unit{

	
	
	public landUnit(Player p, Tile pl, String c) {
		super(p, pl);
	}

	public void move(Tile t) {
		try {
			if (t instanceof landTile && t.occupier == null) {
				t.occupier = this;
				this.place = null;
			}
		}
		catch (Exception e) {
			
		}
	}
}
