package com.yologamer123415.fightforsalvation.helpers;

public class Vector {
	private final float x;
	private final float y;
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

	/**
	 * Generate a vector from to points.
	 *
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
		double mp = Math.sqrt(
				Math.pow( Math.abs(mx - px), 2 ) + Math.pow( Math.abs(my - py), 2 )
		);
		double angle = Math.toDegrees( Math.acos(lp / mp) );

		if (my < py) angle = 360 - angle;

		return new Vector(px, py, (float) angle + 90, (float) mp);
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

	@Override
	public String toString() {
		return "Vector{" +
				"x=" + x +
				", y=" + y +
				", angle=" + angle +
				", length=" + length +
				'}';
	}
}
