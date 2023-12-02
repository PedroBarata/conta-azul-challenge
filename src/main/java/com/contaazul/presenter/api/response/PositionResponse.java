package com.contaazul.presenter.api.response;

import com.contaazul.robot.domain.Position;

public class PositionResponse {
	private Integer x;
	private Integer y;
	private String orientation;

	protected PositionResponse(Integer x, Integer y, String orientation) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}



	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public String getOrientation() {
		return orientation;
	}

}
