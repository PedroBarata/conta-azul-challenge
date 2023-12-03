package com.contaazul.presenter.api;


import com.contaazul.common.exception.BadRequestException;
import com.contaazul.common.exception.ConverterException;
import com.contaazul.common.exception.NotFoundException;
import com.contaazul.presenter.api.response.PositionResponse;
import com.contaazul.robot.application.commands.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "/robots", produces = MediaType.APPLICATION_JSON_VALUE)
public class RobotController {

	private static final Logger log = LoggerFactory.getLogger(RobotController.class);

	private final GetRobotPositionQueryHandler getRobotPositionQueryHandler;

	private final MoveRobotCommandHandler moveRobotCommandHandler;

	private final TakePictureQueryHandler takePictureQueryHandler;

	public RobotController(
			GetRobotPositionQueryHandler getRobotPositionQueryHandler,
			MoveRobotCommandHandler moveRobotCommandHandler,
			TakePictureQueryHandler takePictureQueryHandler
	) {
		Objects.requireNonNull(getRobotPositionQueryHandler);
		Objects.requireNonNull(moveRobotCommandHandler);
		Objects.requireNonNull(takePictureQueryHandler);

		this.getRobotPositionQueryHandler = getRobotPositionQueryHandler;
		this.moveRobotCommandHandler = moveRobotCommandHandler;
		this.takePictureQueryHandler = takePictureQueryHandler;
	}


	@GetMapping("{robotId}")
	public ResponseEntity<PositionResponse> getRobotPosition(@PathVariable(name = "robotId") Long robotId) {


		try {
			log.info("Starting getRobotPosition resource...");
			GetRobotPositionQuery cmd = new GetRobotPositionQuery(robotId);
			PositionResponse response = getRobotPositionQueryHandler.handle(cmd);
			log.info("GetRobotPosition done!. " +
							"Position = x_position: {}, " +
							"y_position: {}, " +
							"orientation: {}",
					response.getX(),
					response.getY(),
					response.getOrientation());

			return ResponseEntity
					.ok()
					.body(response);
		} catch (NotFoundException e) {
			log.error("The robotId {} was not found", robotId);

			return ResponseEntity
					.notFound()
					.build();
		} catch (ConverterException e) {
			log.error("The orientation was not found. Message: {}", e.getMessage());

			return ResponseEntity
					.internalServerError()
					.build();
		}
	}


	@PatchMapping("{robotId}/move-to")
	public ResponseEntity<?> moveRobot(@PathVariable(name = "robotId") Long robotId,
									   @RequestParam(name = "command", required = true) String command) {

		try {
			log.info("Starting moveRobot resource...");
			MoveRobotCommand cmd = new MoveRobotCommand(robotId, command);
			moveRobotCommandHandler.handle(cmd);
			log.info("MoveRobot done! The robot was moved to {}", cmd.getCommand());

			return ResponseEntity.noContent().build();
		} catch (NotFoundException e) {
			log.error("The robotId {} was not found", robotId);

			return ResponseEntity
					.notFound()
					.build();
		} catch (BadRequestException e) {
			log.error("The command is invalid. Message {}", e.getMessage());

			return ResponseEntity
					.badRequest()
					.build();
		} catch (ConverterException e) {
			log.error("The orientation was not found. Message: {}", e.getMessage());

			return ResponseEntity
					.internalServerError()
					.build();
		}
	}


	@GetMapping(value = "{robotId}/picture",
			produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> takePicture(@PathVariable(name = "robotId") Long robotId) {
		try {
			log.info("Starting takePicture resource...");

			TakePictureQuery cmd = new TakePictureQuery(robotId);
			byte[] picture = takePictureQueryHandler.handle(cmd);

			log.info("TakePicture done! The robot {} take the picure.", robotId);

			return ResponseEntity
					.ok()
					.body(picture);

		} catch (NotFoundException e) {
			log.error("The robotId {} was not found", robotId);

			return ResponseEntity
					.notFound()
					.build();
		} catch (ConverterException e) {
			log.error("The orientation was not found. Message: {}", e.getMessage());

			return ResponseEntity
					.internalServerError()
					.build();
		}
	}

}
