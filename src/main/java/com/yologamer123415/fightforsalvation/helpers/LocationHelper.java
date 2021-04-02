package com.yologamer123415.fightforsalvation.helpers;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.generators.MapGenerator;
import com.yologamer123415.fightforsalvation.monsters.Monster;
import com.yologamer123415.fightforsalvation.player.Player;
import nl.han.ica.oopg.objects.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class LocationHelper {
	/**
	 * Get the half tile size.
	 *
	 * @return The half tile size
	 */
	public static float getHalfTileSize() {
		return (float) (MapGenerator.TILESIZE / 2.0);
	}

	/**
	 * Convert a screen pixel to a tile pixel.
	 *
	 * @param pixelLocation The screen pixel.
	 * @return The tile pixel.
	 */
	public static float screenToTilePixel(float pixelLocation) {
		return pixelLocation / MapGenerator.TILESIZE;
	}

	/**
	 * Convert a tile pixel to a screen pixel.
	 *
	 * @param pixelLocation The tile pixel.
	 * @return The screen pixel.
	 */
	public static float tileToScreenPixel(float pixelLocation) {
		return pixelLocation * MapGenerator.TILESIZE;
	}

	/**
	 * Find all the targets in a range.
	 *
	 * @param range The range to detect in.
	 * @param aroundObject The object to search around.
	 * @return A list of found targets.
	 */
	public static List<GameObject> findTargetsWithinRange(double range, GameObject aroundObject) {
		if (aroundObject instanceof Monster || aroundObject instanceof Player) {
			Vector<GameObject> gameObjects = FightForSalvation.getInstance().getGameObjectItems();
			List<GameObject> hits = new ArrayList<>();
			Class<? extends GameObject> target = aroundObject instanceof Player
					? Monster.class
					: Player.class;

			for (GameObject ob : gameObjects)
				if ( target.isInstance(ob) && ob.getDistanceFrom(aroundObject) <= range )
					hits.add( ob );

			return hits;
		}

		return new ArrayList<>();
	}

	/**
	 * Calculate the distance between two points.
	 *
	 * @param x1 The first x-position.
	 * @param y1 The first y-position.
	 * @param x2 The second x-position.
	 * @param y2 The second y-position.
	 * @return The distance.
	 */
	public static double calculateDistanceBetweenTwoPoints(double x1, double y1, double x2, double y2) {
		return Math.sqrt(
				Math.pow( Math.abs(x1 - x2), 2 ) + Math.pow( Math.abs(y1 - y2), 2 )
		);
	}
}
