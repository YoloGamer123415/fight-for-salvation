package com.yologamer123415.fightforsalvation.object.movables;

import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.monsters.Monster;
import com.yologamer123415.fightforsalvation.object.Damageable;
import com.yologamer123415.fightforsalvation.player.Player;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class Bullet extends Movable {
	private static final int HP = 10;

	/**
	 * Construct a new Bullet.
	 *
	 * @param path The path of the bullet.
	 * @param shooter The shooter of this bullet (NEVER the gun).
	 */
	public Bullet(Vector path, GameObject shooter) {
		super( new Sprite("src/main/resources/usables/weapons/ranged/bullet.png"), path, shooter );
	}

	@Override
	public void collidedWithGameObjects(List<GameObject> gameObjects) {
		for (GameObject go : gameObjects) {
			if (go instanceof Damageable) {
				((Damageable) go).damage(HP);
			}
		}
	}
}