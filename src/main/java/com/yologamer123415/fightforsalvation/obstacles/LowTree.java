package com.yologamer123415.fightforsalvation.obstacles;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class LowTree extends FlamableSpriteObject {
	public LowTree(Sprite sprite, int duration, int tickTime) {
		super(sprite, duration, tickTime);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> list) {

	}
}
