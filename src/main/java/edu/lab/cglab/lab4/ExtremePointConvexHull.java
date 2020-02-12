package edu.lab.cglab.lab4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.lab.cglab.lab1.Point;
import edu.lab.cglab.lab3.Polygon;
import edu.lab.cglab.lab3.PolygonService;

/**
 * @author joshanashakya <Feb 7, 2020>
 *
 */
public class ExtremePointConvexHull implements ConvexHull {

	@Override
	public List<Point> get(List<Point> points) {
		List<Point> nonExtremes = findNonExtremes(points);
		System.out.println("Non-Extreme Points Count: " + nonExtremes.size());
		System.out.println("Points:" + Arrays.toString(nonExtremes.toArray()));
		List<Point> extremes = removeNonExtremes(points, nonExtremes);
		System.out.println("Extreme Points Count: " + extremes.size());
		System.out.println("Points:" + Arrays.toString(extremes.toArray()));
		DoublePoint centroid = calCentroid(extremes);
		System.out.println("\nCentroid" + centroid.toString());
		List<Point> sortedExtremes = sort(extremes, centroid);
		return sortedExtremes;
	}

	private List<Point> findNonExtremes(List<Point> points) {
		int n = points.size();
		PolygonService ps = new PolygonService();
		Set<Point> nonExtremes = new HashSet<>();
		for (int i = 0; i <= n - 1; i++) {
			for (int j = 0; j <= n - 2; j++) {
				if (i == j)
					continue;
				for (int k = 0; k <= n - 3; k++) {
					if (k == i || k == j)
						continue;
					for (int l = 0; l <= n - 1; l++) {
						if (l == i || l == j || l == k)
							continue;
						Polygon triangle = ps
								.create(Arrays.asList(new Point[] { points.get(i), points.get(j), points.get(k) }));
						Point point = points.get(l);
						if (ps.isIncluded(triangle, point))
							nonExtremes.add(point);
					}
				}
			}
		}
		return new ArrayList<>(nonExtremes);
	}

	private List<Point> removeNonExtremes(List<Point> points, List<Point> nonExtremes) {
		points.removeAll(nonExtremes);
		return points;
	}

	private DoublePoint calCentroid(List<Point> extremes) {
		Point pt1 = extremes.get(0);
		Point pt2 = extremes.get(1);
		Point pt3 = extremes.get(2);
		double x = (double) (pt1.getX() + pt2.getX() + pt3.getX()) / 3;
		double y = (double) (pt2.getY() + pt3.getY() + pt3.getY()) / 3;
		return new DoublePoint(x, y);
	}

	private List<Point> sort(List<Point> points, DoublePoint centroid) {
		// separate points lying to the left and right of the centroid
		List<Point> rights = new ArrayList<>();
		List<Point> lefts = new ArrayList<>();
		for (Point point : points) {
			if (isLeft(centroid, point))
				lefts.add(point);
			else
				rights.add(point);
		}

		// sort points left to centroid in ascending order with respect to y
		Commons.sort(rights, false);
		// sort points left to centroid in descending order with respect to y
		Commons.sort(lefts, true);

		// merge sorted right and left points
		List<Point> sorted = new ArrayList<>();
		sorted.addAll(rights);
		sorted.addAll(lefts);
		return sorted;
	}

	private boolean isLeft(DoublePoint centroid, Point point) {
		return centroid.getX() > point.getX();
	}
}
