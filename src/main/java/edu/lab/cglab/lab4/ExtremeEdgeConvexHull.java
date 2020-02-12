package edu.lab.cglab.lab4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.lab.cglab.lab1.LineSegment;
import edu.lab.cglab.lab1.Point;
import edu.lab.cglab.lab1.PointLineClassification;
import edu.lab.cglab.lab1.PointService;
import edu.lab.cglab.lab2.TurnTest;

/**
 * @author joshanashakya <Feb 11, 2020>
 *
 */
public class ExtremeEdgeConvexHull implements ConvexHull {

	@Override
	public List<Point> get(List<Point> points) {
		List<LineSegment> extremes = findExtremes(points);
		System.out.println("Extreme Edges Count: " + extremes.size());
		System.out.println("Extreme Edges:");
		for (LineSegment ls : extremes) {
			System.out.println(ls.toString());
		}
		List<Point> convexHull = sort(extremes);
		return convexHull;

	}

	private List<LineSegment> findExtremes(List<Point> points) {
		Set<LineSegment> extremeEdges = new HashSet<>();
		int n = points.size();
		for (int i = 0; i <= n - 1; i++) {
			for (int j = 0; j <= n - 1; j++) {
				if (i == j)
					continue;
				boolean isExtreme = true;
				Point pi = points.get(i);
				Point pj = points.get(j);
				LineSegment ls = new LineSegment(pi, pj);
				for (int k = 0; k <= n - 1; k++) {
					if (k == i || k == j)
						continue;
					Point pk = points.get(k);
					if (isNotExtreme(pi, pj, pk)) {
						isExtreme = false;
						break;
					}
				}
				if (isExtreme)
					extremeEdges.add(ls);
			}
		}
		return new ArrayList<>(extremeEdges);

	}

	private List<Point> sort(List<LineSegment> lineSegments) {
		// collect points from line segments
		Set<Point> points = new HashSet<>();
		for (LineSegment ls : lineSegments) {
			points.add(ls.getP1());
			points.add(ls.getP2());
		}

		// find points with maximum y-coordinate and minimum y-coordinate
		Comparator<Point> cmp = new PointYComparator();
		Point max = Collections.max(points, cmp);
		Point min = Collections.min(points, cmp);

		// find points to the left and right of these points based on x-coordinate value
		List<Point> lefts = new ArrayList<>();
		List<Point> rights = new ArrayList<>();
		for (Point point : points) {
			if (isLeft(max, min, point))
				lefts.add(point);
			else
				rights.add(point);
		}
		// sort points to the left in ascending y-coordinate
		Commons.sort(lefts, true);
		Commons.sort(rights, false);

		// merge sorted right and left points
		List<Point> sorted = new ArrayList<>();
		sorted.addAll(rights);
		sorted.addAll(lefts);
		return sorted;
	}

	private boolean isLeft(Point max, Point min, Point point) {
		int x = point.getX();
		return x < max.getX() && x < min.getX();
	}

	private boolean isNotExtreme(Point pi, Point pj, Point pk) {
		TurnTest turnTest = new TurnTest();
		Point[] points = new Point[] { pi, pj, pk };
		if (isOn(pk, pi, pj))
			return false;
		return !turnTest.isLeftTurn(points);
	}

	private boolean isOn(Point pk, Point pi, Point pj) {
		PointLineClassification plc = new PointLineClassification(pi, pj);
		PointService ps = new PointService();
		return plc.isBetween(pk) && ps.areCollinear(new Point[] { pi, pj, pk });
	}
}
