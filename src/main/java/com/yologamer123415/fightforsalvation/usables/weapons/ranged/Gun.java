package com.yologamer123415.fightforsalvation.usables.weapons.ranged;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.helpers.Cooldown;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.object.movables.Arrow;
import com.yologamer123415.fightforsalvation.object.movables.Movable;
import com.yologamer123415.fightforsalvation.usables.weapons.Weapon;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class Gun extends Weapon implements ICollidableWithGameObjects {
	private static final float COOLDOWN = 0.5F;
	private final Cooldown cooldown;

	public Gun(GameObject holder) {
		super("Gun", new Sprite("src/main/resources/usables/weapons/ranged/Revolver.png"), holder);
		this.cooldown = new Cooldown(COOLDOWN);
	}

	@Override
	public void use(Vector mousePos) {
		if (this.cooldown.isInCooldown()) return;

		Movable arrowMovable = new Arrow(mousePos, this.holder);
		FightForSalvation.getInstance().getGameObjectItems().add(arrowMovable);
		arrowMovable.startMoving();

		this.cooldown.start();
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> list) {

	}
}
