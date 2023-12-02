package com.contaazul.robot.application.commands;

import com.contaazul.common.exception.NotFoundException;
import com.contaazul.common.presenter.QueryHandler;
import com.contaazul.presenter.api.response.PositionResponse;
import com.contaazul.presenter.api.response.PositionResponseBuilder;
import com.contaazul.robot.domain.Position;
import com.contaazul.robot.domain.Robot;
import com.contaazul.robot.infrastructure.data.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GetRobotPositionQueryHandler implements QueryHandler<GetRobotPositionQuery, PositionResponse> {

	private final RobotRepository repository;

	public GetRobotPositionQueryHandler(RobotRepository repository) {
		Objects.requireNonNull(repository);
		this.repository = repository;
	}

	@Override
	public PositionResponse handle(GetRobotPositionQuery cmd) {
		Position position = repository.findById(cmd.getRobotId())
				.orElseThrow(() -> new NotFoundException("Robot"))
				.toRobot().getPosition();

		return new PositionResponseBuilder()
				.withXPosition(position.getX())
				.withYPosition(position.getY())
				.withOrientation(position.getOrientation().getCoordinationKey())
				.buildPositionResponse();
	}

}
