package edu.lab.cglab;

import edu.lab.cglab.lab1.Point;

/**
 * @author joshanashakya <Feb 7, 2020>
 *
 */
public class Utilities {

	public static Point[] createPoints(String line) {
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
}
