package com.yologamer123415.fightforsalvation.usables.abilities.normal;

import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public class RegenerationAbility extends NormalAbility {
	public RegenerationAbility(GameObject holder, Rarity chestRarity) {
		super("Regeneration", new Sprite(""), holder, chestRarity);
	}

	@Override
	public void use(Vector mousePos) {

	}

	@Override
	public void update() {

	}
}
