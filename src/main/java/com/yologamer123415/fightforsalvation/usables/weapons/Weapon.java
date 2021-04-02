package com.yologamer123415.fightforsalvation.usables.weapons;

import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.object.UsableObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public abstract class Weapon extends UsableObject {
	protected GameObject holder;

	private final Cooldown cooldown;

	/**
	 * Construct a new Weapon.
	 *
	 * @param name The name of the Weapon.
	 * @param sprite The sprite of the Weapon.
	 * @param holder The holder of the Weapon.
	 * @param chestRarity The rarity of the Weapon.
	 * @param cooldownSec The cooldown in seconds of the Weapon.
	 */
	public Weapon(String name, Sprite sprite, GameObject holder, Rarity chestRarity, float cooldownSec) {
		super(name, sprite, holder, chestRarity);
		this.holder = holder;
		this.cooldown = new Cooldown(cooldownSec);
	}

	/**
	 * Check if this Weapon is in cooldown, and if not, start the cooldown.
	 *
	 * @return True if in cooldown, false if not.
	 */
	public boolean isInCooldown() {
		if (this.cooldown.isInCooldown()) return true;
		this.cooldown.start();
		return false;
	}

	@Override
	public void update() {

	}
}
