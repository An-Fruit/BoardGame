import java.awt.Graphics;
import java.awt.MouseInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JInternalFrame;

public class ConsoleUI extends JInternalFrame implements Runnable{

	
	File read;
	
	public ConsoleUI() {
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setSize(400,400);
		setClosable(true);
		read = null;
		new Thread(this).start();
	}
	
	public void printOrder(File f) throws FileNotFoundException {
		Scanner scan = new Scanner(f);
		
	}
	
	public void paint(Graphics g) {
		if (read != null) {
			
			int mouseX = MouseInfo.getPointerInfo().getLocation().x;
			int mouseY = MouseInfo.getPointerInfo().getLocation().y;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();
	}
}
