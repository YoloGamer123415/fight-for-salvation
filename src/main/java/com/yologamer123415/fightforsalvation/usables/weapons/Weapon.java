package com.yologamer123415.fightforsalvation.usables.weapons;

import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.object.UsableObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public abstract class Weapon extends UsableObject {
	protected GameObject holder;

	private final Cooldown cooldown;

	public Weapon(String name, Sprite sprite, GameObject holder, Rarity chestRarity, float cooldownSec) {
		super(name, sprite, holder, chestRarity);
		this.holder = holder;
		this.cooldown = new Cooldown(cooldownSec);
	}

	public boolean isInCooldown() {
		if (this.cooldown.isInCooldown()) return true;
		this.cooldown.start();
		return false;
	}

	@Override
	public void update() {

	}
}
