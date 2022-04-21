import java.util.ArrayList;
import java.util.HashSet;

public class Player {
	
	String name;
	ArrayList<landUnit> army;
	ArrayList<seaUnit> fleet;
	HashSet<Tile> tilesOwned;
	int hubCnt;
	
	
	public Player(String n) {
		name = n;
		army  = new ArrayList<>();
		fleet  = new ArrayList<>();
		tilesOwned = new HashSet<>();
		hubCnt = 0;
	}
	public void setNation(HashSet<Tile> list) {
		tilesOwned = list;
	}
	public ArrayList<seaUnit> getFleet(){
		return fleet;
	}
	public ArrayList<landUnit> getArmy(){
		return army;
	}
	public HashSet<Tile> getTiles(){
		return tilesOwned;
	}
	public void addArmy(landUnit u) {
		army.add(u);
	}
	public String getName() {
		return name;
	}
	
	
	//dispose of units who do not belong to a tile
	public void disposeUnits() {
		for(landUnit Lu : army) {
			if(Lu.place == null) {
				army.remove(Lu);
			}
		}
		for(seaUnit Su : fleet) {
			if(Su.place == null) {
				fleet.remove(Su);
			}
		}
	}
	
	public String toString(){
		String s = "";
		s += "Player Name: " + name + " ";
		s+= "Troops: " + army.size() + " ";
		s+= "Ships: " + fleet.size() + " ";
		return s;
	}

}