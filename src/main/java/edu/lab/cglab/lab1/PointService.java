package edu.lab.cglab.lab1;

public class PointService {

	public boolean areCollinear(Point[] points) {
		double area = computeArea(points);
		return area == 0;
	}

	public double computeArea(Point[] points) {
		int p1x = points[0].getX();
		int p1y = points[0].getY();
		int p2x = points[1].getX();
		int p2y = points[1].getY();
		int p3x = points[2].getX();
		int p3y = points[2].getY();
		return (p1x * (p2y - p3y) - p1y * (p2x - p3x) + 1 * (p2x * p3y - p3x * p2y)) / (double) 2;
	}
}
