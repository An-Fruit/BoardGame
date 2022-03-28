
public class landTile extends Tile{
	boolean isCoast;
	
	public landTile(String n, int[] x, int[] y, int numberofpoints, boolean coast, boolean hub, int ex, int wy) {
		super(n,x,y, numberofpoints, ex, wy);
		isCoast = coast;
		isHub = hub;
	}

}
