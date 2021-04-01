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
	public static float screenToTilePixel(float pixelLocation) {
		return pixelLocation / MapGenerator.TILESIZE;
	}

	public static float tileToScreenPixel(float pixelLocation) {
		return pixelLocation * MapGenerator.TILESIZE;
	}

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

	public static double calculateDistanceBetweenTwoPoints(double x1, double y1, double x2, double y2) {
		return Math.sqrt(
				Math.pow( Math.abs(x1 - x2), 2 ) + Math.pow( Math.abs(y1 - y2), 2 )
		);
	}
}
