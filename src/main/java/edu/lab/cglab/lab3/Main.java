package edu.lab.cglab.lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.lab.cglab.lab1.Point;

public class Main {

	private Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Main main = new Main();

		// create polygon
		List<Point> points = main.readVertices();
		PolygonService polygonService = new PolygonService();
		Polygon polygon = polygonService.create(points);

		// check whether the polygon is convex
		boolean isConvex = polygonService.isConvex(polygon);
		System.out.format("The polygon is %s.\n", isConvex ? "convex" : "not convex");

		// check point inclusion
		Point point = main.readPoint();
		// turn test
		boolean isIncluded = polygonService.isIncluded(polygon, point);
		System.out.println("Turn Test Method");
		System.out.format("The point is %s polygon.\n", isIncluded ? "inside" : "outside");
		// ray casting
		isIncluded = new RayCasting().isIncluded(polygon, point);
		System.out.println("Ray Casting Method");
		System.out.format("The point is %s polygon.\n", isIncluded ? "inside" : "outside");
	}

	private List<Point> readVertices() {
		System.out.println("Enter number of vertices of polygon: ");
		int size = sc.nextInt();
		System.out.format("Enter %d vertices of polygon:\n", size);
		List<Point> points = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			points.add(new Point(sc.nextInt(), sc.nextInt()));
		}
		return points;
	}

	private Point readPoint() {
		System.out.println("Enter the point to check inclusion: ");
		return new Point(sc.nextInt(), sc.nextInt());
	}
}

//2 2 5 4 4 7 2 7 1 5
//0 2
