package edu.lab.cglab.lab1;

import edu.lab.cglab.lab2.TurnTest;

public class LineSegmentService {

	public boolean doesIntersect(LineSegment[] lineSegments) {
		Point p11 = lineSegments[0].getP1();
		Point p12 = lineSegments[0].getP2();
		Point p21 = lineSegments[1].getP1();
		Point p22 = lineSegments[1].getP2();

		TurnTest turnTest = new TurnTest();
		String turnLine11 = turnTest.getTurn(new Point[] { p11, p12, p21 });
		String turnLine12 = turnTest.getTurn(new Point[] { p11, p12, p22 });

		String turnLine21 = turnTest.getTurn(new Point[] { p21, p22, p11 });
		String turnLine22 = turnTest.getTurn(new Point[] { p21, p22, p22 });

		return (!turnLine11.equalsIgnoreCase(turnLine12) && !turnLine21.equalsIgnoreCase(turnLine22));
	}
}
