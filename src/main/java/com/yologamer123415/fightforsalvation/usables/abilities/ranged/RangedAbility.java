package com.yologamer123415.fightforsalvation.usables.abilities.ranged;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.object.UsableObject;
import com.yologamer123415.fightforsalvation.object.movables.Movable;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.lang.reflect.InvocationTargetException;

public abstract class RangedAbility extends UsableObject {
	public RangedAbility(String name, Sprite sprite, GameObject shooter, Rarity chestRarity, float cooldownSec) {
		super(name, sprite, shooter, chestRarity, cooldownSec);
	}

	public abstract Movable getMovable(Vector mousePos);

	@Override
	public void use(Vector mousePos) {
		Movable movableInstance = this.getMovable(mousePos);
		FightForSalvation.getInstance().getGameObjectItems().add(movableInstance);
		movableInstance.startMoving();
	}
}
