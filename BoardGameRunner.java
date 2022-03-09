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

public class BoardGameRunner extends JFrame{

	private static int WIDTH = 1080;
	private static int LENGTH = 800;
	
	public static void main(String[] args) throws Exception {
		BoardGameRunner a = new BoardGameRunner();
		a.run();
		
	}

	public void run() throws Exception {
		
		setSize(WIDTH, LENGTH);
		BoardGame a = new BoardGame();
		
		add(a);
		
		setVisible(true);	

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
