package edu.lab.cglab.lab4;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.lab.cglab.lab1.Point;

/**
 * @author joshanashakya <Feb 12, 2020>
 *
 */
public class Commons {

	private Commons() {
	}

	public static void sort(List<Point> points, boolean isLeft) {
		if (isLeft)
			Collections.sort(points, new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					return o2.getY() - o1.getY();
				}
			});
		else {
			Collections.sort(points, new PointYComparator());
		}
	}
}
