package com.contaazul.robot.infrastructure.data;

import com.contaazul.robot.domain.Position;
import com.contaazul.robot.domain.Robot;
import com.contaazul.robot.domain.orientation.OrientationEnum;
import jakarta.persistence.*;

@Table(name = "robots")
@Entity
public class RobotDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "x_position")
	private Integer x;

	@Column(name = "y_position")
	private Integer y;

	@Column(name = "orientation")
	private String orientation;

	public RobotDto() {
	}

	protected RobotDto(Long id, Integer x, Integer y, String orientation) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}

	protected RobotDto(Integer x, Integer y, String orientation) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}

	public Long getId() {
		return id;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public String getOrientation() {
		return orientation;
	}

	public Robot toRobot() {
		Position position = new Position(this.x, this.y, OrientationEnum.getEnumByKey(this.orientation));
		return new Robot(this.id, position);
	}

}
