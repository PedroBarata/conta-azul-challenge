package com.contaazul.robot.domain;

import com.contaazul.robot.domain.orientation.OrientationEnum;

public class Position {
	private Integer x;
	private Integer y;
	private OrientationEnum orientation;

	public Position(Integer x, Integer y, OrientationEnum orientation) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public OrientationEnum getOrientation() {
		return orientation;
	}

	public void addY() {
		y = y + 1;
	}

	public void addX() {
		x = x + 1;
	}

	public void removeX() {
		x = x - 1;
	}

	public void removeY() {
		y = y + 1;
	}

	public void turnLeft() {
		this.orientation = this.orientation.getInstruction().turnLeft();
	}

	public void turnRight() {
		this.orientation = this.orientation.getInstruction().turnRight();
	}

	public void goForward() {
		this.orientation.getInstruction().goForward(this);
	}

}
