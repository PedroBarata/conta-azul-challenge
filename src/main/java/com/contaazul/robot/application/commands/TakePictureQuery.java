package com.contaazul.robot.application.commands;

import com.contaazul.common.presenter.Command;
import com.contaazul.common.presenter.Query;

public class TakePictureQuery implements Query<byte[]> {

	private final Long robotId;

	public TakePictureQuery(Long robotId) {
		this.robotId = robotId;
	}

	public Long getRobotId() {
		return robotId;
	}

}
