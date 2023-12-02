package com.contaazul.robot.infrastructure.data;

import com.contaazul.robot.domain.orientation.OrientationEnum;

public class RobotDtoBuilder {

	private Long id;
	private Integer x;
	private Integer y;
	private String coordination;

	public RobotDtoBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public RobotDtoBuilder withXPosition(Integer x) {
		this.x = x;
		return this;
	}

	public RobotDtoBuilder withYPosition(Integer y) {
		this.y = y;
		return this;
	}

	public RobotDtoBuilder withCoordination(OrientationEnum coordination) {
		this.coordination = coordination.getCoordinationKey();
		return this;
	}

	public RobotDto buildRobotDto() {
		if (id != null) {
			return new RobotDto(
					this.id,
					this.x,
					this.y,
					this.coordination
			);
		}

		return new RobotDto(
				this.x,
				this.y,
				this.coordination
		);
	}

}
