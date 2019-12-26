package edu.lab.cglab.lab2;

import java.util.Scanner;

import edu.lab.cglab.FileReaderWriter;
import edu.lab.cglab.lab1.LineSegment;
import edu.lab.cglab.lab1.LineSegmentService;
import edu.lab.cglab.lab1.Point;
import edu.lab.cglab.lab1.PointService;

public class Main {

	private static final String FILE_NAME = "lab2.txt";
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Main main = new Main();
		System.out.println("Options");
		System.out.println("1. Turn Test");
		System.out.println("2. Find Area of Triangle");
		System.out.println("3. Collinearity Test");
		System.out.println("4. Line Segment Intersection Test");
		System.out.println("Enter your choice: ");
		int ch = sc.nextInt();
		String strData = main.getTestData(String.format("%d.", ch));
		String[] lines = strData.split("\n");
		int noOfTestCases = Integer.valueOf(lines[0]);
		switch (ch) {
		case 1:
			main.turnTest(lines, noOfTestCases);
			break;
		case 2:
			main.computeArea(lines, noOfTestCases);
			break;
		case 3:
			main.collinearityTest(lines, noOfTestCases);
			break;
		case 4:
			main.checkIntersection(lines, noOfTestCases);
			break;
		default:
			main.mainTest();
			break;
		}
	}

	private void turnTest(String[] lines, int noOfTestCases) {
		TurnTest turnTest = new TurnTest();
		for (int i = 0; i < noOfTestCases; i++) {
			Point[] points = parsePoints(lines[i + 1]);
			String turn = turnTest.getTurn(points);
			System.out.println(String.format("The points %s, %s and %s are %s turn about point %s.",
					points[0].toString(), points[1].toString(), points[2].toString(), turn, points[1].toString()));
		}
	}

	private void computeArea(String[] lines, int noOfTestCases) {
		PointService pointService = new PointService();
		for (int i = 0; i < noOfTestCases; i++) {
			Point[] points = parsePoints(lines[i + 1]);
			double area = pointService.computeArea(points);
			System.out.println(String.format("The area of triangle constructed from points %s, %s and %s is %.2f.",
					points[0].toString(), points[1].toString(), points[2].toString(), area));
		}
	}

	private void collinearityTest(String[] lines, int noOfTestCases) {
		PointService pointService = new PointService();
		for (int i = 0; i < noOfTestCases; i++) {
			Point[] points = parsePoints(lines[i + 1]);
			boolean collinear = pointService.areCollinear(points);
			System.out.println(String.format("The points %s, %s and %s are %s.", points[0].toString(),
					points[1].toString(), points[2].toString(), collinear ? "collinear" : "are not collinear"));
		}
	}

	private void checkIntersection(String[] lines, int noOfTestCases) {
		LineSegmentService lineSegmentService = new LineSegmentService();
		for (int i = 0; i < noOfTestCases; i++) {
			LineSegment[] ls = parseLineSegments(lines[i + 1]);
			boolean pIntersect = lineSegmentService.doesIntersectUsingTurnTest(ls);
			String output = "The line segments %s, %s has %s.";
			if (pIntersect) {
				System.out.println(String.format(output, ls[0].toString(), ls[1].toString(), "proper intersection"));
			} else {
				boolean imIntersect = lineSegmentService.hasImproperIntersection(ls);
				if (imIntersect) {
					System.out.println(
							String.format(output, ls[0].toString(), ls[1].toString(), "improper intersection"));
				} else {
					System.out.println(String.format(output, ls[0].toString(), ls[1].toString(), "no intersection"));
				}
			}
		}
	}

	public void mainTest() {
		Point[] points = readPoints(3);
		TurnTest turnTest = new TurnTest();
		String turn = turnTest.getTurn(points);
		System.out.println(String.format("The points %s, %s and %s are %s turn.", points[0].toString(),
				points[1].toString(), points[2].toString(), turn));

		PointService pointService = new PointService();
		double area = pointService.computeArea(points);
		System.out.println(String.format("The area of triangle is %f.", area));

		boolean collinear = pointService.areCollinear(points);
		System.out.println(String.format("The points %s, %s and %s are %s.", points[0].toString(), points[1].toString(),
				points[2].toString(), collinear ? "collinear" : "are not collinear"));

		System.out.println();
		LineSegment[] lineSegments = readLineSegments(2);
		LineSegmentService lineSegmentService = new LineSegmentService();
		boolean intersect = lineSegmentService.doesIntersect(lineSegments);
		System.out.println(String.format("The line segments %s, %s %s.", lineSegments[0].toString(),
				lineSegments[1].toString(), (intersect ? "intersect" : "do not intersect")));
	}

	public String getTestData(String value) {
		String str = FileReaderWriter.read(FILE_NAME);
		String[] lines = str.split("\n");
		int startIdx = 0;
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].startsWith(value)) {
				startIdx = i + 1;
				break;
			}
		}
		int endIdx = startIdx + Integer.valueOf(lines[startIdx]) + 1;
		StringBuilder sb = new StringBuilder();
		for (int i = startIdx; i < endIdx; i++) {
			sb.append(lines[i]);
			sb.append("\n");
		}
		return sb.toString();
	}

	private Point[] parsePoints(String str) {
		String[] values = str.split(" ");
		int size = values.length;
		Point[] points = new Point[size / 2];
		int idx = 0;
		for (int i = 0; i < size; i = i + 2) {
			int x = Integer.valueOf(values[i]);
			int y = Integer.valueOf(values[i + 1]);
			points[idx] = new Point(x, y);
			idx++;
		}
		return points;
	}

	private LineSegment[] parseLineSegments(String str) {
		String[] values = str.split(" ");
		int size = values.length;
		LineSegment[] lineSegments = new LineSegment[size / 4];
		int idx = 0;
		for (int i = 0; i < size; i = i + 4) {
			Point p1 = new Point(Integer.valueOf(values[i]), Integer.valueOf(values[i + 1]));
			Point p2 = new Point(Integer.valueOf(values[i + 2]), Integer.valueOf(values[i + 3]));
			lineSegments[idx] = new LineSegment(p1, p2);
			idx++;
		}
		return lineSegments;
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
