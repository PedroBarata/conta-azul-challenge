package com.contaazul.robot.domain.orientation;


import com.contaazul.robot.domain.Position;

public class East implements Instruction {
	@Override
	public OrientationEnum turnLeft() {
		return OrientationEnum.NORTH;
	}

	@Override
	public OrientationEnum turnRight() {
		return OrientationEnum.SOUTH;
	}

	@Override
	public void goForward(Position position) {
		position.addX();
	}
}
