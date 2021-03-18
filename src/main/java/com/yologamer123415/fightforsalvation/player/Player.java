package com.yologamer123415.fightforsalvation.player;

import com.yologamer123415.fightforsalvation.obstacles.FlamableSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class Player extends FlamableSpriteObject {
	public Player(Sprite sprite, int duration, int tickTime) {
		super(sprite, duration, tickTime);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> list) {

	}
}
