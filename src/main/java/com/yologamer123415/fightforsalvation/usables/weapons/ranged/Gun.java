package com.yologamer123415.fightforsalvation.usables.weapons.ranged;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.object.movables.Arrow;
import com.yologamer123415.fightforsalvation.object.movables.Movable;
import com.yologamer123415.fightforsalvation.usables.weapons.Weapon;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class Gun extends Weapon implements ICollidableWithGameObjects {
	public Gun(GameObject holder, Rarity chestRarity) {
		super("Gun", new Sprite("src/main/resources/usables/weapons/ranged/Revolver.png"), holder, chestRarity, 0.5F);
	}

	@Override
	public void use(Vector mousePos) {
		if (isInCooldown()) return;

		Movable arrowMovable = new Arrow(mousePos, this.holder);
		FightForSalvation.getInstance().addGameObject(arrowMovable);
		arrowMovable.startMoving();
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> list) {

	}
}
