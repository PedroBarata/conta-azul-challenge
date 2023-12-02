package com.contaazul.presenter.api;


import com.contaazul.common.exception.BadRequestException;
import com.contaazul.common.exception.ConverterException;
import com.contaazul.common.exception.NotFoundException;
import com.contaazul.presenter.api.response.PositionResponse;
import com.contaazul.robot.application.commands.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/robot", produces = MediaType.APPLICATION_JSON_VALUE)
public class RobotController {
	@Autowired
	private GetRobotPositionQueryHandler getRobotPositionQueryHandler;

	@Autowired
	private MoveRobotCommandHandler moveRobotCommandHandler;

	@Autowired
	private TakePictureQueryHandler takePictureQueryHandler;

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
									   @RequestParam(name = "command", defaultValue = "L") String command) {

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
