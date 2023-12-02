package com.contaazul.robot.application.commands;

import com.contaazul.common.exception.NotFoundException;
import com.contaazul.presenter.api.response.PositionResponse;
import com.contaazul.robot.domain.orientation.OrientationEnum;
import com.contaazul.robot.infrastructure.data.RobotDto;
import com.contaazul.robot.infrastructure.data.RobotDtoBuilder;
import com.contaazul.robot.infrastructure.data.RobotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class GetRobotPositionQueryHandlerTest {

	@Mock
	private RobotRepository repository;
	private GetRobotPositionQueryHandler handler;


	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		handler = new GetRobotPositionQueryHandler(repository);
	}

	@Test
	public void givenAGetPositionQueryWhenHandleThenReturnsRobotPosition() {
		//Given
		GetRobotPositionQuery cmd = new GetRobotPositionQuery(1L);
		Optional<RobotDto> robot = Optional.of(
				new RobotDtoBuilder()
						.withId(1L)
						.withXPosition(0)
						.withYPosition(1)
						.withCoordination(OrientationEnum.NORTH)
						.buildRobotDto()
		);

		doReturn(robot).when(repository).findById(1L);

		//When
		PositionResponse response = handler.handle(cmd);

		//Then
		verify(repository, times(1)).findById(1L);
		assertEquals(0, response.getX());
		assertEquals(1, response.getY());
		assertEquals("N", response.getOrientation());

	}

	@Test
	public void givenAnInvalidRobotIdWhenHandleTMoveThenThrowsException() {
		//Given
		GetRobotPositionQuery cmd = new GetRobotPositionQuery(1234L);
		doReturn(Optional.empty()).when(repository).findById(1234L);

		//When
		NotFoundException exception = assertThrows(NotFoundException.class, () -> handler.handle(cmd));

		//Then
		verify(repository, times(1)).findById(1234L);
		assertEquals("Robot not found", exception.getMessage());
	}

}