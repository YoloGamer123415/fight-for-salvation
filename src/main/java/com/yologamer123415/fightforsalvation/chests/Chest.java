package com.yologamer123415.fightforsalvation.chests;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.helpers.ItemHelper;
import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.object.UsableObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;
import nl.han.ica.oopg.tile.TileMap;
import processing.core.PVector;

public class Chest extends Tile {
	private static final int ITEM_COUNT = 3;

	private final int cost;
	protected final Rarity rarity;
	
	public Chest(Sprite sprite) {
		this(sprite, 2);
	}

	public Chest(Sprite sprite, int cost) {
		super(sprite);
		this.cost = cost;
		this.rarity = Rarity.getRandomRarity();
	}

	public boolean canBeOpened() {
		return FightForSalvation.getInstance().getPlayer().getTotalEssence() >= this.cost;
	}

	public UsableObject[] open(GameObject holder) {
		if ( !this.canBeOpened() ) return null;

		final FightForSalvation instance = FightForSalvation.getInstance();

		instance.getPlayer().removeEssence(this.cost);

		UsableObject[] items = new UsableObject[ITEM_COUNT];

		for (int i = 0; i < ITEM_COUNT; i++) {
			items[i] = ItemHelper.getRandomItem(this.rarity, holder);
		}

		TileMap tileMap = instance.getTileMap();
		PVector pos = tileMap.getTileIndex(this);
		tileMap.setTile( (int) pos.x, (int) pos.y, -1 );

		return items;
	}
}
