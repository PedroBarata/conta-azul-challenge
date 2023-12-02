package com.contaazul.robot.domain;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;

public class Robot {

	private final Long id;
	private final Position position;

	public Robot(Long id, Position position) {
		this.id = id;
		this.position = position;
	}


	public Position getPosition() {
		return position;
	}

	public Long getId() {
		return id;
	}

	public byte[] takePicture() throws IOException {
		InputStream imgFile = getClass().getClassLoader().getResourceAsStream("static/mars.jpg");
		return StreamUtils.copyToByteArray(imgFile);
	}

}
