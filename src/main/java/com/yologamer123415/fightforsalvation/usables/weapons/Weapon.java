package com.yologamer123415.fightforsalvation.usables.weapons;

import com.yologamer123415.fightforsalvation.helpers.Cooldown;
import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.object.UsableObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public abstract class Weapon extends UsableObject {
	protected Rarity rarity;
	protected GameObject holder;

	private final Cooldown cooldown;

	public Weapon(String name, Sprite sprite, GameObject holder, float cooldownSec) {
		super(name, sprite);
		this.holder = holder;
		this.rarity = Rarity.getRandomRarity();
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
