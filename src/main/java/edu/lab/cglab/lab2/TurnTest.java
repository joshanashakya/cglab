package edu.lab.cglab.lab2;

import edu.lab.cglab.lab1.Point;
import edu.lab.cglab.lab1.PointService;

public class TurnTest {

	public String getTurn(Point[] points) {
		PointService pointService = new PointService();
		double area = pointService.computeArea(points);
		return area > 0 ? "left" : "right";
	}

	public boolean isLeftTurn(Point[] points) {
		String turn = getTurn(points);
		return "left".equalsIgnoreCase(turn);
	}

	public boolean isRightTurn(Point[] points) {
		String turn = getTurn(points);
		return "right".equalsIgnoreCase(turn);
	}
}
