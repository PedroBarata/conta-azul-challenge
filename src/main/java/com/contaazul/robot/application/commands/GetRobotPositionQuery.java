package com.contaazul.robot.application.commands;

import com.contaazul.common.presenter.Query;
import com.contaazul.presenter.api.response.PositionResponse;

public class GetRobotPositionQuery implements Query<PositionResponse> {

	private final Long robotId;

	public GetRobotPositionQuery(Long robotId) {
		this.robotId = robotId;
	}

	public Long getRobotId() {
		return robotId;
	}
}
