package edu.lab.cglab.lab3;

import java.util.ArrayList;
import java.util.List;

import edu.lab.cglab.lab1.Point;
import edu.lab.cglab.lab2.TurnTest;
import edu.lab.cglab.lab3.Polygon.Node;

public class PolygonService {

	public Polygon create(List<Point> points) {
		List<Node> nodes = createNodes(points);
		// set previous and next
		List<Node> updatedNodes = setPrevNext(nodes);
		return new Polygon(updatedNodes);
	}

	public boolean isConvex(Polygon polygon) {
		boolean isConvex = true;
		List<Node> nodes = polygon.getNodes();
		TurnTest turnTest = new TurnTest();
		for (Node node : nodes) {
			Node next = node.getNext();
			isConvex = turnTest
					.isLeftTurn(new Point[] { node.getVertex(), next.getVertex(), next.getNext().getVertex() });
			if (!isConvex) {
				break;
			}
		}
		return isConvex;
	}

	public boolean isIncluded(Polygon polygon, Point point) {
		boolean isConvex = isConvex(polygon);
		if (!isConvex) {
			return false;
		}
		boolean isIncluded = true;
		List<Node> nodes = polygon.getNodes();
		TurnTest turnTest = new TurnTest();
		for (Node node : nodes) {
			isIncluded = turnTest.isLeftTurn(new Point[] { point, node.getVertex(), node.getNext().getVertex() });
			if (!isIncluded) {
				break;
			}
		}
		return isIncluded;
	}

	private List<Node> setPrevNext(List<Node> nodes) {
		int size = nodes.size();
		for (int i = 0; i < size; i++) {
			Node node = nodes.get(i);
			Node prev;
			Node next;
			if (i == 0) {
				prev = nodes.get(size - 1);
				next = nodes.get(i + 1);
			} else if (i == size - 1) {
				prev = nodes.get(i - 1);
				next = nodes.get(0);
			} else {
				prev = nodes.get(i - 1);
				next = nodes.get(i + 1);
			}
			node.setPrev(prev);
			node.setNext(next);
		}
		return nodes;
	}

	private List<Node> createNodes(List<Point> points) {
		int size = points.size();
		List<Node> nodes = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			Polygon.Node node = new Node(points.get(i));
			nodes.add(node);
		}
		return nodes;
	}
}
