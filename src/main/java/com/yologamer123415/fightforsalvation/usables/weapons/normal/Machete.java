package com.yologamer123415.fightforsalvation.usables.weapons.normal;

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

public class Machete extends Weapon {
	private static final float COOLDOWN = 1.0F;

	private final int damage;

	/**
	 * Construct a new Machete.
	 *
	 * @param holder The holder of the Machete.
	 * @param chestRarity The rarity of the Machete.
	 */
	public Machete(GameObject holder, Rarity chestRarity) {
		super("Machete", new Sprite("src/main/resources/usables/weapons/normal/machete.png"), holder, chestRarity, COOLDOWN);

		this.damage = (int) (this.rarity.getCalculationValue() * 2.2);
	}

	@Override
	public void use(Vector mousePos) {
		List<GameObject> monstersWithinRange = LocationHelper
				.findTargetsWithinRange( MapGenerator.TILESIZE, this.holder );

		for (GameObject go : monstersWithinRange) {
			if (go instanceof Damageable) {
				((Damageable) go).damage(this.damage);
			}
		}
	}
}
