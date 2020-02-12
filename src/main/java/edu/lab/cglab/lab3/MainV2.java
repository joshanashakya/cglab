package edu.lab.cglab.lab3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.lab.cglab.FileReaderWriter;
import edu.lab.cglab.lab1.Point;

public class MainV2 {

	private static final String FILE_NAME = "lab3.txt";

	public static void main(String[] args) {
		MainV2 main = new MainV2();
		String str = FileReaderWriter.read(FILE_NAME);
		String[] lines = str.split("\n");
		int noOfTestCases = Integer.valueOf(lines[0]);
		int idx = 1;
		PolygonService polygonService = new PolygonService();
		for (int i = 0; i < noOfTestCases; i++) {
			Point[] points = main.createPoints(lines[idx]);
			List<Point> sortedPoints = main.sort(points);
			// create polygon
			Polygon polygon = polygonService.create(sortedPoints);

			// check whether the polygon is convex
			boolean isConvex = polygonService.isConvex(polygon);
			System.out.format("The polygon is %s.\n", isConvex ? "convex" : "not convex");

			// check point inclusion
			Point[] checkPoints = main.createPoints(lines[idx + 1]);
			for (int j = 0; j < checkPoints.length; j++) {
				Point point = checkPoints[j];
				// turn test
				boolean isIncluded = polygonService.isIncluded(polygon, point);
				System.out.format("Turn Test Method:The point is %s polygon.\n", isIncluded ? "inside" : "outside");
				// ray casting
				isIncluded = new RayCasting().isIncluded(polygon, point);
				System.out.format("Ray Casting Method:The point is %s polygon.\n\n", isIncluded ? "inside" : "outside");
			}
			idx = idx + 2;
		}
	}

	private Point[] createPoints(String line) {
		String[] values = line.split(" ");
		int count = Integer.valueOf(values[0]);
		Point[] points = new Point[count];
		int ptIdx = 1;
		for (int i = 0; i < count; i++) {
			points[i] = new Point(Integer.valueOf(values[ptIdx]), Integer.valueOf(values[ptIdx + 1]));
			ptIdx = ptIdx + 2;
		}
		return points;
	}

	private List<Point> sort(Point[] points) {
		List<Point> ySortedPoints = sortOnY(points);
		List<Point> sortedPoints = new ArrayList<>();
		Point current = ySortedPoints.get(0);
		sortedPoints.add(current);
		ySortedPoints.remove(current);

		while (!ySortedPoints.isEmpty()) {
			Point selected = ySortedPoints.get(0);
			double slope = calSlope(current, selected);
			int xdist = getXdist(current, selected);
			for (int i = 1; i < ySortedPoints.size(); i++) {
				Point point = ySortedPoints.get(i);
				double tmpSlope = calSlope(current, point);
				int tmpXdist = getXdist(current, point);
				// for +ve slope and +ve xdist
				if ((slope > 0 && tmpSlope > 0) && (xdist > 0 && tmpXdist > 0)) {
					if (tmpSlope < slope && tmpXdist < xdist) {
						selected = point;
					}
				} else if ((slope >= 0  && tmpSlope >= 0) && (xdist > 0 || tmpXdist > 0)) {
					if (tmpSlope < slope && tmpXdist < xdist) {
						selected = point;
					}
				}
			}
			sortedPoints.add(selected);
			current = selected;
			ySortedPoints.remove(selected);
		}
		System.out.println(Arrays.toString(sortedPoints.toArray()));
		return sortedPoints;
	}

	private int getXdist(Point point1, Point point2) {
		return point2.getX() - point1.getX();
	}

	private double calSlope(Point point1, Point point2) {
		if ((point2.getX() - point1.getX()) == 0)
			return 0;
		return (point2.getY() - point1.getY()) / (double) (point2.getX() - point1.getX());
	}

	private List<Point> sortOnY(Point[] points) {
		List<Point> list = new ArrayList<>(Arrays.asList(points));
		Collections.sort(list, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return o1.getY() - o2.getY();
			}
		});
		return list;
	}
}
