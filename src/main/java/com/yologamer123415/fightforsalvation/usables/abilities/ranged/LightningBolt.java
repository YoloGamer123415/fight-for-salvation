package com.yologamer123415.fightforsalvation.usables.abilities.ranged;

import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.object.movables.Movable;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public class LightningBolt extends RangedAbility {
	public LightningBolt(GameObject holder) {
		super("LightningBolt", new Sprite("src/main/resources/usables/abilities/ranged/spark.png"), holder);
	}

	@Override
	public Movable getMovable(Vector mousePos) {
		return new com.yologamer123415.fightforsalvation.object.movables.LightningBolt(mousePos, holder);
	}

	@Override
	public void use(Vector mousePos) {

	}

	@Override
	public void update() {

	}
}
