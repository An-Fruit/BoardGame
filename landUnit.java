
public class landUnit extends Unit{

	
	
	public landUnit(char p, Tile pl, String c) {
		super(p, pl, new Point(0,0));
	}
	
	public void reset() {
		strength = 1;
	}
	
	public void move(Tile t) {
		if (t instanceof landTile) {
			try {
				if (t.occupier == null) {
					t.occupier = this;
					this.place = t;
				}
			}
			catch (Exception e) {
				
			}
		}
	}
	
}
