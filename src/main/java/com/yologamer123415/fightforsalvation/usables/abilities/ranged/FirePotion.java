package com.yologamer123415.fightforsalvation.usables.abilities.ranged;

import com.yologamer123415.fightforsalvation.object.movables.Movable;
import nl.han.ica.oopg.objects.Sprite;

public class FirePotion extends Potion {
	public FirePotion() {
		super("Fire Potion", new Sprite("src/main/resources/usables/abilities/ranged/rood.png"));
	}

	@Override
	public Class<? extends Movable> getMovable() {
		return com.yologamer123415.fightforsalvation.object.movables.FirePotion.class;
	}

	@Override
	public void update() {

	}
}
