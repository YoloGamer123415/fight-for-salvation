package com.yologamer123415.fightforsalvation.chests;

import nl.han.ica.oopg.objects.Sprite;

public class EndChest extends Chest {
	public EndChest(Sprite sprite) {
		super(sprite);
	}

	@Override
	public boolean canBeOpened() {
		return super.canBeOpened() && false; //TODO Implement (false should be replaced by level ended)
	}
}
