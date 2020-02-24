package edu.lab.cglab.lab4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.lab.cglab.lab1.Point;

public class GiftWrapAlgo implements ConvexHull {

	@Override
	public List<Point> get(List<Point> points) {
		// find point with the minimum y-coordinate and maximum y-coordinate
		Point min = Collections.min(points, new PointYComparator());
		Point max = Collections.max(points, new PointYComparator());
		List<Point> convexHull = new ArrayList<>();
		Point current = min;
		convexHull.add(current);
		while (true) {
			// check if the current point lies on the right side
			boolean isOnRight = isOnRight(current, min, max);
			// if point lies on the right side, choose points above current points else
			// choose points below current points
			List<Point> toChoosePoints = filter(current, points, isOnRight);
			// selected point with mininum angle
			Point selected = find(current, toChoosePoints);
			// if minimum point is reached break
			if (min.equals(selected))
				break;
			convexHull.add(selected);
			current = selected;
		}
		return convexHull;
	}

	private boolean isOnRight(Point current, Point min, Point max) {
		if (current.equals(min))
			return true;
		if (current.equals(max) || current.getY() == max.getY())
			return false;
		return min.getX() < current.getX() && max.getX() < current.getX();
	}

	private List<Point> filter(Point current, List<Point> points, boolean isOnRight) {
		List<Point> filtered = new ArrayList<>();
		if (isOnRight)
			for (Point point : points) {
				if (point.getY() > current.getY() || (point.getX() > current.getX() && point.getY() == current.getY()))
					filtered.add(point);
			}
		else
			for (Point point : points) {
				if (point.getY() < current.getY()
						|| (point.getX() < current.getX() && point.getY() == current.getY())) {
					filtered.add(point);
				}
			}
		return filtered;
	}

	private Point find(Point current, List<Point> points) {
		Point selected = null;
		double angle = Double.MAX_VALUE;
		for (int i = 0; i < points.size(); i++) {
			if (selected == null) {
				selected = points.get(i);
				angle = calAng(current, selected);
			} else {
				Point tmpPoint = points.get(i);
				double tmpAngle = calAng(current, tmpPoint);
				if (tmpAngle < angle) {
					selected = tmpPoint;
					angle = tmpAngle;
				}
			}
		}
		return selected;
	}

	private double calAng(Point p1, Point p2) {
		double slope = (p2.getY() - p1.getY()) / (double) (p2.getX() - p1.getX());
		double radAng = Math.atan(slope);
		double angle = Math.toDegrees(radAng);
		// the calculations used in tan2 function will return a value anywhere between
		// -180 to +180. Therefore, to obtain an angle between 0-360 we need to correct
		// the results that are less than 0. This is covered with the if statement which
		// checks for answers less than 0, and adds 360 to them.
		if (angle < 0)
			return angle + 360;
		return angle;
	}
}
