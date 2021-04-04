package com.yologamer123415.fightforsalvation.usables.weapons;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.object.UsableObject;
import com.yologamer123415.fightforsalvation.player.Player;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public abstract class Weapon extends UsableObject {
	protected GameObject holder;

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
		super(name, sprite, holder, chestRarity, cooldownSec);
		this.holder = holder;
	}

	public static int getCorrectedDamage(GameObject holder, int baseDamage) {
		final Player player = FightForSalvation.getInstance().getPlayer();
		return holder.equals(player)
				? baseDamage * player.getDamageModifier()
				: baseDamage;
	}

	@Override
	public void update() {}
}
