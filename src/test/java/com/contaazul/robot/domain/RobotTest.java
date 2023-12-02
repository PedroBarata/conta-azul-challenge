package com.contaazul.robot.domain;

import com.contaazul.robot.domain.orientation.OrientationEnum;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RobotTest {

	@Test
	public void givenARobotWhenGetPositionThenReturnPosition() {
		//Given
		Position position = new Position(1, 1, OrientationEnum.EAST);
		Robot robot = new Robot(1L, position);

		//When
		Position robotPosition = robot.getPosition();

		//Then
		assertEquals(robotPosition.getOrientation(), position.getOrientation());
		assertEquals(robotPosition.getX(), position.getX());
		assertEquals(robotPosition.getY(), position.getY());

	}

	@Test
	public void givenARobotWhenTakePictureThenReturnThePicture() throws IOException {
		//Given
		Position position = new Position(1, 1, OrientationEnum.EAST);
		Robot robot = new Robot(1L, position);

		//When
		byte[] picture = robot.takePicture();

		//Then
		assertNotNull(picture);
	}

}