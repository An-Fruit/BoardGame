import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoardGame extends JPanel implements MouseListener, Runnable{
	

	private ArrayList<Tile> TileList;
	public ArrayList<Integer> xloc;
	public ArrayList<Integer> yloc;
	// constructor
	public BoardGame() {
		setBackground(Color.WHITE);
		addMouseListener(this);

		TileList = new ArrayList<>();
		xloc = new ArrayList<>();
		yloc = new ArrayList<>();
		
		landTile Petrograd = new landTile(new int[] {989, 988, 864, 742, 672, 641, 669, 673, 632, 667, 988},
									new int[] {185, 5, 4, 188, 129, 131, 250, 287, 315, 355, 183}, 
									11, true, true);
		TileList.add(Petrograd);
		
		landTile Baltic_States = new landTile(new int[] {593, 642, 662, 621, 598, 599, 566, 580, 595, 615, 595},
				new int[] {318, 327, 359, 449, 439, 417, 359, 346, 359, 353, 321}, 
				11, true, false);
		TileList.add(Baltic_States);
		
		landTile Moscow = new landTile(new int[] {990, 668, 659, 622, 630, 763, 895, 991, 989},
				new int[] {188, 354, 431, 452, 475, 457, 446, 514, 188}, 
				9, false, true);
		TileList.add(Moscow);
		
		landTile East_Ukraine = new landTile(new int[] {989, 988, 936, 929, 835, 862, 791, 808, 826, 795, 764, 779, 750, 714, 703, 693, 750, 767, 778, 895},
				new int[] {520, 690, 672, 637, 610, 552, 591, 606, 604, 633, 617, 601, 589, 621, 623, 596, 546, 461, 455, 449}, 
				20, true, true);
		TileList.add(East_Ukraine);
		
		landTile West_Ukraine = new landTile(new int[] {630, 624, 655, 662, 669, 688, 703, 706, 746, 762, 631},
				new int[] {482, 519, 535, 569, 569, 591, 587, 570, 546, 466, 483}, 
				11, false, false);
		TileList.add(West_Ukraine);
		
		landTile Poland = new landTile(new int[] {596, 620, 630, 624, 599, 583, 564, 531, 522, 527, 555, 585, 595},
				new int[] {443, 453, 480, 512, 522, 511, 524, 515, 476, 464, 451, 452, 444}, 
				13, false, true);
		TileList.add(Poland);
		
		landTile Galicia = new landTile(new int[] {526, 547, 565, 581, 594, 622, 653, 658, 643, 550, 541, 517, 523},
				new int[] {539, 520, 526, 514, 523, 522, 539, 587, 591, 549, 544, 547, 539}, 
				13, false, false);
		TileList.add(Galicia);
		
		landTile Prussia = new landTile(new int[] {567, 576, 578, 587, 595, 595, 584, 556, 530, 523, 484, 475, 478, 501, 529, 528, 549, 561, 568, 566, 566},
				new int[] {386, 395, 412, 410, 418, 441, 450, 449, 459, 474, 470, 452, 437, 427, 420, 431, 417, 412, 399, 390, 387}, 
				21, true, false);
		TileList.add(Prussia);
		
		landTile Romania = new landTile(new int[] {663, 667, 687, 696, 712, 702, 669, 651, 602, 598, 601, 658, 666, 645, 644, 662, 662},
				new int[] {574, 574, 595, 630, 628, 665, 661, 673, 668, 654, 642, 631, 623, 602, 598, 591, 574}, 
				17, true, true);
		TileList.add(Romania);
		
		landTile Sweden = new landTile(new int[] {556, 579, 587, 577, 563, 570, 523, 518, 528, 536, 534, 507, 496, 477, 472, 461, 451, 482, 531, 559},
				new int[] {95, 113, 162, 161, 183, 197, 242, 287, 287, 300, 318, 329, 381, 384, 397, 397, 322, 207, 110, 97}, 
				20, true, true);
		TileList.add(Sweden);
		
		landTile Finland = new landTile(new int[] {638, 630, 667, 658, 569, 573, 565, 564, 609, 590, 583, 563, 578, 600, 636},
				new int[] {90, 97, 250, 276, 284, 254, 249, 237, 180, 158, 113, 84, 94, 93, 90}, 
				15, true, false);
		TileList.add(Finland);
		
		landTile Norway = new landTile(new int[] {648, 640, 599, 598, 580, 560, 552, 554, 479, 448, 438, 402, 394, 377, 417, 431, 526, 589, 625, 626, 638, 638, 646},
				new int[] {73, 87, 68, 89, 90, 77, 92, 95, 206, 315, 304, 325, 325, 314, 224, 225, 86, 50, 58, 51, 54, 67, 72}, 
				23, true, true);
		TileList.add(Norway);
		
		landTile Denmark = new landTile(new int[] {428, 428, 437, 448, 454, 443, 432, 419, 419, 396, 402, 427},
				new int[] {351, 380, 386, 385, 394, 407, 400, 401, 398, 401, 358, 352}, 
				12, true, true);
		TileList.add(Denmark);
		
		landTile Silesia = new landTile(new int[] {520, 528, 542, 514, 485, 470, 461, 466, 484, 519},
				new int[] {480, 517, 521, 523, 504, 506, 489, 489, 474, 480}, 
				10, false, false);
		TileList.add(Silesia);
		
		landTile Berlin = new landTile(new int[] {435, 457, 465, 465, 475, 471, 482, 462, 430, 430, 436},
				new int[] {436, 423, 423, 434, 434, 453, 471, 485, 488, 453, 435}, 
				11, true, true);
		TileList.add(Berlin);
		
		landTile Czechia = new landTile(new int[] {471, 481, 502, 511, 525, 515, 480, 474, 458, 437, 432, 462, 470},
				new int[] {509, 509, 527, 528, 543, 547, 550, 562, 563, 543, 519, 514, 508}, 
				13, false, false);
		TileList.add(Czechia);
		
		landTile Kiel = new landTile(new int[] {397, 411, 418, 413, 419, 431, 425, 426, 397, 350, 366, 373, 379, 396, 396},
				new int[] {404, 404, 421, 424, 432, 436, 452, 490, 506, 477, 466, 434, 439, 433, 405}, 
				15, true, true);
		TileList.add(Kiel);
		
		landTile Bavaria = new landTile(new int[] {458, 428, 433, 436, 372, 357, 341, 344, 393, 429, 458},
				new int[] {489, 515, 542, 582, 573, 576, 572, 551, 511, 493, 490}, 
				11, false, true);
		TileList.add(Bavaria);
		
		landTile Hungary = new landTile(new int[] {502, 527, 551, 569, 612, 640, 640, 662, 657, 599, 595, 527, 525, 511, 502},
				new int[] {606, 588, 557, 552, 565, 594, 603, 624, 628, 639, 649, 643, 624, 624, 606}, 
				15, false, true);
		TileList.add(Hungary);
		
		landTile Vienna = new landTile(new int[] {480, 506, 517, 538, 547, 526, 507, 497, 479, 486, 475, 480},
				new int[] {554, 550, 552, 551, 556, 583, 592, 605, 603, 581, 567, 554}, 
				12, false, true);
		TileList.add(Vienna);
		
		landTile Tyrol = new landTile(new int[] {382, 440, 453, 468, 480, 476, 464, 432, 420, 406, 395, 398, 381},
				new int[] {581, 585, 567, 568, 581, 601, 609, 609, 612, 629, 614, 605, 592}, 
				13, false, false);
		TileList.add(Tyrol);
		
		landTile Trieste = new landTile(new int[] {457, 463, 477, 494, 499, 506, 519, 523, 539, 540, 532, 541, 538, 534, 496, 469, 467, 458, 452, 450, 457},
				new int[] {613, 614, 605, 612, 610, 627, 629, 648, 648, 671, 678, 702, 703, 714, 689, 657, 638, 635, 642, 635, 614}, 
				21, false, true);
		TileList.add(Trieste);
		
		landTile Serbia = new landTile(new int[] {544, 593, 600, 600, 573, 573, 567, 567, 545, 537, 543},
				new int[] {650, 655, 689, 700, 744, 741, 736, 710, 699, 681, 673}, 
				11, true, true);
		TileList.add(Serbia);
		
		landTile Bulgaria = new landTile(new int[] {601, 669, 700, 685, 690, 673, 667, 640, 631, 598, 604, 600},
				new int[] {672, 665, 669, 695, 710, 714, 741, 744, 726, 733, 688, 673}, 
				12, false, true);
		TileList.add(Bulgaria);
		
		landTile Greece = new landTile(new int[] {628, 635, 623, 619, 603, 598, 609, 601, 629, 616, 615, 606, 608, 599, 595, 577, 581, 607, 604, 572, 559, 573, 573, 598, 599, 626},
				new int[] {731, 746, 746, 762, 755, 763, 778, 781, 817, 817, 821, 821, 845, 838, 844, 819, 816, 814, 801, 798, 772, 761, 750, 740, 735, 731}, 
				26, true, false);
		TileList.add(Greece);
		
		landTile Albania = new landTile(new int[] {539, 541, 551, 561, 562, 568, 568, 554, 549, 541, 540},
				new int[] {713, 709, 709, 716, 734, 745, 758, 770, 754, 754, 712}, 
				11, true, false);
		TileList.add(Albania);
		
		landTile Istanbul = new landTile(new int[] {677, 671, 675, 672, 672, 692, 708, 748, 761, 763, 761, 724, 718, 709, 692, 679},
				new int[] {718, 737, 747, 752, 774, 773, 779, 775, 769, 730, 729, 730, 724, 724, 712, 717}, 
				16, true, true);
		TileList.add(Istanbul);
		
		landTile Eastern_Anatolia = new landTile(new int[] {759, 782, 833, 905, 868, 829, 798, 766, 767, 763, 759},
				new int[] {724, 695, 693, 725, 724, 759, 758, 775, 731, 722, 721}, 
				11, true, true);
		TileList.add(Eastern_Anatolia);
		
		landTile Western_Anatolia = new landTile(new int[] {685, 691, 708, 751, 766, 800, 905, 872, 846, 831, 823, 798, 751, 737, 726, 714, 709, 697, 684, 684},
				new int[] {778, 777, 784, 779, 781, 763, 730, 790, 811, 810, 826, 834, 843, 843, 837, 837, 822, 823, 805, 778}, 
				20, true, true);
		TileList.add(Western_Anatolia);
		
		landTile Armenia = new landTile(new int[] {908, 935, 990, 990, 919, 908, 908},
				new int[] {693, 676, 695, 770, 747, 730, 693}, 
				7, false, false);
		TileList.add(Armenia);
		
		landTile Syria = new landTile(new int[] {943, 989, 999, 871, 869, 861, 878, 887, 943},
				new int[] {758, 774, 896, 897, 843, 839, 791, 774, 758}, 
				9, false, false);
		TileList.add(Syria);
		
		landTile Rhineland = new landTile(new int[] {346, 379, 359, 344, 338, 337, 342, 345},
				new int[] {480, 511, 542, 547, 542, 522, 518, 480}, 
				8, false, false);
		TileList.add(Rhineland);
		
		landTile Venice = new landTile(new int[] {452, 447, 425, 418, 441, 445, 428, 382, 397, 405, 421, 432, 451},
				new int[] {614, 632, 632, 657, 672, 706, 689, 651, 625, 634, 617, 613, 613}, 
				13, true, true);
		TileList.add(Venice);
		
		landTile Aputia = new landTile(new int[] {450, 452, 478, 524, 504, 497, 477, 458, 450},
				new int[] {709, 708, 720, 761, 759, 766, 759, 721, 710}, 
				9, true, false);
		TileList.add(Aputia);
		
		landTile Tuscany = new landTile(new int[] {376, 381, 407, 423, 402, 385, 377},
				new int[] {659, 655, 665, 686, 695, 679, 658}, 
				7, true, false);
		TileList.add(Tuscany);
		
		landTile Rome = new landTile(new int[] {402, 424, 441, 444, 452, 414, 400},
				new int[] {702, 692, 711, 711, 722, 726, 702}, 
				7, true, true);
		TileList.add(Rome);
		
		landTile Napoli = new landTile(new int[] {438, 453, 472, 497, 496, 476, 439},
				new int[] {735, 724, 758, 769, 777, 777, 737}, 
				6, true, true);
		TileList.add(Napoli);
		
		landTile Sicily = new landTile(new int[] {475, 498, 504, 483, 460, 455, 444, 403, 410, 431, 461, 467, 477, 498},
				new int[] {780, 779, 788, 818, 817, 844, 843, 815, 805, 811, 808, 810, 797, 780}, 
				14, true, false);
		TileList.add(Sicily);
		
		landTile Piedmont = new landTile(new int[] {336, 388, 395, 379, 380, 355, 340, 335},
				new int[] {611, 614, 622, 637, 656, 652, 659, 610}, 
				8, true, false);
		TileList.add(Piedmont);
		
		landTile Burgundy = new landTile(new int[] {310, 334, 343, 340, 314, 285, 273, 253, 302, 310, 314},
				new int[] {511, 541, 551, 571, 601, 601, 621, 604, 539, 514, 515}, 
				8, true, false);
		TileList.add(Burgundy);
		
		
		landTile Netherlands = new landTile(new int[] {311, 331, 334, 344, 362, 365, 342, 328, 310},
				new int[] {472, 483, 494, 473, 464, 437, 433, 443, 469}, 
				9, true, false);
		TileList.add(Netherlands);
		
		landTile Amsterdam = new landTile(new int[] {283, 306, 311, 331, 337, 334, 313, 297, 274, 280},
				new int[] {478, 476, 483, 487, 518, 522, 511, 494, 490, 480}, 
				10, true, true);
		TileList.add(Amsterdam);
		
		landTile Paris = new landTile(new int[] {235, 302, 299, 253, 236, 236},
				new int[] {523, 525, 538, 593, 584, 522}, 
				6, false, true);
		TileList.add(Paris);
		
		landTile Picardy = new landTile(new int[] {270, 295, 308, 305, 240, 248, 269},
				new int[] {495, 497, 512, 521, 519, 502, 493}, 
				7, true, false);
		TileList.add(Picardy);
		
		landTile Brest = new landTile(new int[] {163, 184, 205, 235, 234, 196, 195, 161},
				new int[] {507, 506, 517, 516, 575, 565, 544, 515}, 
				8, true, true);
		TileList.add(Brest);
		
		landTile Gascony = new landTile(new int[] {204, 230, 230, 252, 250, 267, 236, 228, 179, 179, 204},
				new int[] {577, 580, 585, 596, 602, 620, 638, 658, 647, 636, 576}, 
				11, true, false);
		TileList.add(Gascony);
		
		landTile Marseilles = new landTile(new int[] {288, 309, 310, 329, 335, 333, 300, 275, 254, 231, 237, 267, 280},
				new int[] {607, 604, 607, 611, 658, 663, 669, 654, 676, 660, 643, 624, 626}, 
				13, true, true);
		TileList.add(Marseilles);
		
		landTile Spain = new landTile(new int[] {51, 52, 76, 173, 175, 226, 253, 235, 207, 175, 183, 159, 138, 124, 51, 41, 89, 52},
				new int[] {629, 604, 598, 634, 647, 662, 686, 697, 697, 734, 744, 765, 768, 784, 777, 749, 639, 627}, 
				18, true, true);
		TileList.add(Spain);
		
		landTile Portugal = new landTile(new int[] {50, 89, 41, 25, 5, 15, 11, 18, 51},
				new int[] {629, 644, 744, 745, 734, 727, 692, 683, 634}, 
				9, true, true);
		TileList.add(Portugal);
		
		landTile London = new landTile(new int[] {239, 249, 261, 271, 282, 285, 284, 265, 262, 271, 264, 236, 236},
				new int[] {443, 432, 430, 422, 426, 431, 446, 458, 465, 467, 470, 467, 444}, 
				13, true, true);
		TileList.add(London);
		
		landTile Wales = new landTile(new int[] {185, 206, 248, 238, 236, 197, 191, 166, 183, 200, 206},
				new int[] {417, 416, 432, 444, 466, 461, 469, 460, 450, 452, 444}, 
				11, true, false);
		TileList.add(Wales);
		
		landTile York = new landTile(new int[] {250, 261, 264, 263, 272, 270, 261, 250, 246, 242, 243},
				new int[] {362, 361, 364, 384, 392, 419, 428, 428, 420, 418, 398}, 
				11, true, false);
		TileList.add(York);
		
		landTile Liverpool = new landTile(new int[] {209, 220, 226, 248, 243, 241, 230, 203, 197, 219},
				new int[] {354, 340, 339, 360, 395, 419, 414, 413, 406, 391}, 
				10, true, true);
		TileList.add(Liverpool);
		
		landTile Edinburgh = new landTile(new int[] {230, 236, 246, 262, 276, 259, 262, 250, 231},
				new int[] {340, 316, 305, 307, 316, 345, 357, 360, 342}, 
				9, true, true);
		TileList.add(Edinburgh);
		
		landTile Clyde = new landTile(new int[] {217, 230, 246, 251, 257, 258, 239, 222, 205, 206, 217},
				new int[] {338, 314, 303, 292, 292, 283, 279, 289, 311, 328, 338}, 
				11, true, false);
		TileList.add(Clyde);
		
		landTile Algeria = new landTile(new int[] {0, 47, 62, 123, 133, 170, 244, 266, 286, 330, 319, 315, 315, 2},
				new int[] {825, 790, 790, 828, 812, 813, 812, 821, 819, 829, 836, 883, 897, 895}, 
				14, true, false);
		TileList.add(Algeria);
		
		landTile Tunis = new landTile(new int[] {326, 336, 345, 360, 358, 380, 368, 381, 370, 380, 317, 316, 319},
				new int[] {831, 830, 822, 823, 836, 837, 849, 874, 888, 898, 896, 874, 841}, 
				13, true, true);
		TileList.add(Tunis);
		
	
		
		new Thread(this).start();
	}
	
	// paints the Diplomap onto graphics
	public void paint( Graphics window )
	{

		Graphics2D g2 = (Graphics2D) window;
		
		g2.drawPolygon(TileList.get(0).collisionhull);
		g2.drawPolygon(TileList.get(1).collisionhull);
		g2.drawPolygon(TileList.get(2).collisionhull);
		g2.drawPolygon(TileList.get(3).collisionhull);
		g2.drawPolygon(TileList.get(4).collisionhull);
		g2.drawPolygon(TileList.get(5).collisionhull);
		g2.drawPolygon(TileList.get(6).collisionhull);
		g2.drawPolygon(TileList.get(7).collisionhull);
		g2.drawPolygon(TileList.get(8).collisionhull);
		g2.drawPolygon(TileList.get(9).collisionhull);
		g2.drawPolygon(TileList.get(10).collisionhull);
		g2.drawPolygon(TileList.get(11).collisionhull);
		g2.drawPolygon(TileList.get(12).collisionhull);
		g2.drawPolygon(TileList.get(13).collisionhull);
		g2.drawPolygon(TileList.get(14).collisionhull);
		g2.drawPolygon(TileList.get(15).collisionhull);
		g2.drawPolygon(TileList.get(16).collisionhull);
		g2.drawPolygon(TileList.get(17).collisionhull);
		g2.drawPolygon(TileList.get(18).collisionhull);
		g2.drawPolygon(TileList.get(19).collisionhull);
		g2.drawPolygon(TileList.get(20).collisionhull);
		g2.drawPolygon(TileList.get(21).collisionhull);
		g2.drawPolygon(TileList.get(22).collisionhull);
		g2.drawPolygon(TileList.get(23).collisionhull);
		g2.drawPolygon(TileList.get(24).collisionhull);
		g2.drawPolygon(TileList.get(25).collisionhull);
		g2.drawPolygon(TileList.get(26).collisionhull);
		g2.drawPolygon(TileList.get(27).collisionhull);
		g2.drawPolygon(TileList.get(28).collisionhull);
		g2.drawPolygon(TileList.get(29).collisionhull);
		g2.drawPolygon(TileList.get(30).collisionhull);
		g2.drawPolygon(TileList.get(31).collisionhull);
		g2.drawPolygon(TileList.get(32).collisionhull);
		g2.drawPolygon(TileList.get(33).collisionhull);
		g2.drawPolygon(TileList.get(34).collisionhull);
		g2.drawPolygon(TileList.get(35).collisionhull);
		g2.drawPolygon(TileList.get(36).collisionhull);
		g2.drawPolygon(TileList.get(37).collisionhull);
		g2.drawPolygon(TileList.get(38).collisionhull);
		g2.drawPolygon(TileList.get(39).collisionhull);
		g2.drawPolygon(TileList.get(40).collisionhull);
		g2.drawPolygon(TileList.get(41).collisionhull);
		g2.drawPolygon(TileList.get(42).collisionhull);
		g2.drawPolygon(TileList.get(43).collisionhull);
		g2.drawPolygon(TileList.get(44).collisionhull);
		g2.drawPolygon(TileList.get(45).collisionhull);
		g2.drawPolygon(TileList.get(46).collisionhull);
		g2.drawPolygon(TileList.get(47).collisionhull);
		g2.drawPolygon(TileList.get(48).collisionhull);
		g2.drawPolygon(TileList.get(49).collisionhull);
		g2.drawPolygon(TileList.get(50).collisionhull);
		g2.drawPolygon(TileList.get(51).collisionhull);
		g2.drawPolygon(TileList.get(52).collisionhull);
		g2.drawPolygon(TileList.get(53).collisionhull);
		g2.drawPolygon(TileList.get(54).collisionhull);
		g2.drawPolygon(TileList.get(55).collisionhull);
		g2.drawPolygon(TileList.get(56).collisionhull);
		
		

//
//		Image map = Toolkit.getDefaultToolkit().getImage("DiploMap.png");
//		
//		g2.drawImage(map, 0, 0, 990, 900, this);
//		
//		window.drawString(MouseInfo.getPointerInfo().getLocation().x+ " " + MouseInfo.getPointerInfo().getLocation().y,900 , 100);
//		
////		
	}
	
	
	public int[] mouseLoc(int x, int y) {
		int[] num = {x, y};
		return num;
		
	}
	
	public void mouseClicked(MouseEvent e) {
		int loc1 = MouseInfo.getPointerInfo().getLocation().x;
		int loc2 = MouseInfo.getPointerInfo().getLocation().y;
		System.out.println(loc1 + " " + loc2);
		xloc.add(loc1);
		yloc.add(loc2);
		System.out.println(xloc);
		System.out.println(yloc);
		System.out.println(xloc.size());
		
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
	 
	public void run()
	{
		// TODO Auto-generated method
		try
		{
			while( true )
			{	
			   Thread.sleep(100);
			   repaint();
			}
		}
		catch( Exception e )
		{
			System.out.println("error:");
		}

	}
}
