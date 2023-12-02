package com.contaazul.robot.infrastructure.data;

import com.contaazul.robot.domain.orientation.OrientationEnum;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RobotDtoBuilderTest {

	private RobotDtoBuilder builder = new RobotDtoBuilder();

	@Test
	public void givenRobotDtoWithIdWhenBuildDtoThenReturnDto() {
		//Given & When
		RobotDto dto = builder
				.withId(1L)
				.withCoordination(OrientationEnum.NORTH)
				.withXPosition(0)
				.withYPosition(1)
				.buildRobotDto();

		//Then
		assertEquals(1L, dto.getId());
		assertEquals(0, dto.getX());
		assertEquals(1, dto.getY());
		assertEquals("N", dto.getOrientation());
	}

	@Test
	public void givenRobotDtoWithoutIdWhenBuildDtoThenReturnDto() {
		//Given & When
		RobotDto dto = builder
				.withCoordination(OrientationEnum.NORTH)
				.withXPosition(0)
				.withYPosition(1)
				.buildRobotDto();

		//Then
		assertNull(dto.getId());
		assertEquals(0, dto.getX());
		assertEquals(1, dto.getY());
		assertEquals("N", dto.getOrientation());
	}

}