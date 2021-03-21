package com.yologamer123415.fightforsalvation.usables.weapons.ranged;

import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.usables.weapons.Weapon;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class BowAndArrow extends Weapon implements ICollidableWithGameObjects {
	public BowAndArrow(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void use(Vector mousePos) {

	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> list) {

	}
}
