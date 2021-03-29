package com.yologamer123415.fightforsalvation.monsters;

import com.yologamer123415.fightforsalvation.object.FlamableSpriteObject;
import com.yologamer123415.fightforsalvation.usables.weapons.Weapon;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class Monster extends FlamableSpriteObject {
	private Weapon weapon;

	public Monster(Sprite sprite) {
		super(sprite, 0, 0); //TODO Fix constructor
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> list) {

	}

	@Override
	public void update() {
		super.update();
	}
}
