package com.yologamer123415.fightforsalvation.object;

import com.yologamer123415.fightforsalvation.generators.MapGenerator;
import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PConstants;
import processing.core.PGraphics;

public abstract class UsableObject extends SpriteObject {
	private final String name;
	protected final Rarity rarity;
	protected GameObject holder;

	public UsableObject(String name, Sprite sprite, GameObject holder, Rarity chestRarity) {
		super(sprite);
		this.name = name;
		this.holder = holder;
		this.rarity = Rarity.getRandomRarity(chestRarity);
	}

	public String getName() {
		return name;
	}

	public Rarity getRarity() {
		return this.rarity;
	}

	public abstract void use(Vector mousePos);

	@Override
	public void draw(PGraphics g) {
		super.draw(g);

		final float strokeWidth = 2;

		g.fill(0x000000, 0);
		g.stroke( this.rarity.getColor() );
		g.strokeWeight(strokeWidth);
		g.rect(this.x, this.y, MapGenerator.TILESIZE - strokeWidth, MapGenerator.TILESIZE - strokeWidth);
	}
}
