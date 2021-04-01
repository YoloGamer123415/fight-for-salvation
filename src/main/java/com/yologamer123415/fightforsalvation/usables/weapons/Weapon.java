package com.yologamer123415.fightforsalvation.usables.weapons;

import com.yologamer123415.fightforsalvation.object.UsableObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public abstract class Weapon extends UsableObject {
	protected GameObject holder;

	public Weapon(String name, Sprite sprite, GameObject holder) {
		super(name, sprite);
		this.holder = holder;
	}

	@Override
	public void update() {

	}
}
