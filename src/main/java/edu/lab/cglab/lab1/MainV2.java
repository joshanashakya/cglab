package edu.lab.cglab.lab1;

import edu.lab.cglab.FileReaderWriter;

public class MainV2 {

	static final String FILE_NAME = "lab1.txt";

	public static void main(String[] args) {
		String str = FileReaderWriter.read(FILE_NAME);
		String[] lines = str.split("\n");
		int noOfTestCases = Integer.valueOf(lines[0]);
		int idx = 1;
		for (int i = 0; i < noOfTestCases; i++) {
			String[] lineIP = lines[idx].split(" ");
			Point pt1 = new Point(Integer.valueOf(lineIP[0]), Integer.valueOf(lineIP[1]));
			Point pt2 = new Point(Integer.valueOf(lineIP[2]), Integer.valueOf(lineIP[3]));
			Point start = pt1.aheadOf(pt2) ? pt1 : pt2;
			Point terminal = !pt1.aheadOf(pt2) ? pt1 : pt2;
			String[] pointIP = lines[idx + 1].split(" ");
			Point pt = new Point(Integer.valueOf(pointIP[0]), Integer.valueOf(pointIP[1]));
			PointLineClassificationV3 plc = new PointLineClassificationV3(start, terminal);
			String loc = plc.getLoc(pt);
			System.out.format("The line segment is %s.\n", new LineSegment(start, terminal).toString());
			System.out.format("The point %s is %s.\n\n", pt.toString(), loc);
			idx = idx + 2;
		}
	}
}
