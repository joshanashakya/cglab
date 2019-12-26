package edu.lab.cglab.lab1;

public class PointLineClassificationV2 {
	private Point start;
	private Point terminal;
	private int sx;
	private int sy;
	private int tx;
	private int ty;
	
	public PointLineClassificationV2(Point start, Point terminal) {
		this.start = start;
		this.terminal = terminal;
		this.sx = this.start.getX();
		this.sy = this.start.getY();
		this.tx = this.terminal.getX();
		this.ty = this.terminal.getY();
	}

	public boolean isBehind (Point p) {
		int x = p.getX();
		int y = p.getY();
//		s
//		|
//		|
//		|
//		t
		if (sx == tx)
			return y < sy && x == sx;
		
//		s ---------- t
		if (sy == ty)
			return x < sx && y == sy;
		
//	s	.
//		 .
//		  .
//		   .
//	t	    .
		if (sx < tx && sy > ty)
			return x < sx && y > sy;

//		t	.
//			 .
//			  .
//			   .
//		s	    .
		if (sx > tx && sy < ty)
			return x > sx && y < sy;
		
//		t        .
//		       .
//		      .
//		     .
//		s   .
		if (sx < tx && sy < ty)
			return x < sx && y < sy;
		
		return false;
	}
}
