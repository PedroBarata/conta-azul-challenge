package com.contaazul.presenter.api.response;

public class PositionResponse {
	private Integer x;
	private Integer y;
	private final String orientation;

	protected PositionResponse(Integer x, Integer y, String orientation) {
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

	public String getOrientation() {
		return orientation;
	}

}
