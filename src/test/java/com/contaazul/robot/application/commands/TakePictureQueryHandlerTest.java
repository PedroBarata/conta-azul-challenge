package com.contaazul.robot.application.commands;

import com.contaazul.common.exception.NotFoundException;
import com.contaazul.robot.domain.orientation.OrientationEnum;
import com.contaazul.robot.infrastructure.data.RobotDto;
import com.contaazul.robot.infrastructure.data.RobotDtoBuilder;
import com.contaazul.robot.infrastructure.data.RobotRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TakePictureQueryHandlerTest {

	@Mock
	private RobotRepository repository;
	private TakePictureQueryHandler handler;


	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		handler = new TakePictureQueryHandler(repository);
	}

	@Test
	public void givenARobotIdWhenHandleTakePictureThenReturnPicture() {
		//Given
		TakePictureQuery cmd = new TakePictureQuery(1L);
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
		byte[] picture = handler.handle(cmd);

		//Then
		assertNotNull(picture);
	}

	@Test
	public void givenAnInvalidRobotIdWhenHandleTakePictureThenThrowsException() {
		//Given
		TakePictureQuery cmd = new TakePictureQuery(1234L);
		doReturn(Optional.empty()).when(repository).findById(1234L);

		//When
		NotFoundException exception = assertThrows(NotFoundException.class, () -> handler.handle(cmd));

		//Then
		assertEquals("Robot not found", exception.getMessage());
	}

}