package com.yologamer123415.fightforsalvation.object.movables;

import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.object.FlammableSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class LightningBolt extends Movable {
	/**
	 * Construct a new LightningBolt.
	 *
	 * @param path The path of the bolt.
	 * @param shooter The shooter of the bolt.
	 */
	public LightningBolt(Vector path, GameObject shooter) {
		super( new Sprite("src/main/resources/usables/abilities/ranged/LightningBolt.png"), path, shooter );
	}

	@Override
	public void collidedWithGameObjects(List<GameObject> gameObjects) {
		for (GameObject object : gameObjects) {
			if (object instanceof FlammableSpriteObject) {
				((FlammableSpriteObject) object).startBurning();
			}
		}
	}
}
