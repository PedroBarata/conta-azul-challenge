package com.contaazul.robot.domain.orientation;


import com.contaazul.robot.domain.Position;

public interface Instruction {
	OrientationEnum turnLeft();
	OrientationEnum turnRight();
	void goForward(Position position);
}
