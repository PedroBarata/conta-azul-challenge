package com.contaazul.presenter.api.response;

public class PositionResponseBuilder {

	private Integer x;
	private Integer y;
	private String orientation;

	public PositionResponseBuilder withXPosition(Integer x) {
		this.x = x;
		return this;
	}

	public PositionResponseBuilder withYPosition(Integer y) {
		this.y = y;
		return this;
	}

	public PositionResponseBuilder withOrientation(String orientation) {
		this.orientation = orientation;
		return this;
	}

	public PositionResponse buildPositionResponse() {
		return new PositionResponse(
				this.x,
				this.y,
				this.orientation
		);
	}
}
