package com.contaazul.robot.infrastructure.data;

import com.contaazul.robot.domain.Robot;
import com.contaazul.robot.domain.orientation.OrientationEnum;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RobotDtoTest {

	@Test
	public void givenRobotDtoWhenConvertingToEntityThenReturnEntity() {
		//Given
		RobotDto dto = new RobotDto(1L, 0, 1, "N");

		//When
		Robot robot = dto.toRobot();

		//Then
		assertEquals(0, robot.getPosition().getX());
		assertEquals(1, robot.getPosition().getY());
		assertEquals(OrientationEnum.NORTH, robot.getPosition().getOrientation());

	}

}