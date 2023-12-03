package com.contaazul.presenter.api;


import com.contaazul.common.exception.BadRequestException;
import com.contaazul.common.exception.ConverterException;
import com.contaazul.common.exception.NotFoundException;
import com.contaazul.presenter.api.response.PositionResponse;
import com.contaazul.robot.application.commands.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "/robots", produces = MediaType.APPLICATION_JSON_VALUE)
public class RobotController {

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
		GetRobotPositionQuery cmd = new GetRobotPositionQuery(robotId);

		try {
			PositionResponse response = getRobotPositionQueryHandler.handle(cmd);
			return ResponseEntity
					.ok()
					.body(response);
		} catch (NotFoundException e) {
			return ResponseEntity
					.notFound()
					.build();
		} catch (ConverterException e) {
			return ResponseEntity
					.internalServerError()
					.build();
		}
	}


	@PatchMapping("{robotId}/move-to")
	public ResponseEntity<?> moveRobot(@PathVariable(name = "robotId") Long robotId,
									   @RequestParam(name = "command", required = true) String command) {

		try {
			MoveRobotCommand cmd = new MoveRobotCommand(robotId, command);
			moveRobotCommandHandler.handle(cmd);

			return ResponseEntity.noContent().build();
		} catch (NotFoundException e) {
			return ResponseEntity
					.notFound()
					.build();
		} catch (BadRequestException e) {
			return ResponseEntity
					.badRequest()
					.build();
		} catch (ConverterException e) {
			return ResponseEntity
					.internalServerError()
					.build();
		}
	}


	@GetMapping(value = "{robotId}/picture",
			produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> takePicture(@PathVariable(name = "robotId") Long robotId) {
		try {
			TakePictureQuery cmd = new TakePictureQuery(robotId);
			byte[] picture = takePictureQueryHandler.handle(cmd);

			return ResponseEntity
					.ok()
					.body(picture);

		} catch (NotFoundException e) {
			return ResponseEntity
					.notFound()
					.build();
		} catch (ConverterException e) {
			return ResponseEntity
					.internalServerError()
					.build();
		}
	}

}
