package com.yologamer123415.fightforsalvation.usables.abilities.ranged;

import com.yologamer123415.fightforsalvation.object.movables.Movable;
import nl.han.ica.oopg.objects.Sprite;

public class WeaknessPotion extends Potion {
	public WeaknessPotion() {
		super("Weakness Potion", new Sprite("src/main/resources/usables/abilities/ranged/paars.png"));
	}

	@Override
	public void update() {

	}

	@Override
	public Class<? extends Movable> getMovable() {
		return com.yologamer123415.fightforsalvation.object.movables.WeaknessPotion.class;
	}
}
