package edu.lab.cglab.lab4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.lab.cglab.FileReaderWriter;
import edu.lab.cglab.Utilities;
import edu.lab.cglab.lab1.Point;

/**
 * @author joshanashakya <Feb 6, 2020>
 *
 */
public class Main {

	private static final String FILE_NAME = "lab4.txt";

	public static void main(String[] args) {
		String str = FileReaderWriter.read(FILE_NAME);
		String[] lines = str.split("\n");
		int noOfTestCases = Integer.valueOf(lines[0]);
		int idx = 1;
		ConvexHull ePCh = new ExtremePointConvexHull();
		ConvexHull eECh = new ExtremeEdgeConvexHull();
		ConvexHull giftWrap = new GiftWrapAlgo();
		ConvexHull grahamsScan = new GrahamsScan();
		for (int i = 0; i < noOfTestCases; i++) {
			Point[] pointsArr = Utilities.createPoints(lines[idx]);
			List<Point> points = new ArrayList<>(Arrays.asList(pointsArr));

			System.out.println("EXTREME POINTS CONVEX HULL\n==========================");
			List<Point> convexHullPoints1 = ePCh.get(points);
			print(convexHullPoints1);

			System.out.println("\n\nEXTREME EDGES CONVEX HULL\n==========================");
			List<Point> convexHullPoints2 = eECh.get(points);
			print(convexHullPoints2);

			System.out.println("\n\nGIFT WRAPPING ALGORITHM\n==========================");
			List<Point> convexHullPoints3 = giftWrap.get(points);
			print(convexHullPoints3);

			System.out.println("\n\nGRAHAM'S SCAN ALGORITHM\n==========================");
			List<Point> convexHullPoints4 = grahamsScan.get(points);
			print(convexHullPoints4);

			System.out.println();
			idx++;
		}
	}

	private static void print(List<Point> convexHullPoints) {
		System.out.println("Convex Hull Points:" + Arrays.toString(convexHullPoints.toArray()));
	}
}
