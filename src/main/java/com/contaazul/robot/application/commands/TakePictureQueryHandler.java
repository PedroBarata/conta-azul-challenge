package com.contaazul.robot.application.commands;

import com.contaazul.common.exception.NotFoundException;
import com.contaazul.common.presenter.QueryHandler;
import com.contaazul.robot.domain.Robot;
import com.contaazul.robot.infrastructure.data.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class TakePictureQueryHandler implements QueryHandler<TakePictureQuery, byte[]> {

	private final RobotRepository repository;

	public TakePictureQueryHandler(RobotRepository repository) {
		Objects.requireNonNull(repository);
		this.repository = repository;
	}

	@Override
	public byte[] handle(TakePictureQuery cmd) {

		try {
			Robot robot = repository.findById(cmd.getRobotId())
					.orElseThrow(() -> new NotFoundException("Robot"))
					.toRobot();

			return robot.takePicture();
		} catch (IOException ioException) {
			throw new RuntimeException("Error on loading image", ioException);
		}
	}
}
