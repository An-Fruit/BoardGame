import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;

public class landUnitUI extends UnitUI {
	
	landUnit selectedUnit;
	boolean moveButtonPressed;
	boolean supportButtonPressed;

	public landUnitUI(landUnit u) {
		super();
		selectedUnit = u;
		moveButtonPressed = false;
		supportButtonPressed = false;
		
		final JButton move = new JButton("Move Unit");
		move.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveButtonPressed = true;
				dispose();
			}	
		});
		
		final JButton support = new JButton("Support");
		support.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				supportButtonPressed = true;
				dispose();
			}
		
		});
		
		this.add(move, BorderLayout.CENTER);
		this.add(support, BorderLayout.NORTH);
		
	}
	

}
