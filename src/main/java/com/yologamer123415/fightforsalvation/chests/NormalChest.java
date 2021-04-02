package com.yologamer123415.fightforsalvation.chests;

import nl.han.ica.oopg.objects.Sprite;

public class NormalChest extends Chest {
	public NormalChest(Sprite sprite) {
		super(sprite, 2); //TODO Calculate cost based on rarity.

		//Overwrite sprite with sprite for rarity.
		this.setSprite(new Sprite("src/main/resources/tiletypes/special/NormalChest_" + rarity.name() + ".png"));
	}
}
