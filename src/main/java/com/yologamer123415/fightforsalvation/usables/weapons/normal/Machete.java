package com.yologamer123415.fightforsalvation.usables.weapons.normal;

import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.usables.weapons.Weapon;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public class Machete extends Weapon {
	public Machete(GameObject holder, Rarity chestRarity) {
		super("Machete", new Sprite("src/main/resources/usables/weapons/normal/machete.png"), holder, chestRarity, 1.0F);
	}

	@Override
	public void use(Vector mousePos) {

	}
}
