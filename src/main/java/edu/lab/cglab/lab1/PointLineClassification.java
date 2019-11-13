package edu.lab.cglab.lab1;

public class PointLineClassification {
	private Point start;
	private Point terminal;
	private int sx;
	private int sy;
	private int tx;
	private int ty;

	public PointLineClassification(Point start, Point terminal) {
		this.start = start;
		this.terminal = terminal;
		this.sx = this.start.getX();
		this.sy = this.start.getY();
		this.tx = this.terminal.getX();
		this.ty = this.terminal.getY();
	}

	public boolean isBehind(Point p) {
		int x = p.getX();
		int y = p.getY();

		if (sx == tx)
			return y < sy && x == sx;

		if (sy == ty)
			return x < sx && y == sy;

		if (sx < tx && sy > ty)
			return x < sx && y > sy;

		if (sx > tx && sy < ty)
			return x > sx && y < sy;

		if (sx < tx && sy < ty)
			return x < sx && y < sy;

		return false;
	}

	public boolean isBeyond(Point p) {
		int x = p.getX();
		int y = p.getY();

		if (sx == tx)
			return ty < y && x == tx;

		if (sy == ty)
			return tx < x && y == ty;

		if (sx < tx && sy > ty)
			return tx < x && ty > y;

		if (sx > tx && sy < ty)
			return tx > x && ty < y;

		if (sx < tx && sy < ty)
			return tx < x && ty < y;

		return false;
	}

	public boolean isBetween(Point p) {
		int x = p.getX();
		int y = p.getY();

		if (sx == tx)
			return sy < y && y < ty;

		if (sy == ty)
			return sx < x && x < tx;

		if (sx < tx && sy > ty)
			return (sx < x && x < tx) && (sy > y && y > ty);

		if (sx > tx && sy < ty)
			return (sx > x && x > tx) && (sy < y && y < ty);

		if (sx < tx && sy < ty)
			return (sx < x && x < tx) && (sy < y && y < ty);

		return false;
	}
}
