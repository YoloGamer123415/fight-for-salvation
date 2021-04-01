package com.yologamer123415.fightforsalvation.usables.abilities.normal;

import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.object.UsableObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public abstract class NormalAbility extends UsableObject {
	public NormalAbility(String name, Sprite sprite, GameObject holder, Rarity chestRarity) {
		super(name, sprite, holder, chestRarity);
	}
}
