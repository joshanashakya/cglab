package edu.lab.cglab.lab3;

import java.util.ArrayList;
import java.util.List;

import edu.lab.cglab.lab1.Point;

public class Polygon {

	private List<Node> nodes = new ArrayList<>();

	public Polygon(List<Node> nodes) {
		this.nodes = nodes;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	static class Node {
		Point vertex;
		Node prev;
		Node next;

		public Node(Point vertex) {
			this.vertex = vertex;
		}

		public Point getVertex() {
			return vertex;
		}

		public Node getPrev() {
			return prev;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}
}
