import java.io.*;


import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.text.*;
import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Character.*;
import static java.util.Collections.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import static java.lang.Math.*;
import static java.util.Arrays.*;

public class BoardGameRunner
{
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 1000;

	public static void main( String args[] )
	{		
		JFrame window = new JFrame("Board Game Runner");
		
		final JDesktopPane desktop = new JDesktopPane();
		BoardGame gameMap = new BoardGame(desktop);
		
		desktop.add(gameMap);		
		
		
		window.setVisible(true);
		window.add(desktop);
		window.setSize(WIDTH,HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}


}
