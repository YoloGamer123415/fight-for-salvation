package com.yologamer123415.fightforsalvation.generators;

public class TilePosition {
	private final float x;
	private final float y;

	/**
	 * Construct a new TilePosition.
	 *
	 * @param x The x-position
	 * @param y The y-position
	 */
	public TilePosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Get the X-position of the tile.
	 *
	 * @return The X-position.
	 */
	public float getX() {
		return x;
	}

	/**
	 * Get the Y-position of the tile.
	 *
	 * @return The Y-position.
	 */
	public float getY() {
		return y;
	}
}