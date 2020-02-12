package edu.lab.cglab.lab1;

public class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean aheadOf(Point obj) {
		return this.x < obj.x || this.y < obj.y;
	}

	@Override
	public String toString() {
		return String.format("(%d, %d)", this.getX(), this.getY());
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + x;
		result = 31 * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Point))
			return false;
		if (obj == this)
			return true;
		Point pt = (Point) obj;
		return this.x == pt.getX() && this.y == pt.getY();
	}
}
