package com.yologamer123415.fightforsalvation.usables.weapons.ranged;

import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.usables.weapons.Weapon;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class Gun extends Weapon implements ICollidableWithGameObjects {
	public Gun() {
		super("Gun", new Sprite("src/main/resources/usables/weapons/ranged/Revolver.png"));
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> list) {

	}

	@Override
	public void use(Vector mousePos) {

	}
}
