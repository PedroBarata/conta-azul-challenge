package com.contaazul.robot.domain;

import com.contaazul.robot.domain.orientation.OrientationEnum;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PositionTest {

	@ParameterizedTest
	@CsvSource({
			"NORTH,WEST",
			"WEST,SOUTH",
			"SOUTH,EAST",
			"EAST,NORTH"
	})
	public void givenAPositionWhenTurnLeftThenTurnsTheRobot(OrientationEnum orientationEnum, OrientationEnum expectedOrientation) {
		//Given
		Position position = new Position(0, 0, orientationEnum);

		//When
		position.turnLeft();

		//Then
		assertEquals(expectedOrientation, position.getOrientation());
	}

	@ParameterizedTest
	@CsvSource({
			"NORTH,EAST",
			"EAST,SOUTH",
			"SOUTH,WEST",
			"WEST,NORTH"
	})
	public void givenAPositionWhenTurnRightThenTurnsTheRobot(OrientationEnum orientationEnum, OrientationEnum expectedOrientation) {
		//Given
		Position position = new Position(0, 0, orientationEnum);

		//When
		position.turnRight();

		//Then
		assertEquals(expectedOrientation, position.getOrientation());
	}

	@ParameterizedTest
	@CsvSource({
			"NORTH,0,1",
			"EAST,1,0",
			"SOUTH,0,-1",
			"WEST,-1,0"
	})
	public void givenAPositionWhenGoForwardThenMovesTheRobot(OrientationEnum orientationEnum, Integer expectedX, Integer expectedY) {
		//Given
		Position position = new Position(0, 0, orientationEnum);

		//When
		position.goForward();

		//Then
		assertEquals(expectedX, position.getX());
		assertEquals(expectedY, position.getY());
	}
}