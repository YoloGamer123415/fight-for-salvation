package com.yologamer123415.fightforsalvation.object.movables;

import com.yologamer123415.fightforsalvation.helpers.Vector;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class Arrow extends Movable {
	public Arrow(Vector path) {
		super( new Sprite("src/main/resources/usables/weapons/ranged/arrow.png"), path );
	}

	@Override
	public void collidedWithGameObjects(List<GameObject> gameObjects) {

	}
}
