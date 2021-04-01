package com.yologamer123415.fightforsalvation.helpers;

import com.yologamer123415.fightforsalvation.generators.MapGenerator;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.objects.GameObject;

/**
 * Helper class for calculating and handling collisions
 */
public class CollidingHelper {
	/**
	 * Calculate the collision side from the angle
	 * @see <a href="https://github.com/HANICA/oopg/blob/d00d3272eec6a9b8ba2c513b4bc4b3d4e5cd2722/src/main/java/nl/han/ica/oopg/engine/GameEngine.java#L582">OOPG</a> for the source
	 *
	 * @param angle The angle between the object(s) (and tile)
	 * @return The {@link CollisionSide} of the collision
	 */
	public static CollisionSide calculateCollidedTileSide(int angle) {
		if (angle >= 136 && angle <= 225) {
			return CollisionSide.BOTTOM;
		} else if (angle >= 226 && angle <= 315) {
			return CollisionSide.LEFT;
		} else if ((angle >= 316 && angle <= 360) || angle >= 0 && angle <= 45) {
			return CollisionSide.TOP;
		} else if (angle >= 46 && angle <= 135) {
			return CollisionSide.RIGHT;
		}
		return null;
	}

	/**
	 * Stop the toHandleFor object at collision with something else.
	 *
	 * @param toHandleFor The object to stop
	 * @param side The collision side
	 * @param x The x-position of the 'something else' object/else
	 * @param y The y-position of the 'something else' object/else
	 */
	public static void handleCollisionStop(GameObject toHandleFor, CollisionSide side, float x, float y) {
		switch (side) {
			case TOP:
				toHandleFor.setY(y - toHandleFor.getHeight());
				break;
			case RIGHT:
				toHandleFor.setX(x + MapGenerator.TILESIZE);
				break;
			case LEFT:
				toHandleFor.setX(x - toHandleFor.getWidth());
				break;
			case BOTTOM:
				toHandleFor.setY(y + MapGenerator.TILESIZE);
				break;
		}
	}
}
