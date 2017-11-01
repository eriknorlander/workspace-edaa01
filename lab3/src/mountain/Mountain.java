package mountain;

import fractal.*;

import java.util.Iterator;
import java.util.LinkedList;

public class Mountain extends Fractal {
	private Point a;
	private Point b;
	private Point c;
	private LinkedList<Side> list;
	private double dev;
	
	public Mountain(Point a, Point b, Point c, double dev) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.dev = dev;
		list = new LinkedList<Side>();
	}
	
	
	/**
	 * Returns the title.
	 * @return the title
	 */
	public String getTitle() {
		return "Mountain Fractal";
	}
	
	public void draw(TurtleGraphics turtle) {	
		fractalLine(turtle, order, a, b, c, dev);
	}
	
	private Point getMid(Point one, Point two, double dev) {
		Iterator<Side> itr = list.iterator();
		while (itr.hasNext()) {
			Side temp = itr.next();
			if ((temp.getP1().equals(one) && temp.getP2().equals(two))
					|| (temp.getP1().equals(two) && temp.getP2().equals(one))) {
				itr.remove();
				return temp.getM();
			}
		}
		Side fresh = new Side(one, two, dev);
		list.add(fresh);
		return fresh.getM();
	}
	
	private void fractalLine(TurtleGraphics turtle, int order, Point a, Point b, Point c, double dev) {
		turtle.penDown();
		if (order == 0) {			
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		} else {
			double dev1 = RandomUtilities.randFunc(dev);
			double dev2 = RandomUtilities.randFunc(dev);
			double dev3 = RandomUtilities.randFunc(dev);
			
			Point ab = getMid(a, b, dev1);
			Point bc = getMid(b, c, dev2);
			Point ca = getMid(c, a, dev3);
			fractalLine(turtle, order - 1, a, ab, ca, dev / 2);
			fractalLine(turtle, order - 1, ab, b, bc, dev / 2);
			fractalLine(turtle, order - 1, ca, bc, c, dev / 2);
			fractalLine(turtle, order - 1, ab, ca, bc, dev / 2);	
		}
		turtle.penUp();
	}
}
