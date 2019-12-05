package edu.lab.cglab.lab3;

import java.util.List;

import edu.lab.cglab.lab1.LineSegment;
import edu.lab.cglab.lab1.LineSegmentService;
import edu.lab.cglab.lab1.Point;
import edu.lab.cglab.lab3.Polygon.Node;

public class RayCasting {

	private static final Point END = new Point(1000, 1000);

	public boolean isIncluded(Polygon polygon, Point point) {
		LineSegment ray = createRay(point);
		int noOfIntersections = count(polygon, ray);
		return (noOfIntersections % 2 != 0);
	}

	private int count(Polygon polygon, LineSegment ray) {
		List<Node> nodes = polygon.getNodes();
		LineSegmentService segService = new LineSegmentService();
		int count = 0;
		for (Node node : nodes) {
			LineSegment segment = new LineSegment(node.getVertex(), node.getNext().getVertex());
			boolean doesIntersect = segService.doesIntersect(new LineSegment[] { segment, ray });
			count = doesIntersect ? count + 1 : count;
		}
		return count;
	}

	private LineSegment createRay(Point point) {
		return new LineSegment(point, END);
	}
}
