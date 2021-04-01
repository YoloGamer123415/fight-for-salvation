package com.yologamer123415.fightforsalvation.object.movables;

import com.yologamer123415.fightforsalvation.helpers.Vector;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public abstract class Potion extends Movable {
	public Potion(Sprite sprite, Vector path, GameObject shooter) {
		super(sprite, path, shooter);
	}
}
