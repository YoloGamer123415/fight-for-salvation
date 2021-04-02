package com.yologamer123415.fightforsalvation.helpers;

public class Vector {
	private final float x;
	private final float y;
	private float angle = 0;
	private float length = 0;

	/**
	 * Construct a new Vector with x and y.
	 *
	 * @param x The x-position.
	 * @param y The y-position.
	 */
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Construct a new Vector with x, y and angle.
	 *
	 * @param x The x-position.
	 * @param y The y-position.
	 * @param angle The angle of the vector.
	 */
	public Vector(float x, float y, float angle) {
		this(x, y);

		this.angle = angle;
	}

	/**
	 * Construct a new Vector with x, y, angle and length.
	 *
	 * @param x The x-position.
	 * @param y The y-position.
	 * @param angle The angle of the vector.
	 * @param length The length of the vector.
	 */
	public Vector(float x, float y, float angle, float length) {
		this(x, y, angle);

		this.length = length;
	}

	/**
	 * Generate a vector from to points.
	 * @see <a href="https://github.com/YoloGamer123415/fight-for-salvation/blob/main/calculate_angle_between_points.jpg">The method used for calculating the angle between the two points</a>
	 *
	 * @param px The x coordinate of the first point.
	 * @param py The y coordinate of the first point.
	 * @param mx The x coordinate of the second point.
	 * @param my The y coordinate of the second point.
	 * @return The generated {@link Vector}.
	 */
	public static Vector generateVector(float px, float py, float mx, float my) {
		double lp = mx - px;
		double mp = LocationHelper.calculateDistanceBetweenTwoPoints(mx, my, px, py);
		double angle = Math.toDegrees( Math.acos(lp / mp) );

		if (my < py) angle = 360 - angle;

		return new Vector(px, py, (float) angle + 90, (float) mp);
	}

	/**
	 * Get the x-position.
	 *
	 * @return The x-position.
	 */
	public float getX() {
		return x;
	}

	/**
	 * Get the y-position.
	 *
	 * @return The y-position.
	 */
	public float getY() {
		return y;
	}

	/**
	 * Get the angle.
	 *
	 * @return The angle.
	 */
	public float getAngle() {
		return angle;
	}

	/**
	 * Get the length.
	 *
	 * @return The length.
	 */
	public float getLength() {
		return length;
	}
}
