package com.yologamer123415.fightforsalvation.usables.abilities.ranged;

import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.object.movables.Movable;
import nl.han.ica.oopg.objects.Sprite;

public class LightningBolt extends RangedAbility {
	public LightningBolt() {
		super("LightningBolt", new Sprite("src/main/resources/usables/abilities/ranged/spark.png"));
	}

	@Override
	public Class<? extends Movable> getMovable() {
		return com.yologamer123415.fightforsalvation.object.movables.LightningBolt.class;
	}

	@Override
	public void use(Vector mousePos) {

	}

	@Override
	public void update() {

	}
}
