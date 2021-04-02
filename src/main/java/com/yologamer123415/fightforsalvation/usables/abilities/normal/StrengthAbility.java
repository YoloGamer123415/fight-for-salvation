package com.yologamer123415.fightforsalvation.usables.abilities.normal;

import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public class StrengthAbility extends NormalAbility {
	public StrengthAbility(GameObject holder, Rarity chestRarity) {
		super("Strength", new Sprite(""), holder, chestRarity, 15f);
	}

	@Override
	public void use(Vector mousePos) {

	}

	@Override
	public void update() {

	}
}
