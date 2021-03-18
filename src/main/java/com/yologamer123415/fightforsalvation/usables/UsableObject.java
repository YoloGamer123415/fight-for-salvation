package com.yologamer123415.fightforsalvation.usables;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PVector;

public abstract class UsableObject extends SpriteObject {
	public UsableObject(Sprite sprite) {
		super(sprite);
	}

	public abstract void use(PVector mousePos);
}
