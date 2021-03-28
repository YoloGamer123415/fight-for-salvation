package com.yologamer123415.fightforsalvation.chests;

import com.yologamer123415.fightforsalvation.helpers.Rarity;
import nl.han.ica.oopg.objects.Sprite;

public class Chest {
	private int cost;
	private Rarity rarity;

	public Chest(Sprite sprite) {

	}

	public boolean canBeOpened() {
		if (cost == 0) return true;
		//TODO Check if user has enough coins
		return false;
	}
}
