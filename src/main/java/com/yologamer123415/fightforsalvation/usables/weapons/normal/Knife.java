package com.yologamer123415.fightforsalvation.usables.weapons.normal;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.generators.MapGenerator;
import com.yologamer123415.fightforsalvation.helpers.LocationHelper;
import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.monsters.Monster;
import com.yologamer123415.fightforsalvation.object.Damageable;
import com.yologamer123415.fightforsalvation.player.Player;
import com.yologamer123415.fightforsalvation.usables.weapons.Weapon;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class Knife extends Weapon {
	private static final float COOLDOWN = 0.8F;

	private final int damage;

	/**
	 * Construct a new Knife.
	 *
	 * @param holder The holder of the Knife.
	 * @param chestRarity The rarity of the Knife.
	 */
	public Knife(GameObject holder, Rarity chestRarity) {
		super("Knife", new Sprite("src/main/resources/usables/weapons/normal/Knife.png"), holder, chestRarity, COOLDOWN);

		this.damage = this.rarity.getCalculationValue() * 2;
	}

	@Override
	public void use(Vector mousePos) {
		final int damage = getCorrectedDamage(this.holder, this.damage);
		List<GameObject> monstersWithinRange = LocationHelper
				.findTargetsWithinRange( MapGenerator.TILESIZE, this.holder );

		for (GameObject go : monstersWithinRange) {
			if (go instanceof Damageable) {
				((Damageable) go).damage(damage);
			}
		}
	}
}
