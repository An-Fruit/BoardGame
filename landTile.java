
public class landTile extends Tile{
	boolean isCoast;
	
	public landTile(int[] x, int[] y, int numberofpoints, boolean coast, boolean hub) {
		super(x,y, numberofpoints);
		isCoast = coast;
		isHub = hub;
	}

}
