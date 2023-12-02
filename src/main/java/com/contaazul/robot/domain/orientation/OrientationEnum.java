package com.contaazul.robot.domain.orientation;

import java.util.Arrays;

public enum OrientationEnum {
	NORTH("N", new North()),
	WEST("W", new West()),
	EAST("E", new East()),
	SOUTH("S", new South());
	private final String orientationKey;
	private final Instruction instruction;

	OrientationEnum(String orientationKey, Instruction instruction) {
		this.orientationKey = orientationKey;
		this.instruction = instruction;
	}

	public static OrientationEnum getEnumByKey(String key) {
		return Arrays.stream(OrientationEnum.values())
				.filter(coordinatesEnum -> coordinatesEnum.orientationKey.equals(key))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Not found this orientation key: " + key));
	}

	public String getCoordinationKey() {
		return orientationKey;
	}

	public Instruction getInstruction() {
		return instruction;
	}
}
