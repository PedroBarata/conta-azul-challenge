package com.contaazul.robot.application.commands;

import com.contaazul.common.presenter.QueryHandler;
import com.contaazul.robot.domain.Robot;
import com.contaazul.robot.infrastructure.data.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class TakePictureQueryHandler implements QueryHandler<TakePictureQuery, byte[]> {

	@Autowired
	private RobotRepository repository;

	@Override
	public byte[] handle(TakePictureQuery cmd) {

		try {
			Robot robot = repository.findById(cmd.getRobotId())
					.orElseThrow(() -> new RuntimeException("Not found"))
					.toRobot();

			return robot.takePicture();
		} catch (IOException ioException) {
			throw new RuntimeException("Error on loading image", ioException);
		}
	}
}
