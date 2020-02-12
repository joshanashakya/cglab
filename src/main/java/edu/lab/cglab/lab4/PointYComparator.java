package edu.lab.cglab.lab4;

import java.util.Comparator;

import edu.lab.cglab.lab1.Point;

/**
 * @author joshanashakya <Feb 12, 2020>
 *
 */
public class PointYComparator implements Comparator<Point> {

	@Override
	public int compare(Point o1, Point o2) {
		return o1.getY() - o2.getY();
	}
}
