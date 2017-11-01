package mountain;

public class Side {
	private Point p1;
	private Point p2;
	private Point m;

	public Side(Point p1, Point p2, double dev) {
		this.p1 = p1;
		this.p2 = p2;
		this.m = new Point((p1.getX() + p2.getX()) / 2, ((p1.getY() + p2.getY()) / 2) + dev);
	}

	public Point getP1() {
		return this.p1;
	}

	public Point getP2() {
		return this.p2;
	}

	public Point getM() {
		return this.m;
	}
}
