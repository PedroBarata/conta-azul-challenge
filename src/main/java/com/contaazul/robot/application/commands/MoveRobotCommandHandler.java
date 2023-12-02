package com.contaazul.robot.application.commands;

import com.contaazul.common.exception.BadRequestException;
import com.contaazul.common.presenter.CommandHandler;
import com.contaazul.robot.domain.Robot;
import com.contaazul.robot.infrastructure.data.RobotDto;
import com.contaazul.robot.infrastructure.data.RobotDtoBuilder;
import com.contaazul.robot.infrastructure.data.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoveRobotCommandHandler implements CommandHandler<MoveRobotCommand> {

	@Autowired
	private RobotRepository repository;

	@Override
	public void handle(MoveRobotCommand cmd) {
		Robot robot = repository.findById(cmd.getRobotId())
				.orElseThrow(() -> new RuntimeException("Not found"))
				.toRobot();
		switch (cmd.getCommand()) {
			case "M":
				robot.getPosition().goForward();
				break;
			case "R":
				robot.getPosition().turnRight();
				break;
			case "L":
				robot.getPosition().turnLeft();
				break;
			default:
				throw new BadRequestException("Command " + cmd.getCommand() + "does not exists.");
		}

		RobotDto robotDto = new RobotDtoBuilder()
				.withId(robot.getId())
				.withYPosition(robot.getPosition().getY())
				.withXPosition(robot.getPosition().getX())
				.withCoordination(robot.getPosition().getOrientation())
				.buildRobotDto();

		repository.save(robotDto);
	}
}
