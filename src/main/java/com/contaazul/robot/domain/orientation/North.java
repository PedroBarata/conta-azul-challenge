package com.contaazul.robot.domain.orientation;


import com.contaazul.robot.domain.Position;

public class North implements Instruction {
	@Override
	public OrientationEnum turnLeft() {
		return OrientationEnum.WEST;
	}

	@Override
	public OrientationEnum turnRight() {
		return OrientationEnum.EAST;
	}

	@Override
	public void goForward(Position position) {
		position.addY();
	}
}
