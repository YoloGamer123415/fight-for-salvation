package com.yologamer123415.fightforsalvation.generators;

public class TilePosition {
	private final float x;
	private final float y;

	public TilePosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	@Override
	public String toString() {
		return "TilePosition{" +
				"x=" + x +
				", y=" + y +
				'}';
	}
}