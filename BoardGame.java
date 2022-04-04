import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;



public class BoardGame extends JInternalFrame implements MouseListener, Runnable, KeyListener{
	 

	private ArrayList<Tile> TileList;
	HashMap<Character, Player> playerMap;
	char[] playerID;
	int turn;
	int year;
	boolean endGame;
	boolean setUp;
	char winnerID;
	Tile currentTile;
	JDesktopPane desktop;
	UnitUI panel; 
	Unit selectedUnit;
	
	

	public void paint( Graphics window )
	{
		//print year and turn for debug purposes
		//System.out.println("yr: " + year + " turn: " + turn); 
		
		
		
		//checks all the players to see if one of them has won through controlling most supply hubs
		// render everything
		
		//get the mouse location - will be useful for moving troops & stuff so we don't need to constantly call methods
		int mouseX = MouseInfo.getPointerInfo().getLocation().x;
		int mouseY = MouseInfo.getPointerInfo().getLocation().y;
		
		//draw the map to the canvas
		Image map = Toolkit.getDefaultToolkit().getImage("DiploMap.png");
		
		window.drawImage(map, 0, 0, 990, 900, this);
//		window.drawImage(map, 0, 0, 1000, 1000, this);
		
		window.drawString("mouseLoc: " + mouseX+ " " + mouseY,850 , 100);
		
		window.drawString("turn number: " + turn, 200, 100);
		
		
		//highlights the tile & changes the highlight color based on whose turn it currently is
		for (Tile t: TileList) {
			if (t.occupier != null) {
				t.occupier.paintComponent(window);
			}
			if(t.isInside(mouseX, mouseY)) {
				currentTile = t;
				Color color = new Color(255,255,255,50);
				switch(turn) {
				//red
				case 0: color = new Color(255, 0, 0, 100);
						break;
				//green
				case 1: color = new Color(0,255,0,100);
						break;
						
				//blue
				case 2: color = new Color(0,0,255,100);
						break;
				//
				case 3: color = new Color(255,200,0,100);
						break;
				case 4: color = new Color(175,0,255,100);
						break;
				case 5: color = new Color(0,255,60,100);
						break;
				case 6: color = new Color(250,250,60,100);
						break;
				case 7: color = new Color(0,255,255,100);
						break;
				}
				
			
				window.setColor(color);
				window.fillPolygon(t.collisionhull);
				
			}
//			window.drawPolygon(t.collisionhull);
		}
		
		
		//draw the all units on the board
		for(char c : playerID) {
			for(landUnit Lu: playerMap.get(c).getArmy()) {
				Lu.paintComponent(window);
				
			}
			for(seaUnit Su : playerMap.get(c).getFleet()) {
				Su.paintComponent(window);
			}
		}
		
		if (panel != null) {
			panel.moveToFront();
		}
		

//		
	}
	//on click, figure out the x/y values and iterate the turns
	public void mouseClicked(MouseEvent e) {
		//since you only need to check when someone wins when the turn is advanced, game logic is here
		
		//if there have been too many years, the game ends
		if(year > 1980) {
			endGame = true;
			return;
		}
		if(turn >= playerID.length - 1) {
			turn = 0;
			year++;
		}
		else {
			turn++;
		}
		//if all players have gone, the "year" advances
		
		//checks all players to see whether one of them has controlled the majority of supply hubs and therefore won
		
//		for(char c : playerMap.keySet()) {
//			if(playerMap.get(c).hubCnt > 16) {
//				endGame = true;
//				winnerID = c;
//				return;
//			}
//		}
		
		System.out.println("Year: " + year + " Turn: " + turn);
		System.out.println("Player that just went/pressed a key: " + playerMap.get(playerID[turn]));
		
		int loc1 = MouseInfo.getPointerInfo().getLocation().x;
		int loc2 = MouseInfo.getPointerInfo().getLocation().y;
		
		// generates the UI for ordering moves when a Unit is cliked on
		if (panel == null && currentTile.occupier != null) {
			selectedUnit = currentTile.occupier;
			if (selectedUnit instanceof landUnit) {
				selectedUnit = currentTile.occupier;
				landUnitUI pane = new landUnitUI((landUnit)selectedUnit, false, false);
				panel = pane;
				desktop.add(panel);
			}
			else {
				selectedUnit = (seaUnit)currentTile.occupier;
				seaUnitUI pane = new seaUnitUI((seaUnit)selectedUnit, false, false);
				panel = pane;
				desktop.add(panel);
			}
		}
	
		// if a button has been clicked to order a move, it will be executed
		if (panel instanceof landUnitUI) {
			landUnitUI use = (landUnitUI) panel;
			landUnit chosenUnit = (landUnit)selectedUnit;
			if (use.moveButtonPressed) {
				chosenUnit.move(currentTile);
				panel = null;
			}
			if (use.moveButtonPressed) {
				if (currentTile.occupier != null && currentTile.occupier instanceof landUnit) {
					chosenUnit.support(currentTile.occupier);
					panel = null;
				}
			}
		}
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	 
	@Override
	public void keyPressed(KeyEvent e) {
		
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}
	
	public void run()
	{
		//basically render the game
		
		try
		{
			while(!endGame)
			{
				Thread.sleep(200);
				repaint();
			}
			System.out.print("Game Ended");
			
		}
		catch( Exception e )
		{
			System.out.println("error:");
		}

	}
	
	// constructor
		public BoardGame(JDesktopPane d) {
			//interface stuff w/keyboard & mouse inpu
			setBackground(Color.WHITE);
			addMouseListener(this);
			addKeyListener(this);
			setVisible(true);
			setSize(990,900);

			
			//list of tiles & players by playerID
			TileList = new ArrayList<>();
			playerMap = new HashMap<>();
			
			//initializes the year and the gamestate
			setUp = true;
			endGame = false;
			year = 1901;
			
			//initializes JDesktop
			desktop = d;
			panel = null;
			currentTile = null;
			selectedUnit = null;
			
			//asks for the amount of players and the player ID array
			Scanner f = new Scanner(System.in);
			System.out.println("How many players would you like to have in this game? (3-7 allowed)");
			int temp = f.nextInt();
			if(temp >= 3 && temp <= 7) playerID = new char[temp];
			for(int i = 0; i < temp; i++) {
				
				
				
			}
			
			//gives every player a name
			for(int i = 0; i < playerID.length; i++) {
				System.out.println("What is Player " + (i + 1) + "'s name?");
				playerMap.put(playerID[i], new Player(f.next()));
			}
			f.close();
			turn = 0;
			//print the player IDs
			System.out.println(Arrays.toString(playerID));
			System.out.println("Pressing any key on the keyboard will advance turns");
			
	/*****************************************ADDING TILES************************************************************************************************/
			ArrayList<Tile> United_Kingdom = new ArrayList<>();
			ArrayList<Tile> France = new ArrayList<>();
			ArrayList<Tile> Germany = new ArrayList<>();
			ArrayList<Tile> Russia = new ArrayList<>();
			ArrayList<Tile> Ottomans = new ArrayList<>();
			ArrayList<Tile> Italy = new ArrayList<>();
			ArrayList<Tile> Austria = new ArrayList<>();
			
			
			landTile Petrograd = new landTile("Petrograd", new int[] {989, 988, 864, 742, 672, 641, 669, 673, 632, 667, 988},
					new int[] {185, 5, 4, 188, 129, 131, 250, 287, 315, 355, 183}, 
					11, true, true,778,251);
			TileList.add(Petrograd);
			Russia.add(Petrograd);
			
			landTile Baltic_States = new landTile("Baltic_States", new int[] {595, 646, 666, 660, 660, 620, 597, 599, 589, 581, 578, 565, 565, 583, 594},
			new int[] {320, 327, 356, 372, 434, 453, 441, 415, 408, 411, 397, 385, 362, 345, 360}, 
			15, true, false,617,412);
			TileList.add(Baltic_States);
			
			landTile Moscow = new landTile("Moscow", new int[] {990, 668, 659, 622, 630, 763, 895, 991, 989},
			new int[] {188, 354, 431, 452, 475, 457, 446, 514, 188}, 
			9, false, true,785,381);
			TileList.add(Moscow);
			Russia.add(Moscow);
			
			landTile East_Ukraine = new landTile("East_Ukraine", new int[] {989, 988, 936, 929, 835, 862, 791, 808, 826, 795, 764, 779, 750, 714, 703, 693, 750, 767, 778, 895},
			new int[] {520, 690, 672, 637, 610, 552, 591, 606, 604, 633, 617, 601, 589, 621, 623, 596, 546, 461, 455, 449}, 
			20, true, true,786,620);
			TileList.add(East_Ukraine);
			Russia.add(East_Ukraine);
			
			landTile West_Ukraine = new landTile("West_Ukraine", new int[] {630, 624, 655, 662, 669, 688, 703, 706, 746, 762, 631},
			new int[] {482, 519, 535, 569, 569, 591, 587, 570, 546, 466, 483}, 
			11, false, false,685,543);
			TileList.add(West_Ukraine);
			Russia.add(West_Ukraine);
			
			landTile Poland = new landTile("Poland", new int[] {596, 620, 630, 624, 599, 583, 564, 531, 522, 527, 555, 585, 595},
			new int[] {443, 453, 480, 512, 522, 511, 524, 515, 476, 464, 451, 452, 444}, 
			13, false, true,560,494);
			TileList.add(Poland);
			Russia.add(Poland);
			
			landTile Galicia = new landTile("Galicia", new int[] {526, 547, 565, 581, 594, 622, 653, 658, 643, 550, 541, 517, 523},
			new int[] {539, 520, 526, 514, 523, 522, 539, 587, 591, 549, 544, 547, 539}, 
			13, false, false,613,551);
			TileList.add(Galicia);
			
			landTile Prussia = new landTile("Prussia", new int[] {567, 576, 578, 587, 595, 595, 584, 556, 530, 523, 484, 475, 478, 501, 529, 528, 549, 561, 568, 566, 566},
			new int[] {386, 395, 412, 410, 418, 441, 450, 449, 459, 474, 470, 452, 437, 427, 420, 431, 417, 412, 399, 390, 387}, 
			21, true, false,557,438);
			TileList.add(Prussia);
			
			landTile Romania = new landTile("Romania", new int[] {663, 667, 687, 696, 712, 702, 669, 651, 602, 598, 601, 658, 666, 645, 644, 662, 662},
			new int[] {574, 574, 595, 630, 628, 665, 661, 673, 668, 654, 642, 631, 623, 602, 598, 591, 574}, 
			17, true, true,649,666);
			TileList.add(Romania);
			
			landTile Sweden = new landTile("Sweden", new int[] {556, 579, 587, 577, 563, 570, 523, 518, 528, 536, 534, 507, 496, 477, 472, 461, 451, 482, 531, 559},
			new int[] {95, 113, 162, 161, 183, 197, 242, 287, 287, 300, 318, 329, 381, 384, 397, 397, 322, 207, 110, 97}, 
			20, true, true,518,322);
			TileList.add(Sweden);
			
			landTile Finland = new landTile("Finland", new int[] {590, 608, 563, 563, 573, 569, 597, 654, 665, 630, 639, 601, 601, 579, 555, 582, 589, },
			new int[] {160, 180, 237, 247, 255, 282, 297, 276, 249, 97, 88, 72, 91, 90, 89, 109, 162, }, 
			17, true, false,625,219);
			TileList.add(Finland);
			
			landTile Norway = new landTile("Norway", new int[] {381, 376, 399, 437, 448, 478, 527, 559, 556, 565, 577, 599, 600, 640, 651, 642, 642, 625, 624, 591, 525, 432, 416, 382},
			new int[] {242, 313, 328, 305, 317, 207, 107, 95, 91, 80, 90, 90, 69, 88, 71, 67, 52, 48, 58, 48, 81, 222, 221, 244}, 
			24, true, true,435,305);
			TileList.add(Norway);
			
			landTile Denmark = new landTile("Denmark", new int[] {428, 428, 437, 448, 454, 443, 432, 419, 419, 396, 402, 427},
			new int[] {351, 380, 386, 385, 394, 407, 400, 401, 398, 401, 358, 352}, 
			12, true, true,412,392);
			TileList.add(Denmark);
			
			landTile Silesia = new landTile("Silesia", new int[] {520, 528, 542, 514, 485, 470, 461, 466, 484, 519},
			new int[] {480, 517, 521, 523, 504, 506, 489, 489, 474, 480}, 
			10, false, false,497,501);
			TileList.add(Silesia);
			
			landTile Berlin = new landTile("Berlin", new int[] {435, 457, 465, 465, 475, 471, 482, 462, 430, 430, 436},
			new int[] {436, 423, 423, 434, 434, 453, 471, 485, 488, 453, 435}, 
			11, true, true,452,482);
			TileList.add(Berlin);
			
			landTile Czechia = new landTile("Czechia", new int[] {471, 481, 502, 511, 525, 515, 480, 474, 458, 437, 432, 462, 470},
			new int[] {509, 509, 527, 528, 543, 547, 550, 562, 563, 543, 519, 514, 508}, 
			13, false, false,471,548);
			TileList.add(Czechia);
			
			landTile Kiel = new landTile("Kiel", new int[] {397, 411, 418, 413, 419, 431, 425, 426, 397, 350, 366, 373, 379, 396, 396},
			new int[] {404, 404, 421, 424, 432, 436, 452, 490, 506, 477, 466, 434, 439, 433, 405}, 
			15, true, true,395,498);
			TileList.add(Kiel);
			
			landTile Bavaria = new landTile("Bavaria", new int[] {468, 458, 429, 431, 451, 436, 388, 380, 356, 339, 342, 361, 377, 391, 427, 456, 468, },
			new int[] {507, 514, 513, 537, 557, 577, 578, 573, 573, 566, 547, 538, 509, 502, 492, 489, 509, }, 
			17, false, true,419,575);
			TileList.add(Bavaria);
			
			landTile Hungary = new landTile("Hungary", new int[] {502, 527, 551, 569, 612, 640, 640, 662, 657, 599, 595, 527, 525, 511, 502},
			new int[] {606, 588, 557, 552, 565, 594, 603, 624, 628, 639, 649, 643, 624, 624, 606}, 
			15, false, true,526,611);
			TileList.add(Hungary);
			
			landTile Vienna = new landTile("Vienna", new int[] {480, 506, 517, 538, 547, 526, 507, 497, 479, 486, 475, 480},
			new int[] {554, 550, 552, 551, 556, 583, 592, 605, 603, 581, 567, 554}, 
			12, false, true,488,591);
			TileList.add(Vienna);
			
			landTile Tyrol = new landTile("Tyrol", new int[] {382, 440, 453, 468, 480, 476, 464, 432, 420, 406, 395, 398, 381},
			new int[] {581, 585, 567, 568, 581, 601, 609, 609, 612, 629, 614, 605, 592}, 
			13, false, false,453,602);
			TileList.add(Tyrol);
			
			landTile Trieste = new landTile("Trieste", new int[] {457, 463, 477, 494, 499, 506, 519, 523, 539, 540, 532, 541, 538, 534, 496, 469, 467, 458, 452, 450, 457},
			new int[] {613, 614, 605, 612, 610, 627, 629, 648, 648, 671, 678, 702, 703, 714, 689, 657, 638, 635, 642, 635, 614}, 
			21, false, true,459,635);
			TileList.add(Trieste);
			
			landTile Serbia = new landTile("Serbia", new int[] {544, 593, 600, 600, 573, 573, 567, 567, 545, 537, 543},
			new int[] {650, 655, 689, 700, 744, 741, 736, 710, 699, 681, 673}, 
			11, true, true,553,683);
			TileList.add(Serbia);
			
			landTile Bulgaria = new landTile("Bulgaria", new int[] {601, 669, 700, 685, 690, 673, 667, 640, 631, 598, 604, 600},
			new int[] {672, 665, 669, 695, 710, 714, 741, 744, 726, 733, 688, 673}, 
			12, false, true,612,719);
			TileList.add(Bulgaria);
			
			landTile Greece = new landTile("Greece", new int[] {628, 635, 623, 619, 603, 598, 609, 601, 629, 616, 615, 606, 608, 599, 595, 577, 581, 607, 604, 572, 559, 573, 573, 598, 599, 626},
			new int[] {731, 746, 746, 762, 755, 763, 778, 781, 817, 817, 821, 821, 845, 838, 844, 819, 816, 814, 801, 798, 772, 761, 750, 740, 735, 731}, 
			26, true, false,583,785);
			TileList.add(Greece);
			
			landTile Albania = new landTile("Albania", new int[] {539, 541, 551, 561, 562, 568, 568, 554, 549, 541, 540},
			new int[] {713, 709, 709, 716, 734, 745, 758, 770, 754, 754, 712}, 
			11, true, false,545,752);
			TileList.add(Albania);
			
			landTile Istanbul = new landTile("Istanbul", new int[] {677, 671, 675, 672, 672, 692, 708, 748, 761, 763, 761, 724, 718, 709, 692, 679},
			new int[] {718, 737, 747, 752, 774, 773, 779, 775, 769, 730, 729, 730, 724, 724, 712, 717}, 
			16, true, true,698,748);
			TileList.add(Istanbul);
			
			landTile Eastern_Anatolia = new landTile("Eastern_Anatolia", new int[] {757, 780, 816, 907, 904, 866, 829, 802, 768, 759, 764, 765, 761},
			new int[] {723, 692, 685, 690, 726, 726, 759, 759, 777, 776, 767, 731, 725}, 
			13, true, true,781,760);
			TileList.add(Eastern_Anatolia);
			
			landTile Southern_Anatolia = new landTile("Southern_Anatolia", new int[] {907, 921, 886, 873, 845, 833, 824, 798, 778, 765, 751, 734, 726, 712, 706, 695, 684, 683, 693, 706, 751, 766, 800, 829, 867, 902, },
			new int[]{730, 753, 772, 792, 814, 813, 831, 839, 823, 825, 846, 844, 839, 840, 827, 828, 807, 775, 775, 782, 776, 780, 761, 764, 729, 726} , 
			26, true, true,690,813);
			TileList.add(Southern_Anatolia);
			
			landTile Armenia = new landTile("Armenia", new int[] {908, 935, 990, 990, 919, 908, 908},
			new int[] {693, 676, 695, 770, 747, 730, 693}, 
			7, false, false,937,741);
			TileList.add(Armenia);
			
			landTile Syria = new landTile("Syria", new int[] {943, 989, 999, 871, 869, 861, 878, 887, 943},
			new int[] {758, 774, 896, 897, 843, 839, 791, 774, 758}, 
			9, false, false,916,845);
			TileList.add(Syria);
			
			landTile Rhineland = new landTile("Rhineland", new int[] {345, 336, 338, 336, 341, 381, 394, 350},
			new int[] {477, 499, 518, 540, 548, 512, 505, 478}, 
			8, false, false,348,533);
			TileList.add(Rhineland);
			
			landTile Venice = new landTile("Venice", new int[] {452, 447, 425, 418, 441, 445, 428, 382, 397, 405, 421, 432, 451},
			new int[] {614, 632, 632, 657, 672, 706, 689, 651, 625, 634, 617, 613, 613}, 
			13, true, true,417,644);
			TileList.add(Venice);
			
			landTile Aputia = new landTile("Aputia", new int[] {450, 452, 478, 524, 504, 497, 477, 458, 450},
			new int[] {709, 708, 720, 761, 759, 766, 759, 721, 710}, 
			9, true, false,473,748);
			TileList.add(Aputia);
			
			landTile Tuscany = new landTile("Tuscany", new int[] {376, 381, 407, 423, 402, 385, 377},
			new int[] {659, 655, 665, 686, 695, 679, 658}, 
			7, true, false,400,688);
			TileList.add(Tuscany);
			
			landTile Rome = new landTile("Rome", new int[] {398, 425, 446, 454, 440, 412, 398, },
			new int[] {702, 691, 710, 724, 734, 727, 704, }, 
			7, true, true,409,722);
			TileList.add(Rome);
			
			landTile Napoli = new landTile("Napoli", new int[] {438, 453, 472, 497, 496, 476, 439},
			new int[] {735, 724, 758, 769, 777, 777, 737}, 
			6, true, true,461,760);
			TileList.add(Napoli);
			
			landTile Sicily = new landTile("Sicily", new int[] {476, 502, 507, 483, 470, 458, 456, 444, 399, 408, 434, 458, 467, 474, },
			new int[] {780, 780, 791, 819, 819, 819, 843, 845, 814, 805, 810, 808, 808, 780, }, 
			14, true, false,478,806);
			TileList.add(Sicily);
			
			landTile Piedmont = new landTile("Piedmont", new int[] {336, 388, 395, 379, 380, 355, 340, 335},
			new int[] {611, 614, 622, 637, 656, 652, 659, 610}, 
			8, true, false,358,642);
			TileList.add(Piedmont);
			
			landTile Burgundy = new landTile("Burgundy", new int[] {310, 334, 343, 340, 314, 285, 273, 253, 302, 310, 314},
			new int[] {511, 541, 551, 571, 601, 601, 621, 604, 539, 514, 515}, 
			8, true, false,302,583);
			TileList.add(Burgundy);
			
			landTile Netherlands = new landTile("Netherlands", new int[] {311, 331, 334, 344, 362, 365, 342, 328, 310},
			new int[] {472, 483, 494, 473, 464, 437, 433, 443, 469}, 
			9, true, false,329,465);
			TileList.add(Netherlands);
			
			landTile Amsterdam = new landTile("Amsterdam", new int[] {283, 306, 311, 331, 337, 334, 313, 297, 274, 280},
			new int[] {478, 476, 483, 487, 518, 522, 511, 494, 490, 480}, 
			10, true, true,300,495);
			TileList.add(Amsterdam);
			
			landTile Paris = new landTile("Paris", new int[] {235, 302, 299, 253, 236, 236},
			new int[] {523, 525, 538, 593, 584, 522}, 
			6, false, true,274,545);
			TileList.add(Paris);
			
			landTile Picardy = new landTile("Picardy", new int[] {270, 295, 308, 305, 240, 248, 269},
			new int[] {495, 497, 512, 521, 519, 502, 493}, 
			7, true, false,267,519);
			TileList.add(Picardy);
			
			landTile Brest = new landTile("Brest", new int[] {163, 184, 205, 235, 234, 196, 195, 161},
			new int[] {507, 506, 517, 516, 575, 565, 544, 515}, 
			8, true, true,208,558);
			TileList.add(Brest);
			
			landTile Gascony = new landTile("Gascony", new int[] {204, 230, 230, 252, 250, 267, 236, 228, 179, 179, 204},
			new int[] {577, 580, 585, 596, 602, 620, 638, 658, 647, 636, 576}, 
			11, true, false,214,616);
			TileList.add(Gascony);
			
			landTile Marseilles = new landTile("Marseilles", new int[] {288, 309, 310, 329, 335, 333, 300, 275, 254, 231, 237, 267, 280},
			new int[] {607, 604, 607, 611, 658, 663, 669, 654, 676, 660, 643, 624, 626}, 
			13, true, true,296,638);
			TileList.add(Marseilles);
			
			landTile Spain = new landTile("Spain",new int[] {51, 52, 76, 173, 175, 226, 253, 235, 207, 175, 183, 159, 138, 124, 51, 41, 89, 52},
			new int[] {629, 604, 598, 634, 647, 662, 686, 697, 697, 734, 744, 765, 768, 784, 777, 749, 639, 627}, 
			18, true, true,124,702);
			TileList.add(Spain);
			
			landTile Portugal = new landTile("Portugal", new int[] {50, 89, 41, 25, 5, 15, 11, 18, 51},
			new int[] {629, 644, 744, 745, 734, 727, 692, 683, 634}, 
			9, true, true,18,709);
			TileList.add(Portugal);
			
			landTile London = new landTile("London",new int[] {239, 249, 261, 271, 282, 285, 284, 265, 262, 271, 264, 236, 236},
			new int[] {443, 432, 430, 422, 426, 431, 446, 458, 465, 467, 470, 467, 444}, 
			13, true, true,252,465);
			TileList.add(London);
			United_Kingdom.add(London);
			
			landTile Wales = new landTile("Wales",new int[] {185, 206, 248, 238, 236, 197, 191, 166, 183, 200, 206},
			new int[] {417, 416, 432, 444, 466, 461, 469, 460, 450, 452, 444}, 
			11, true, false,213,457);
			TileList.add(Wales);
			United_Kingdom.add(Wales);
			
			landTile York = new landTile("York", new int[] {250, 261, 264, 263, 272, 270, 261, 250, 246, 242, 243},
			new int[] {362, 361, 364, 384, 392, 419, 428, 428, 420, 418, 398}, 
			11, true, false,252,426);
			TileList.add(York);
			United_Kingdom.add(York);
			
			landTile Liverpool = new landTile("Liverpool", new int[] {209, 220, 226, 248, 243, 241, 230, 203, 197, 219},
			new int[] {354, 340, 339, 360, 395, 419, 414, 413, 406, 391}, 
			10, true, true,227,413);
			TileList.add(Liverpool);
			United_Kingdom.add(Liverpool);
			
			landTile Edinburgh = new landTile("Edinburgh",new int[] {230, 236, 246, 262, 276, 259, 262, 250, 231},
			new int[] {340, 316, 305, 307, 316, 345, 357, 360, 342}, 
			9, true, true,248,347);
			TileList.add(Edinburgh);
			United_Kingdom.add(Edinburgh);
			
			landTile Clyde = new landTile("Clyde",new int[] {217, 230, 246, 251, 257, 258, 239, 222, 205, 206, 217},
			new int[] {338, 314, 303, 292, 292, 283, 279, 289, 311, 328, 338}, 
			11, true, false,222,321);
			TileList.add(Clyde);
			United_Kingdom.add(Clyde);
			
			landTile Algeria = new landTile("Algeria",new int[] {0, 47, 62, 123, 133, 170, 244, 266, 286, 330, 319, 315, 315, 2},
			new int[] {825, 790, 790, 828, 812, 813, 812, 821, 819, 829, 836, 883, 897, 895}, 
			14, true, false,114,878);
			TileList.add(Algeria);
			
			landTile Tunis = new landTile("Tunis",new int[] {326, 336, 345, 360, 358, 380, 368, 381, 370, 380, 317, 316, 319},
			new int[] {831, 830, 822, 823, 836, 837, 849, 874, 888, 898, 896, 874, 841}, 
			13, true, true,350,858);
			TileList.add(Tunis);
			
			seaTile Barents_Sea = new seaTile("Barents_Sea",new int[] {591, 862, 839, 836, 824, 799, 779, 760, 779, 768, 746, 765, 756, 724, 693, 689, 747, 747, 652, 643, 623, 621, 591},
			new int[] {4, 3, 46, 22, 22, 87, 62, 64, 107, 107, 139, 156, 165, 153, 139, 124, 117, 93, 65, 52, 47, 52, 44}, 
			23,704,51);
			TileList.add(Barents_Sea);
			
			seaTile Norwegian_Sea = new seaTile("Norwegian_Sea",new int[] {587, 242, 236, 259, 259, 256, 248, 260, 271, 273, 285, 306, 380, 417, 429, 523, 588},
			new int[] {4, 6, 279, 277, 290, 293, 300, 301, 306, 277, 258, 240, 238, 220, 220, 79, 44}, 
			17,346,92);
			TileList.add(Norwegian_Sea);
			
			seaTile North_Atlantic = new seaTile("North_Atlantic",new int[] {238, 0, 0, 87, 105, 99, 103, 113, 124, 113, 121, 123, 145, 167, 188, 190, 204, 204, 204, 222, 221, 237},
			new int[] {4, 4, 432, 432, 412, 393, 383, 383, 368, 362, 356, 342, 346, 333, 336, 356, 354, 330, 309, 297, 288, 275}, 
			22,86,263);
			TileList.add(North_Atlantic);
			
			seaTile Irish_Sea = new seaTile("Irish_Sea",new int[] {188, 218, 218, 193, 203, 186, 203, 190, 163, 139, 94, 108, 133, 158, 177, 188},
			new int[] {360, 368, 390, 407, 414, 427, 445, 445, 458, 478, 433, 416, 407, 411, 364, 358}, 
			16,160,446);
			TileList.add(Irish_Sea);
			
			seaTile English_Channel = new seaTile("English_Channel",new int[] {281, 304, 281, 246, 243, 235, 225, 217, 205, 185, 163, 140, 163, 187, 198, 261, 274, 274, 265},
			new int[] {452, 474, 475, 497, 503, 504, 489, 489, 514, 502, 503, 481, 463, 471, 463, 473, 468, 465, 464}, 
			19,181,500);
			TileList.add(English_Channel);
			
			seaTile North_Sea = new seaTile("North_Sea",new int[] {378, 305, 276, 275, 281, 262, 266, 268, 275, 273, 284, 287, 286, 306, 325, 338, 340, 395, 400, 388, 372},
			new int[] {244, 246, 279, 311, 315, 345, 359, 380, 387, 418, 422, 431, 450, 471, 442, 433, 375, 374, 358, 353, 314}, 
			21,314,360);
			TileList.add(North_Sea);
			
			seaTile Heligoland = new seaTile("Heligoland",new int[] {343, 396, 393, 394, 381, 374, 366, 341, 342},
			new int[] {376, 375, 403, 431, 435, 429, 435, 432, 432}, 
			9,365,413);
			TileList.add(Heligoland);
			
			seaTile Skagerrak = new seaTile("Skagerrak",new int[] {437, 449, 454, 437, 430, 430, 402, 390, 391, 403, 436},
			new int[] {307, 322, 381, 382, 376, 348, 354, 353, 330, 330, 308}, 
			9,431,349);
			TileList.add(Skagerrak);
			
			seaTile South_Baltic = new seaTile("South_Baltic",new int[] {417, 420, 420, 433, 457, 467, 468, 479, 497, 532, 533, 548, 560, 566, 561, 561, 573, 507, 496, 478, 472, 455, 446, 432},
			new int[] {406, 424, 428, 435, 420, 419, 431, 433, 428, 416, 425, 415, 411, 400, 390, 355, 347, 345, 386, 391, 400, 398, 408, 408}, 
			24,525,391);
			TileList.add(South_Baltic);
			
			seaTile Gulf_Of_Bothnia = new seaTile("Gulf_Of_Bothnia",new int[] {653, 598, 568, 569, 560, 604, 590, 576, 565, 571, 523, 519, 527, 537, 536, 506, 581, 593, 590, 600, 645, 665, 653, },
			new int[] {280, 299, 285, 258, 237, 183, 165, 164, 183, 196, 238, 284, 284, 297, 318, 342, 344, 356, 319, 308, 305, 289, 279, }, 
			23,542,286);
			
			TileList.add(Gulf_Of_Bothnia);
			
			seaTile Bay_Of_Biscay = new seaTile("Bay_Of_Biscay",new int[] {0, 89, 160, 158, 199, 178, 52, 50, 18, 11, 16, 6, 22, 35, 53, 52, 45, 21, 0, 0, },
			new int[] {437, 437, 504, 517, 574, 632, 600, 632, 682, 684, 724, 735, 747, 746, 777, 784, 785, 823, 823, 443, }, 
			20,17,587);
			TileList.add(Bay_Of_Biscay);
			
			seaTile Gulf_Of_Lyon = new seaTile("Gulf_Of_Lyon",new int[] {252, 255, 273, 296, 337, 354, 375, 384, 363, 343, 342, 354, 351, 330, 331, 249, 237, 231, 182, 207, 234, 252, },
			new int[] {685, 664, 657, 670, 663, 653, 656, 683, 682, 688, 714, 724, 726, 736, 743, 742, 734, 743, 742, 700, 697, 686, }, 
			22,296,716);
			TileList.add(Gulf_Of_Lyon);
			
			seaTile Western_Mediterranean = new seaTile("Western_Mediterranean",new int[]{55, 55, 65, 71, 123, 153, 176, 246, 325, 336, 341, 349, 354, 337, 330, 328, 248, 232, 226, 188, 162, 140, 128, 55 },
			new int[] {780, 786, 788, 815, 825, 818, 811, 810, 825, 824, 819, 817, 779, 786, 774, 743, 744, 752, 745, 745, 767, 767, 784, 780,  }, 
			24,180,801);
			TileList.add(Western_Mediterranean);
			
			seaTile Tyrranean_Sea = new seaTile("Tyrranean_Sea",new int[]{362, 398, 394, 410, 438, 450, 473, 467, 437, 412, 377, 364, 358, 356, 366, 361, 355, 362, 366, },
			new int[]{686, 698, 702, 727, 737, 758, 778, 803, 807, 803, 834, 833, 820, 779, 743, 724, 724, 712, 684, }, 
			19,385,761);
			TileList.add(Tyrranean_Sea);
			
			seaTile Ionian_Sea = new seaTile("Ionian_Sea",new int[]{372, 400, 441, 458, 460, 509, 498, 500, 504, 523, 545, 552, 557, 571, 574, 606, 620, 617, 651, 651, 389, 373, 384,},
			new int[] {850, 814, 845, 848, 823, 791, 773, 768, 764, 767, 758, 772, 776, 800, 818, 848, 862, 864, 879, 898, 896, 886, 870,}, 
			22,515,846);
			TileList.add(Ionian_Sea);
			
			seaTile Aegean = new seaTile("Aegean",new int[]{625, 670, 668, 681, 677, 694, 708, 709, 678, 673, 667, 628, 615, 608, 632, 633, 612, 612, 602, 604, 620, 624, },
			new int[] {751, 740, 774, 803, 807, 827, 828, 835, 870, 863, 861, 861, 854, 827, 819, 807, 784, 781, 764, 759, 765, 747, }, 
			22,656,824);
			TileList.add(Aegean);
			
			seaTile Eastern_Mediterranean = new seaTile("Eastern_Mediterranean",new int[] {710, 679, 679, 652, 805, 846, 864, 865, 851, 852, 861, 845, 832, 823, 765, 750, 711, },
			new int[] {842, 870, 873, 896, 892, 880, 879, 841, 836, 823, 798, 808, 809, 826, 820, 843, 837 }, 
			17,771,878);
			TileList.add(Eastern_Mediterranean);
			
			seaTile Black_Sea = new seaTile("Black_Sea",new int[] {717, 747, 751, 775, 759, 775, 775, 799, 801, 828, 836, 924, 934, 910, 835, 821, 782, 754, 723, 713, 693, 688, 704, 718, 716, },
			new int[] {625, 595, 601, 605, 619, 627, 631, 640, 627, 616, 614, 639, 655, 689, 690, 681, 690, 720, 721, 721, 712, 694, 671, 638, 623, }, 
			25,739,695);
			TileList.add(Black_Sea);

			seaTile Adriatic = new seaTile("Adriatic",new int[] {427, 445, 450, 461, 466, 465, 491, 535, 539, 530, 483, 423, 428},
					new int[] {635, 636, 647, 638, 639, 660, 688, 719, 756, 763, 719, 656, 638}, 
					13,443,675);
			TileList.add(Adriatic);
			
			Petrograd.addAdj(new Tile[] {Finland, Gulf_Of_Bothnia, Moscow, Baltic_States, Barents_Sea});
			Moscow.addAdj(new Tile[] {Baltic_States, Petrograd, West_Ukraine, East_Ukraine, Poland });
			East_Ukraine.addAdj(new Tile[] {West_Ukraine, Moscow, Black_Sea, Armenia, Romania});
			Armenia.addAdj(new Tile[] {Eastern_Anatolia, Syria, Southern_Anatolia, Black_Sea, East_Ukraine});
			Black_Sea.addAdj(new Tile[] {East_Ukraine, Romania, Bulgaria, Istanbul, Eastern_Anatolia, Armenia,});
			Istanbul.addAdj(new Tile[] {Eastern_Anatolia, Southern_Anatolia, Black_Sea, Bulgaria, Aegean});
			Eastern_Anatolia.addAdj(new Tile[] {Southern_Anatolia, Istanbul, Black_Sea, Armenia});
			Southern_Anatolia.addAdj(new Tile[] {Eastern_Anatolia, Aegean, Eastern_Mediterranean, Syria, Armenia, Istanbul});
			Syria.addAdj(new Tile[] {Armenia, Southern_Anatolia, Eastern_Mediterranean, });
			West_Ukraine.addAdj(new Tile[] {East_Ukraine, Moscow, Poland, Romania, Galicia});
			Romania.addAdj(new Tile[] {Galicia, Hungary, West_Ukraine, Black_Sea, Bulgaria, Serbia, East_Ukraine});
			Bulgaria.addAdj(new Tile[] {Istanbul, Black_Sea, Romania, Serbia, Greece, Aegean});
			Greece.addAdj(new Tile[] {Bulgaria, Aegean, Serbia, Albania, Ionian_Sea,});
			Aegean.addAdj(new Tile[] {Eastern_Mediterranean, Ionian_Sea, Greece, Istanbul, Southern_Anatolia, Bulgaria});
			Eastern_Mediterranean.addAdj(new Tile[] {Aegean, Southern_Anatolia, Syria, Ionian_Sea, });
			Ionian_Sea.addAdj(new Tile[] {Sicily, Tunis, Tyrranean_Sea, Napoli, Aputia, Adriatic, Albania, Greece, Eastern_Mediterranean, Aegean});
			Albania.addAdj( new Tile[] {Serbia, Greece, Adriatic, Ionian_Sea, Trieste,});
			Serbia.addAdj(new Tile[] {Greece, Albania, Romania, Bulgaria, Hungary, Trieste});
			Hungary.addAdj(new Tile[] {Galicia, Trieste, Serbia, Romania, Galicia, Vienna});
			Galicia.addAdj(new Tile[] {West_Ukraine, Poland, Hungary, Czechia, Silesia, Romania,Trieste, Vienna});
			Poland.addAdj(new Tile[] {West_Ukraine, Silesia, Prussia, Baltic_States, Moscow, Galicia});
			Prussia.addAdj(new Tile[] {Poland, Baltic_States, South_Baltic, Berlin, Silesia});
			South_Baltic.addAdj(new Tile[] {Prussia, Gulf_Of_Bothnia, Baltic_States, Denmark, Berlin, Kiel, Sweden});
			Gulf_Of_Bothnia.addAdj(new Tile[] {Baltic_States, South_Baltic, Finland, Petrograd, Sweden, });
			Barents_Sea.addAdj(new Tile[] {Norwegian_Sea, Norway, Petrograd});
			Finland.addAdj(new Tile[] {Sweden, Norway, Gulf_Of_Bothnia, Petrograd,});
			Silesia.addAdj(new Tile[] {Berlin, Czechia, Galicia, Poland, Bavaria,Prussia});
			Berlin.addAdj(new Tile[] {Prussia, Silesia, Kiel, South_Baltic, Bavaria});
			Sweden.addAdj(new Tile[] {Finland, Gulf_Of_Bothnia, Norway, Denmark, Skagerrak, South_Baltic});
			Norwegian_Sea.addAdj(new Tile[] {Barents_Sea, Norway, North_Atlantic, Clyde, Edinburgh, North_Sea});
			Norway.addAdj(new Tile[] {Finland, Sweden, North_Sea, Norwegian_Sea, Barents_Sea, Petrograd, Skagerrak});
			North_Sea.addAdj(new Tile[] {Norwegian_Sea, Edinburgh, York, London, English_Channel, Heligoland, Skagerrak, Netherlands, Amsterdam, Norway, Denmark});
			Skagerrak.addAdj(new Tile[] {Sweden, Norway, Denmark, North_Sea, South_Baltic});
			Denmark.addAdj(new Tile[] {Kiel, Heligoland, North_Sea, Skagerrak, South_Baltic, Sweden});
			Heligoland.addAdj(new Tile[] {Denmark, North_Sea, Kiel, Netherlands});
			Kiel.addAdj(new Tile[] {Berlin, Denmark, South_Baltic, Heligoland, Netherlands, Rhineland, Bavaria});
			Bavaria.addAdj(new Tile[] {Rhineland, Kiel, Burgundy, Tyrol, Czechia, Silesia, Berlin });
			Czechia.addAdj(new Tile[] {Bavaria, Vienna, Silesia, Tyrol, Galicia});
			Vienna.addAdj(new Tile[] {Hungary, Trieste, Tyrol, Czechia, Galicia});
			Tyrol.addAdj(new Tile[] {Bavaria, Czechia, Vienna, Venice, Piedmont});
			Trieste.addAdj(new Tile[] {Tyrranean_Sea, Serbia, Albania, Hungary, Tyrol, Venice, Vienna});
			Venice.addAdj(new Tile[] {Trieste, Tyrol, Aputia, Piedmont, Rome, Tuscany, Adriatic});
			Aputia.addAdj(new Tile[] {Venice, Rome, Sicily, Adriatic, Napoli, Ionian_Sea});
			Napoli.addAdj(new Tile[] { Ionian_Sea,Tyrranean_Sea, Sicily, Aputia, Rome, });
			Sicily.addAdj(new Tile[] {Napoli, Tyrranean_Sea, Ionian_Sea, });
			Rome.addAdj(new Tile[] {Tyrranean_Sea, Tuscany, Napoli, Venice, Aputia, });
			Tuscany.addAdj(new Tile[] {Gulf_Of_Lyon, Tyrranean_Sea, Rome, Piedmont, Venice});
			Piedmont.addAdj(new Tile[] {Marseilles, Gulf_Of_Lyon, Tuscany, Venice, Tyrol});
			Marseilles.addAdj(new Tile[] {Piedmont, Gulf_Of_Lyon, Burgundy, Gascony, Spain, });
			Burgundy.addAdj(new Tile[] {Paris, Marseilles, Gascony, Picardy, Amsterdam, Rhineland, Bavaria});
			Netherlands.addAdj(new Tile[] {Rhineland, Kiel, Amsterdam, Heligoland, North_Sea, });
			Amsterdam.addAdj(new Tile[] {Burgundy, Rhineland,English_Channel, Netherlands, Picardy, Amsterdam, North_Sea});
			Picardy.addAdj(new Tile[] {English_Channel, Brest, Paris, Amsterdam, Burgundy});
			Paris.addAdj(new Tile[] {Brest, Picardy, Burgundy, Gascony, });
			Brest.addAdj(new Tile[] {English_Channel, Paris, Bay_Of_Biscay, Gascony, Picardy});
			Gascony.addAdj(new Tile[] {Paris, Brest, Marseilles, Bay_Of_Biscay, Spain,Burgundy});
			Spain.addAdj(new Tile[] {Portugal, Gascony, Marseilles, Bay_Of_Biscay, Gulf_Of_Lyon, Western_Mediterranean});
			Portugal.addAdj(new Tile[] {Spain, Bay_Of_Biscay});
			Algeria.addAdj(new Tile[] {Tunis, Western_Mediterranean, Bay_Of_Biscay});
			Tunis.addAdj(new Tile[] {Tyrranean_Sea, Ionian_Sea, Algeria, Western_Mediterranean});
			London.addAdj(new Tile[] {English_Channel, North_Sea, Wales, York, });
			York.addAdj(new Tile[] {London, Wales, Edinburgh, North_Sea, Liverpool, });
			Wales.addAdj(new Tile[] {London, Liverpool, York, English_Channel, Irish_Sea, });
			Liverpool.addAdj(new Tile[] {Irish_Sea, North_Atlantic, Clyde, Wales, York, Edinburgh,  });	
			Clyde.addAdj(new Tile[] {Edinburgh, Liverpool, North_Atlantic, Norwegian_Sea, });
			North_Atlantic.addAdj(new Tile[] {Irish_Sea, Clyde, Norwegian_Sea, Bay_Of_Biscay, });
			Bay_Of_Biscay.addAdj(new Tile[] {Irish_Sea, North_Atlantic, Spain, Portugal, Gascony, Brest, English_Channel, Algeria, Western_Mediterranean});
			Western_Mediterranean.addAdj(new Tile[] {Spain, Algeria, Gulf_Of_Lyon, Tyrranean_Sea, Tunis, Bay_Of_Biscay, });
			Gulf_Of_Lyon.addAdj(new Tile[] {Spain, Tyrranean_Sea, Marseilles, Piedmont , Western_Mediterranean, Tuscany, });
			Irish_Sea.addAdj(new Tile[] {North_Atlantic, English_Channel, Bay_Of_Biscay, Wales, Liverpool});
			English_Channel.addAdj(new Tile[] {Brest, Picardy, Bay_Of_Biscay, Wales, North_Sea, London, Amsterdam, Irish_Sea});
			Heligoland.addAdj(new Tile[] { Netherlands, Kiel, Denmark, North_Sea});
			Baltic_States.addAdj(new Tile[] {Petrograd, Gulf_Of_Bothnia, South_Baltic, Prussia, Poland, Moscow});
			Tyrranean_Sea.addAdj(new Tile[] {Tuscany, Rome, Napoli, Sicily, Gulf_Of_Lyon, Western_Mediterranean, Tunis, Ionian_Sea});
			Adriatic.addAdj(new Tile[] {Venice, Trieste, Aputia, Albania, Ionian_Sea});
			Rhineland.addAdj(new Tile[] {Kiel, Bavaria, Netherlands, Amsterdam, Burgundy});
			Edinburgh.addAdj(new Tile[] {York, Clyde, North_Sea, Norwegian_Sea, Liverpool});
			
		/********************************************************************************End of Tile Stuff*************************************************************************************************************************/
			playerMap.get(playerID[0]).getArmy().add(new landUnit(playerID[0], Petrograd));
			currentTile = Berlin;
			
			
			
			new Thread(this).start();
		}
}