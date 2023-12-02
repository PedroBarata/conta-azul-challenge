package com.contaazul.presenter.api;

import com.contaazul.common.exception.BadRequestException;
import com.contaazul.common.exception.ConverterException;
import com.contaazul.common.exception.NotFoundException;
import com.contaazul.presenter.api.response.PositionResponse;
import com.contaazul.presenter.api.response.PositionResponseBuilder;
import com.contaazul.robot.application.commands.GetRobotPositionQueryHandler;
import com.contaazul.robot.application.commands.MoveRobotCommandHandler;
import com.contaazul.robot.application.commands.TakePictureQueryHandler;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RobotControllerTest {

	private RobotController controller;
	@Mock
	private GetRobotPositionQueryHandler getRobotPositionQueryHandler;
	@Mock
	private MoveRobotCommandHandler moveRobotCommandHandler;
	@Mock
	private TakePictureQueryHandler takePictureQueryHandler;

	@Before
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		controller = new RobotController(
				getRobotPositionQueryHandler,
				moveRobotCommandHandler,
				takePictureQueryHandler
		);
	}

	@Test
	public void givenGetRobotPositionWhenRobotIdExistsThenReturnOk() {
		//Given
		PositionResponse response = new PositionResponseBuilder()
				.withXPosition(0)
				.withYPosition(1)
				.withOrientation("N")
				.buildPositionResponse();

		doReturn(response).when(getRobotPositionQueryHandler).handle(any());

		//When
		ResponseEntity<PositionResponse> respEntity = controller.getRobotPosition(1L);

		//Then
		assertEquals(HttpStatusCode.valueOf(200), respEntity.getStatusCode());
		assertTrue(respEntity.hasBody());
	}

	@Test
	public void givenGetRobotPositionWhenRobotIdNotExistsThenReturnNotFound() {
		//Given
		doThrow(NotFoundException.class).when(getRobotPositionQueryHandler).handle(any());

		//When
		ResponseEntity<PositionResponse> respEntity = controller.getRobotPosition(1L);

		//Then
		assertEquals(HttpStatusCode.valueOf(404), respEntity.getStatusCode());
		assertFalse(respEntity.hasBody());
	}

	@Test
	public void givenGetRobotPositionWhenOrientationNotExistsThenReturnInternalServerError() {
		//Given
		doThrow(ConverterException.class).when(getRobotPositionQueryHandler).handle(any());

		//When
		ResponseEntity<PositionResponse> respEntity = controller.getRobotPosition(1L);

		//Then
		assertEquals(HttpStatusCode.valueOf(500), respEntity.getStatusCode());
		assertFalse(respEntity.hasBody());
	}

	@Test
	public void givenMoveRobotWhenRobotIdExistsThenReturnNoContent() {
		//Given
		doNothing().when(moveRobotCommandHandler).handle(any());

		//When
		ResponseEntity<?> respEntity = controller.moveRobot(1L, "M");

		//Then
		assertEquals(HttpStatusCode.valueOf(204), respEntity.getStatusCode());
		assertFalse(respEntity.hasBody());
	}

	@Test
	public void givenMoveRobotWhenRobotIdNotExistsThenReturnNoContent() {
		//Given
		doThrow(NotFoundException.class).when(moveRobotCommandHandler).handle(any());

		//When
		ResponseEntity<?> respEntity = controller.moveRobot(1L, "X");

		//Then
		assertEquals(HttpStatusCode.valueOf(404), respEntity.getStatusCode());
		assertFalse(respEntity.hasBody());
	}

	@Test
	public void givenMoveRobotWhenCommandNotExistsThenReturnBadRequest() {
		//Given
		doThrow(BadRequestException.class).when(moveRobotCommandHandler).handle(any());

		//When
		ResponseEntity<?> respEntity = controller.moveRobot(1L, "X");

		//Then
		assertEquals(HttpStatusCode.valueOf(400), respEntity.getStatusCode());
		assertFalse(respEntity.hasBody());
	}

	@Test
	public void givenMoveRobotWhenOrientationNotExistsThenReturnInternalServerError() {
		//Given
		doThrow(ConverterException.class).when(moveRobotCommandHandler).handle(any());

		//When
		ResponseEntity<?> respEntity = controller.moveRobot(1L, "L");

		//Then
		assertEquals(HttpStatusCode.valueOf(500), respEntity.getStatusCode());
		assertFalse(respEntity.hasBody());
	}

	@Test
	public void givenTakePictureWhenRobotIdExistsThenReturnOk() {
		//Given
		byte[] picture = new byte[]{};
		doReturn(picture).when(takePictureQueryHandler).handle(any());

		//When
		ResponseEntity<byte[]> respEntity = controller.takePicture(1L);

		//Then
		assertEquals(HttpStatusCode.valueOf(200), respEntity.getStatusCode());
		assertTrue(respEntity.hasBody());
	}

	@Test
	public void givenTakePictureWhenRobotIdNotExistsThenReturnNotFound() {
		//Given
		doThrow(NotFoundException.class).when(takePictureQueryHandler).handle(any());

		//When
		ResponseEntity<byte[]> respEntity = controller.takePicture(1L);

		//Then
		assertEquals(HttpStatusCode.valueOf(404), respEntity.getStatusCode());
		assertFalse(respEntity.hasBody());
	}

	@Test
	public void givenTakePictureWhenOrientationNotExistsThenReturnInternalServerError() {
		//Given
		doThrow(ConverterException.class).when(takePictureQueryHandler).handle(any());

		//When
		ResponseEntity<byte[]> respEntity = controller.takePicture(1L);

		//Then
		assertEquals(HttpStatusCode.valueOf(500), respEntity.getStatusCode());
		assertFalse(respEntity.hasBody());
	}
}