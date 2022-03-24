import java.util.ArrayList;

public class Player {
	
	String name;
	ArrayList<landUnit> army;
	ArrayList<seaUnit> fleet;
	int hubCnt;
	
	
	public Player(String n) {
		name = n;
		army  = new ArrayList<>();
		fleet  = new ArrayList<>();
		hubCnt = 0;
	}
	
	public String toString(){
		String s = "";
		s += "Player Name: " + name + " ";
		s+= "Troops: " + army.size() + " ";
		s+= "Ships: " + fleet.size() + " ";
		return s;
	}

}
