package com.yologamer123415.fightforsalvation.chests;

import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.object.UsableObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;

public class Chest extends Tile {
	private int cost;
	private Rarity rarity;

	public Chest(Sprite sprite) {
		super(sprite);
		rarity = Rarity.getRandomRarity();
	}

	public boolean canBeOpened() {
		if (cost == 0) return true;
		//TODO Check if user has enough coins
		return false;
	}

	public UsableObject[] open() {
		//TODO Return items
		return new UsableObject[3];
	}
}
