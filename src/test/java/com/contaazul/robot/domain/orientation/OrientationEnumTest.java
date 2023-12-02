package com.contaazul.robot.domain.orientation;

import com.contaazul.common.exception.ConverterException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class OrientationEnumTest {

	@ParameterizedTest
	@CsvSource({
			"N,NORTH",
			"W,WEST",
			"S,SOUTH",
			"E,EAST"
	})
	public void givenAEnumKeyWhenGetEnumThenReturnEnum(String orientationKey, OrientationEnum expectedOrientationEnum) {
		//Given & When
		OrientationEnum orientationEnum = OrientationEnum.getEnumByKey(orientationKey);

		//Then
		assertEquals(expectedOrientationEnum, orientationEnum);
	}


	@Test
	public void givenANonExistsEnumKeyWhenGetEnumThenThrowException() {
		//Given & When
		ConverterException exception = assertThrows(ConverterException.class,() -> OrientationEnum.getEnumByKey("J"));

		//Then
		assertEquals(exception.getMessage(), "Not found this orientation key: J");
	}
}