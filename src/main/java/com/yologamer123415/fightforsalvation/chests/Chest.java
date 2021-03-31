package com.yologamer123415.fightforsalvation.chests;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.object.UsableObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;

public class Chest extends Tile {
	private final int cost;
	private final Rarity rarity;
	
	public Chest(Sprite sprite) {
		this(sprite, 0);
	}

	public Chest(Sprite sprite, int cost) {
		super(sprite);
		this.cost = cost;
		rarity = Rarity.getRandomRarity();
	}

	public boolean canBeOpened() {
		return cost == 0 || FightForSalvation.getInstance().getPlayer().getTotalEssence() >= cost;
	}

	public UsableObject[] open() {
		//TODO Return items
		return new UsableObject[3];
	}
}
