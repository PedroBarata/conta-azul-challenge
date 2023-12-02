package com.contaazul.robot.domain.orientation;


import com.contaazul.robot.domain.Position;

public class South implements Instruction {
	@Override
	public OrientationEnum turnLeft() {
		return OrientationEnum.EAST;
	}

	@Override
	public OrientationEnum turnRight() {
		return OrientationEnum.WEST;
	}

	@Override
	public void goForward(Position position) {
		position.removeY();
	}
}
