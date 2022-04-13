import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;

public class moveArrow {
	Point start;
	Point end;
	Polygon arrowhead;
	Polygon body;
	public moveArrow(Point s, Point e) {
		start = s;
		end = e;
		double angle = Math.acos();
	}
	
	
	public void paintComponent(Graphics window) {
		window.drawLine(start.x, start.y, end.x, end.y);
		
	}

}
