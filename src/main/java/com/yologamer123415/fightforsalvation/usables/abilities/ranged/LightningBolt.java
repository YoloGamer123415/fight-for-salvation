package com.yologamer123415.fightforsalvation.usables.abilities.ranged;

import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.object.movables.Movable;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public class LightningBolt extends RangedAbility {
	public LightningBolt(GameObject holder, Rarity chestRarity) {
		super("LightningBolt", new Sprite("src/main/resources/usables/abilities/ranged/LightningBolt.png"), holder, chestRarity, 10f);
	}

	@Override
	public Movable getMovable(Vector mousePos) {
		return new com.yologamer123415.fightforsalvation.object.movables.LightningBolt(mousePos, this.holder);
	}

	@Override
	public void update() {

	}
}
