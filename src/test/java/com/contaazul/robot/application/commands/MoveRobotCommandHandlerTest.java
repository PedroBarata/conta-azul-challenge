package com.contaazul.robot.application.commands;

import com.contaazul.common.exception.BadRequestException;
import com.contaazul.common.exception.NotFoundException;
import com.contaazul.robot.domain.orientation.OrientationEnum;
import com.contaazul.robot.infrastructure.data.RobotDto;
import com.contaazul.robot.infrastructure.data.RobotDtoBuilder;
import com.contaazul.robot.infrastructure.data.RobotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MoveRobotCommandHandlerTest {


	@Mock
	private RobotRepository repository;
	private MoveRobotCommandHandler handler;


	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		handler = new MoveRobotCommandHandler(repository);
	}

	@ParameterizedTest
	@ValueSource(strings = {"L", "M", "R"})
	public void givenAMoveCommandWhenHandleMoveThenMoveRobot(String command) {
		//Given
		MoveRobotCommand cmd = new MoveRobotCommand(1L, command);
		Optional<RobotDto> robot = Optional.of(
				new RobotDtoBuilder()
						.withId(1L)
						.withXPosition(0)
						.withYPosition(0)
						.withCoordination(OrientationEnum.NORTH)
						.buildRobotDto()
		);

		doReturn(robot).when(repository).findById(1L);

		//When
		assertDoesNotThrow(() -> handler.handle(cmd));

		//Then
		verify(repository, times(1)).findById(1L);
	}

	@Test
	public void givenAnInvalidRobotIdWhenHandleTMoveThenThrowsException() {
		//Given
		MoveRobotCommand cmd = new MoveRobotCommand(1234L, "L");
		doReturn(Optional.empty()).when(repository).findById(1234L);

		//When
		NotFoundException exception = assertThrows(NotFoundException.class, () -> handler.handle(cmd));

		//Then
		assertEquals("Robot not found", exception.getMessage());
	}

	@Test
	public void givenAnInvalidMoveCommandWhenHandleMoveThenThrowsException() {
		//Given
		MoveRobotCommand cmd = new MoveRobotCommand(1L, "X");
		Optional<RobotDto> robot = Optional.of(
				new RobotDtoBuilder()
						.withId(1L)
						.withXPosition(0)
						.withYPosition(0)
						.withCoordination(OrientationEnum.NORTH)
						.buildRobotDto()
		);

		doReturn(robot).when(repository).findById(1L);

		//When
		BadRequestException exception = assertThrows(BadRequestException.class, () -> handler.handle(cmd));

		//Then
		assertEquals("Command X does not exists", exception.getMessage());
	}
}