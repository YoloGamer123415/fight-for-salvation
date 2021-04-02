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

public class BowAndArrow extends Weapon {
	private static final float COOLDOWN = 1.2F;

	/**
	 * Construct a new BowAndArrow.
	 *
	 * @param holder The holder of the BowAndArrow.
	 * @param chestRarity The rarity of the BowAndArrow.
	 */
	public BowAndArrow(GameObject holder, Rarity chestRarity) {
		super("Bow and Arrow", new Sprite("src/main/resources/usables/weapons/ranged/crossbow_01.png"), holder, chestRarity, COOLDOWN);
	}

	@Override
	public void use(Vector mousePos) {
		if (this.isInCooldown()) return;

		Movable arrowMovable = new Arrow(mousePos, this.holder);
		FightForSalvation.getInstance().addGameObject(arrowMovable);
		arrowMovable.startMoving();
	}
}
