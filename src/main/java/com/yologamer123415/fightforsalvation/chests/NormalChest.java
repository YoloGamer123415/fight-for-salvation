package com.yologamer123415.fightforsalvation.chests;

import nl.han.ica.oopg.objects.Sprite;

public class NormalChest extends Chest {

	public static final int COST = 4;

	public NormalChest(Sprite sprite) {
		super(sprite, COST);

		//Overwrite sprite with sprite for rarity.
		this.setSprite(new Sprite("src/main/resources/tiletypes/special/NormalChest_" + this.rarity.name() + ".png"));
		this.setCost( COST * this.rarity.getCalculationValue() );
	}
}
