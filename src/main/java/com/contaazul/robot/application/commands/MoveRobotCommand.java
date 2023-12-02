package com.contaazul.robot.application.commands;

import com.contaazul.common.presenter.Command;

public class MoveRobotCommand implements Command {

	private Long robotId;
	private String command;

	public MoveRobotCommand(Long robotId, String command) {
		this.robotId = robotId;
		this.command = command;
	}

	public Long getRobotId() {
		return robotId;
	}

	public String getCommand() {
		return command;
	}

}
