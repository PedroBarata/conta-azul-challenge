package com.contaazul.presenter.api.response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PositionResponseBuilderTest {

	private final PositionResponseBuilder builder = new PositionResponseBuilder();

	@Test
	public void givenPositionResponseWhenBuildResponseThenResponse() {
		//Given & When
		PositionResponse dto = builder
				.withXPosition(0)
				.withYPosition(1)
				.withOrientation("N")
				.buildPositionResponse();

		//Then
		assertEquals(0, dto.getX());
		assertEquals(1, dto.getY());
		assertEquals("N", dto.getOrientation());
	}

}