package com.yologamer123415.fightforsalvation.helpers;

public class Vector {
	private float x;
	private float y;
	private float angle = 0;
	private float length = 0;

	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector(float x, float y, float angle) {
		this(x, y);

		this.angle = angle;
	}

	public Vector(float x, float y, float angle, float length) {
		this(x, y, angle);

		this.length = length;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getAngle() {
		return angle;
	}

	public float getLength() {
		return length;
	}
}
