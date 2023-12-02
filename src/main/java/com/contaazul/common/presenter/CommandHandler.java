package com.contaazul.common.presenter;

import com.contaazul.presenter.api.response.PositionResponse;
import com.contaazul.robot.application.commands.GetRobotPositionQuery;

public interface CommandHandler<TCommand  extends Command> {
	void handle(TCommand cmd);
}
