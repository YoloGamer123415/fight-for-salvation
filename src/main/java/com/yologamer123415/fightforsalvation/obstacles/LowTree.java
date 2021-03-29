package com.yologamer123415.fightforsalvation.obstacles;

import com.yologamer123415.fightforsalvation.object.FlammableSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class LowTree extends FlammableSpriteObject {
	public LowTree(Sprite sprite) {
		super(sprite, 0, 0);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> list) {

	}
}
