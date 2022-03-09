
public class landUnit extends Unit{

	public int strength;
	
	
	public landUnit(Player p, Tile pl, String c) {
		super(p, pl);
		strength = 1;
	}
	
	public void reset() {
		strength = 1;
	}
	
	public void move(Tile t) {
		if (t instanceof landTile) {
			try {
				if (t.occupier == null) {
					t.occupier = this;
					this.place = null;
				}
			}
			catch (Exception e) {
				
			}
		}
	}
	
}
