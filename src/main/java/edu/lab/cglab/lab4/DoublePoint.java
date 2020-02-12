package edu.lab.cglab.lab4;

/**
 * @author joshanashakya <Feb 7, 2020>
 *
 */
public class DoublePoint {
	private double x;
	private double y;

	public DoublePoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public String toString() {
		return String.format("(%f, %f)", this.getX(), this.getY());
	}
}
