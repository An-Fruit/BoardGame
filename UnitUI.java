import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;

public class UnitUI extends JInternalFrame implements MouseListener{
	
	
	
	landUnit selectedUnit;
	static Tile selectedTile;
	boolean buttonPressed;
	public UnitUI(landUnit u, Tile t) {
		selectedTile = t;
		selectedUnit = u;
		buttonPressed = false;
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setSize(100,100);
		
		final JButton move = new JButton("Move Unit");
		move.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonPressed = true;
			}
			
		});
		
		add(move);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (buttonPressed) {
			selectedUnit.move(selectedTile);
			buttonPressed = false;
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

	
}
