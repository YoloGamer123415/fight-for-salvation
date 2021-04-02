package com.yologamer123415.fightforsalvation.usables.weapons.normal;

import com.yologamer123415.fightforsalvation.generators.MapGenerator;
import com.yologamer123415.fightforsalvation.helpers.LocationHelper;
import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.monsters.Monster;
import com.yologamer123415.fightforsalvation.player.Player;
import com.yologamer123415.fightforsalvation.usables.weapons.Weapon;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class Knife extends Weapon {
	private static final float COOLDOWN = 0.8F;

	private final int damage;

	public Knife(GameObject holder, Rarity chestRarity) {
		super("Knife", new Sprite("src/main/resources/usables/weapons/normal/Knife.png"), holder, chestRarity, COOLDOWN);

		this.damage = this.rarity.getCalculationValue() * 2;
	}

	@Override
	public void use(Vector mousePos) {
		List<GameObject> monstersWithinRange = LocationHelper
				.findTargetsWithinRange( MapGenerator.TILESIZE, this.holder );

		for (GameObject go : monstersWithinRange) {
			if (go instanceof Player) ( (Player) go ).damage(this.damage);
			else if (go instanceof Monster) ( (Monster) go ).damage(this.damage);
		}
	}
}
