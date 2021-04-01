package com.yologamer123415.fightforsalvation.object.movables;

import com.yologamer123415.fightforsalvation.helpers.Vector;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class WeaknessPotion extends Potion {
	public WeaknessPotion(Vector path, GameObject shooter) {
		super( new Sprite("src/main/resources/usables/abilities/ranged/rood.png"), path, shooter );
	}

	@Override
	public void collidedWithGameObjects(List<GameObject> gameObjects) {

	}
}
