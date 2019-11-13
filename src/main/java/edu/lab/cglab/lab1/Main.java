package edu.lab.cglab.lab1;

public class Main {
	public static void main(String[] args) {
		Point s1 = new Point(3, 2);
		Point t1 = new Point(8, 2);
		LineSegment segment = new LineSegment(s1, t1);

		PointLineClassification plc = new PointLineClassification(s1, t1);
		Point testPoint = new Point(10, 2);
		String loc;
		if (plc.isBehind(testPoint)) {
			loc = "behind";
		} else if (plc.isBeyond(testPoint)) {
			loc = "beyond";
		} else if (plc.isBetween(testPoint)) {
			loc = "between";
		} else {
			loc = "not in";
		}
		System.out.println(String.format("The point %s is %s the line segment %s.", testPoint.toString(), loc, segment.toString()));
	}
}

//Point s2 = new Point(2, 1);
//Point t2 = new Point(2, 6);
//
//Point s3 = new Point(4, 7);
//Point t3 = new Point(9, 3);
//
//Point s4 = new Point(9, 3);
//Point t4 = new Point(4, 7);
//
//Point s5 = new Point(12, 3);
//Point t5 = new Point(15, 11);