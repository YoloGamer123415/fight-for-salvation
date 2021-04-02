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
	private static final int ITEM_COUNT = 1;

	private int cost;
	protected final Rarity rarity;

	/**
	 * Construct a new Chest with no price.
	 *
	 * @param sprite The sprite to use.
	 */
	public Chest(Sprite sprite) {
		this(sprite, 0);
	}

	/**
	 * Construct a new Chest with a price.
	 *
	 * @param sprite The sprite to use.
	 * @param cost The cost of this chest.
	 */
	public Chest(Sprite sprite, int cost) {
		super(sprite);
		this.cost = cost;
		this.rarity = Rarity.getRandomRarity();
	}

	/**
	 * Check if this chest can be opened.
	 *
	 * @return true if can be opened, false if not.
	 */
	public boolean canBeOpened() {
		return FightForSalvation.getInstance().getPlayer().getEssence() >= this.cost;
	}

	protected void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * Open this chest.
	 *
	 * @param opener The opener of this chest.
	 * @return The items in the chest.
	 */
	public UsableObject[] open(GameObject opener) {
		if ( !this.canBeOpened() ) return null;

		final FightForSalvation instance = FightForSalvation.getInstance();

		instance.getPlayer().removeEssence(this.cost);

		UsableObject[] items = new UsableObject[ITEM_COUNT];

		for (int i = 0; i < ITEM_COUNT; i++) {
			items[i] = ItemHelper.getRandomItem(this.rarity, opener);
		}

		TileMap tileMap = instance.getTileMap();
		PVector pos = tileMap.getTileIndex(this);
		tileMap.setTile( (int) pos.x, (int) pos.y, -1 );

		return items;
	}
}
