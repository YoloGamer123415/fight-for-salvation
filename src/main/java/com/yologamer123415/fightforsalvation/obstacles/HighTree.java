package com.yologamer123415.fightforsalvation.obstacles;

import com.yologamer123415.fightforsalvation.object.FlammableSpriteObject;
import com.yologamer123415.fightforsalvation.object.ObjectClip;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class HighTree extends FlammableSpriteObject implements ObjectClip {
	public HighTree(Sprite sprite) {
		super(sprite, 0, 0);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> list) {

	}
}
