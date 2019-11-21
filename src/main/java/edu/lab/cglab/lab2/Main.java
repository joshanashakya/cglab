package edu.lab.cglab.lab2;

import java.util.Scanner;

import edu.lab.cglab.lab1.LineSegment;
import edu.lab.cglab.lab1.LineSegmentService;
import edu.lab.cglab.lab1.Point;
import edu.lab.cglab.lab1.PointService;

public class Main {

	private Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Main main = new Main();
		Point[] points = main.readPoints(3);
		TurnTest turnTest = new TurnTest();
		String turn = turnTest.getTurn(points);
		System.out.println(String.format("The points %s, %s and %s are %s turn.", points[0].toString(),
				points[1].toString(), points[2].toString(), turn));
		
		PointService pointService = new PointService();
		double area = pointService.computeArea(points);
		System.out.println(String.format("The area of triangle is %f.", area));
		
		boolean collinear = pointService.areCollinear(points);
		System.out.println(String.format("The points %s, %s and %s are %s.", points[0].toString(),
				points[1].toString(), points[2].toString(), collinear ? "collinear" : "are not collinear"));

		System.out.println();
		LineSegment[] lineSegments = main.readLineSegments(2);
		LineSegmentService lineSegmentService = new LineSegmentService();
		boolean intersect = lineSegmentService.doesIntersect(lineSegments);
		System.out.println(String.format("The line segments %s, %s %s.", lineSegments[0].toString(),
				lineSegments[1].toString(), (intersect ? "intersect" : "do not intersect")));
	}

	private Point[] readPoints(int num) {
		System.out.println(String.format("Enter %d points: ", num));
		Point[] points = new Point[num];
		for (int i = 0; i < num; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			points[i] = new Point(x, y);
		}
		return points;
	}

	private LineSegment[] readLineSegments(int num) {
		System.out.println(String.format("Enter points of %d line segments:", num));
		LineSegment[] lineSegments = new LineSegment[num];
		for (int i = 0; i < num; i++) {
			Point p1 = new Point(sc.nextInt(), sc.nextInt());
			Point p2 = new Point(sc.nextInt(), sc.nextInt());
			lineSegments[i] = new LineSegment(p1, p2);
		}
		return lineSegments;
	}
}
