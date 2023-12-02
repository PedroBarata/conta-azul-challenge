package com.contaazul.robot.domain.orientation;


import com.contaazul.robot.domain.Position;

public class West implements Instruction {
	@Override
	public OrientationEnum turnLeft() {
		return OrientationEnum.SOUTH;
	}

	@Override
	public OrientationEnum turnRight() {
		return OrientationEnum.NORTH;
	}

	@Override
	public void goForward(Position position) {
		position.removeX();
	}
}