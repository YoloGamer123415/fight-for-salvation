package com.yologamer123415.fightforsalvation.object.movables;

import com.yologamer123415.fightforsalvation.helpers.Vector;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class PoisonPotion extends Potion {
	/**
	 * Construct a new Poison potion.
	 *
	 * @param path The path of the Poison potion.
	 * @param shooter The shooter of the Poison potion.
	 */
	public PoisonPotion(Vector path, GameObject shooter) {
		super( new Sprite("src/main/resources/usables/abilities/ranged/poison_potion.png"), path, shooter );
	}

	@Override
	public void collidedWithGameObjects(List<GameObject> gameObjects) {

	}
}
