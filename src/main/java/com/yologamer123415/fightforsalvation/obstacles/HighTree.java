package com.yologamer123415.fightforsalvation.obstacles;

import com.yologamer123415.fightforsalvation.object.FlammableSpriteObject;
import com.yologamer123415.fightforsalvation.object.ObjectClip;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class HighTree extends FlammableSpriteObject implements ObjectClip {
	/**
	 * Construct a new HighTree object.
	 * This method will be called from the MapGenerator!
	 *
	 * @param sprite The sprite to use.
	 */
	public HighTree(Sprite sprite) {
		super(sprite, 15, 5);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> list) {

	}
}
