import java.io.*;

import java.util.*;

import javax.swing.JFrame;

import java.text.*;
import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Character.*;
import static java.util.Collections.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class BoardGameRunner extends JFrame
{
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 1000;

	public BoardGameRunner()
	{
		super("Board Game Runner");

		setSize(WIDTH,HEIGHT);
			
        BoardGame scene = new BoardGame();
       
		add(scene);
		
		setVisible(true);	

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main( String args[] )
	{
		BoardGameRunner run = new BoardGameRunner();
	}
}
