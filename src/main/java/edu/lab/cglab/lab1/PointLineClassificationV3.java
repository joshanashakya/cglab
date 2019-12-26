package edu.lab.cglab.lab1;

import edu.lab.cglab.lab2.TurnTest;

public class PointLineClassificationV3 {
	private Point start;
	private Point terminal;
	private int sx;
	private int sy;
	private int tx;
	private int ty;

	public PointLineClassificationV3(Point start, Point terminal) {
		this.start = start;
		this.terminal = terminal;
		this.sx = this.start.getX();
		this.sy = this.start.getY();
		this.tx = this.terminal.getX();
		this.ty = this.terminal.getY();
	}

	public String getLoc(Point point) {
		PointService pointService = new PointService();
		Point[] points = new Point[] { start, terminal, point };
		boolean flag = pointService.areCollinear(points);
		if (!flag) {
			TurnTest turnTest = new TurnTest();
			String turn = turnTest.getTurn(points);
			return String.format("%s about point %s", turn, terminal);
		}
		if (isBehind(point))
			return "behind";
		if (isBeyond(point))
			return "beyond";
		if (isBetween(point))
			return "between";
		return "invalid";
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
