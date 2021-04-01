package com.yologamer123415.fightforsalvation.object;

import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

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
}
