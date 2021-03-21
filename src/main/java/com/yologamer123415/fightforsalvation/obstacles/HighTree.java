package com.yologamer123415.fightforsalvation.obstacles;

import com.yologamer123415.fightforsalvation.object.FlamableSpriteObject;
import com.yologamer123415.fightforsalvation.object.ObjectClip;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class HighTree extends FlamableSpriteObject implements ObjectClip {
	public HighTree(Sprite sprite, int duration, int tickTime) {
		super(sprite, duration, tickTime);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> list) {

	}
}
