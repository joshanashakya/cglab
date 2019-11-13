package edu.lab.cglab.lab1;

public class LineSegment {
	private Point p1;
	private Point p2;

	public LineSegment(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public Point getP1() {
		return p1;
	}

	public Point getP2() {
		return p2;
	}
	
	@Override
	public String toString() {
		return String.format("(%d, %d) and (%d, %d)", p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}
}
