package com.yologamer123415.fightforsalvation.usables.abilities.ranged;

import com.yologamer123415.fightforsalvation.helpers.Rarity;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public abstract class Potion extends RangedAbility {
	public Potion(String name, Sprite sprite, GameObject holder, Rarity chestRarity) {
		super(name, sprite, holder, chestRarity);
	}
}
