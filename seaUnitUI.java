import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class seaUnitUI extends UnitUI{
	
	seaUnit selectedUnit;
	boolean moveButtonPressed;
	boolean supportButtonPressed;
	boolean convoyButtonPressed;
	
	public seaUnitUI(seaUnit u)
	{
		super();
		moveButtonPressed = false;
		supportButtonPressed = false;
		convoyButtonPressed = false;
		
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
		
		final JButton convoy = new JButton("Convoy");
		support.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				convoyButtonPressed = true;
				dispose();
			}
		
		});
		
		this.add(move, BorderLayout.CENTER);
		this.add(support, BorderLayout.WEST);
		this.add(convoy, BorderLayout.EAST);
	}
	
	
}
