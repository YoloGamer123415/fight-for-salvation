package com.yologamer123415.fightforsalvation.object.movables;

import com.yologamer123415.fightforsalvation.helpers.Vector;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class LightningBolt extends Movable {
	public LightningBolt(Vector path) {
		super( new Sprite("src/main/resources/usables/abilities/ranged/spark.png"), path );
	}

	@Override
	public void collidedWithGameObjects(List<GameObject> gameObjects) {

	}
}
