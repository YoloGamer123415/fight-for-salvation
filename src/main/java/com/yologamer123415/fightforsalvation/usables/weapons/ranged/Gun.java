package com.yologamer123415.fightforsalvation.usables.weapons.ranged;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.object.movables.Arrow;
import com.yologamer123415.fightforsalvation.object.movables.Bullet;
import com.yologamer123415.fightforsalvation.object.movables.Movable;
import com.yologamer123415.fightforsalvation.usables.weapons.Weapon;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class Gun extends Weapon {
	private static final float COOLDOWN = 1F;

	/**
	 * Construct a new Gun.
	 *
	 * @param holder The holder of the Gun.
	 * @param chestRarity The rarity of the Gun.
	 */
	public Gun(GameObject holder, Rarity chestRarity) {
		super( "Gun", new Sprite("src/main/resources/usables/weapons/ranged/Revolver.png"), holder, chestRarity, COOLDOWN );

		this.cooldown.setCooldown( COOLDOWN / this.rarity.getCalculationValue() );
	}

	@Override
	public void use(Vector mousePos) {
		Movable bulletMovable = new Bullet(mousePos, this.holder);
		FightForSalvation.getInstance().addGameObject(bulletMovable);
		bulletMovable.startMoving();
	}
}
