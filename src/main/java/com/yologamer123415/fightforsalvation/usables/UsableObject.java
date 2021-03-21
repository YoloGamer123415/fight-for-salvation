package com.yologamer123415.fightforsalvation.usables;

import com.yologamer123415.fightforsalvation.helpers.Vector;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public abstract class UsableObject extends SpriteObject {
	public UsableObject(Sprite sprite) {
		super(sprite);
	}

	public abstract void use(Vector mousePos);
}
