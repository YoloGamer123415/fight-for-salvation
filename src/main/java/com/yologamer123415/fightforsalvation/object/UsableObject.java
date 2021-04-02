package com.yologamer123415.fightforsalvation.object;

import com.yologamer123415.fightforsalvation.generators.MapGenerator;
import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.inventory.Inventory;
import com.yologamer123415.fightforsalvation.helpers.Cooldown;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PGraphics;

public abstract class UsableObject extends SpriteObject {
	private final String name;
	protected final Rarity rarity;
	protected GameObject holder;

	private final Cooldown cooldown;

	/**
	 * Construct a new UsableObject.
	 *
	 * @param name The name of the object.
	 * @param sprite The sprite of the object.
	 * @param holder The holder of the object.
	 * @param chestRarity The rarity of the object.
	 */
	public UsableObject(String name, Sprite sprite, GameObject holder, Rarity chestRarity) {
		super(sprite);
		this.name = name;
		this.holder = holder;
		this.rarity = Rarity.getRandomRarity(chestRarity);
		this.cooldown = new Cooldown(cooldownSec);
	}

	/**
	 * Get the name of the object.
	 *
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the rarity of the object.
	 *
	 * @return The rarity.
	 */
	public Rarity getRarity() {
		return this.rarity;
	}

	/**
	 * Use this object.
	 *
	 * @param mousePos The position of the mouse.
	 */
	public abstract void use(Vector mousePos);

	public boolean isInCooldown() {
		if ( this.cooldown.isInCooldown() ) return true;
		this.cooldown.start();
		return false;
	}

	@Override
	public void draw(PGraphics g) {
		super.draw(g);

		g.fill(0x000000, 0);
		g.stroke( this.rarity.getColor() );
		g.strokeWeight(Inventory.ITEM_STROKE_WIDTH);
		g.rect(
				this.x, this.y,
				MapGenerator.TILESIZE - Inventory.ITEM_STROKE_WIDTH, MapGenerator.TILESIZE - Inventory.ITEM_STROKE_WIDTH
		);
	}
}
