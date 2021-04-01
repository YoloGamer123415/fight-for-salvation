package com.yologamer123415.fightforsalvation.usables.abilities.ranged;

import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.object.movables.Movable;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public class PoisonPotion extends Potion {
	public PoisonPotion(GameObject holder) {
		super("Poison Potion", new Sprite("src/main/resources/usables/abilities/ranged/groen.png"), holder);
	}

	@Override
	public void update() {

	}

	@Override
	public Movable getMovable(Vector mousePos) {
		return new com.yologamer123415.fightforsalvation.object.movables.PoisonPotion(mousePos, holder);
	}
}
