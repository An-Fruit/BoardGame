import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;

public class buildUI extends UnitUI{
	
	boolean buildFleetButton;
	boolean buildLandUnit;
	
	public buildUI() {
		super();
		buildFleetButton = false;
		buildLandUnit = false;
		
		final JButton buildFleet = new JButton("Build Fleet");
		buildFleet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buildFleetButton = true;
				dispose();
			}	
		});
		
		final JButton buildArmy = new JButton("Build Army");
		buildArmy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buildLandUnit = true;
				dispose();
			}	
		});
		
		add(buildFleet, BorderLayout.CENTER);
		add(buildArmy, BorderLayout.WEST);
	}
	
}