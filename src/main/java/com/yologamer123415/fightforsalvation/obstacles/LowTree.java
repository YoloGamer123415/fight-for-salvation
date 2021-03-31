package com.yologamer123415.fightforsalvation.obstacles;

import com.yologamer123415.fightforsalvation.object.FlammableSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class LowTree extends FlammableSpriteObject {
	/**
	 * Construct a new LowTree object.
	 * This method will be called from the MapGenerator!
	 *
	 * @param sprite The sprite to use.
	 */
	public LowTree(Sprite sprite) {
		super(sprite, 6, 2);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> list) {

	}
}
