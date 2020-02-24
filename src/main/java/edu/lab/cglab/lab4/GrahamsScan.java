package edu.lab.cglab.lab4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.lab.cglab.lab1.Point;
import edu.lab.cglab.lab2.TurnTest;

public class GrahamsScan implements ConvexHull {

	@Override
	public List<Point> get(List<Point> points) {
		Point min = Collections.min(points, new PointYComparator());
		List<Point> sortedPoints = sort(min, points);
		List<Point> stack = new ArrayList<>();
		stack.add(sortedPoints.get(0));
		stack.add(sortedPoints.get(1));
		TurnTest turnTest = new TurnTest();
		for (int i = 2; i < sortedPoints.size();) {
			Point qi = sortedPoints.get(i);
			int topIdx = stack.size() - 1;
			if (turnTest.isLeftTurn(new Point[] { stack.get(topIdx - 1), stack.get(topIdx), qi })) {
				stack.add(qi);
				i++;
			} else {
				stack.remove(topIdx);
			}
		}
		return stack;
	}

	private List<Point> sort(Point min, List<Point> points) {
		Collections.sort(points, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return (int) (calAng(min, o1) - calAng(min, o2));
			}
		});
		return points;
	}

	private double calAng(Point p1, Point p2) {
		double x = p2.getX() - p1.getX();
		double y = p2.getY() - p1.getY();
		double radian = Math.atan2(y, x);
		return Math.toDegrees(radian);
	}
}
