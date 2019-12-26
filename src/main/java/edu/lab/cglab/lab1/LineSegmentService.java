package edu.lab.cglab.lab1;

import edu.lab.cglab.lab2.TurnTest;

public class LineSegmentService {

	public boolean doesIntersect(LineSegment[] lineSegments) {
		boolean flag = doesIntersectUsingTurnTest(lineSegments);
		if (flag)
			return flag;
		flag = hasImproperIntersection(lineSegments);
		return flag;
	}

	public boolean doesIntersectUsingTurnTest(LineSegment[] lineSegments) {
		Point p11 = lineSegments[0].getP1();
		Point p12 = lineSegments[0].getP2();
		Point p21 = lineSegments[1].getP1();
		Point p22 = lineSegments[1].getP2();

		TurnTest turnTest = new TurnTest();
		String turnLine11 = turnTest.getTurn(new Point[] { p11, p12, p21 });
		String turnLine12 = turnTest.getTurn(new Point[] { p11, p12, p22 });

		String turnLine21 = turnTest.getTurn(new Point[] { p21, p22, p11 });
		String turnLine22 = turnTest.getTurn(new Point[] { p21, p22, p12 });

		return !turnLine11.equalsIgnoreCase(turnLine12) && !turnLine21.equalsIgnoreCase(turnLine22);
	}

	public boolean hasImproperIntersection(LineSegment[] lineSegments) {
		Point p11 = lineSegments[0].getP1();
		Point p12 = lineSegments[0].getP2();
		Point p21 = lineSegments[1].getP1();
		Point p22 = lineSegments[1].getP2();
		// check if the points in line are collinear and between line
		PointService pointService = new PointService();
		boolean line1point1 = pointService.areCollinear(new Point[] { p11, p12, p21 })
				&& (new PointLineClassification(p11, p12).isBetween(p21) || p11.equals(p21) || p12.equals(p21));
		boolean line1point2 = pointService.areCollinear(new Point[] { p11, p12, p22 })
				&& (new PointLineClassification(p11, p12).isBetween(p22) || p11.aheadOf(p22) || p12.equals(p22));
		boolean line2point1 = pointService.areCollinear(new Point[] { p21, p22, p11 })
				&& (new PointLineClassification(p21, p22).isBetween(p11) || p21.equals(p11) || p22.equals(p11));
		boolean line2point2 = pointService.areCollinear(new Point[] { p21, p22, p12 })
				&& (new PointLineClassification(p21, p22).isBetween(p12) || p21.equals(p12) || p22.equals(p12));
		return line1point1 || line1point2 || line2point1 || line2point2;
	}
}
