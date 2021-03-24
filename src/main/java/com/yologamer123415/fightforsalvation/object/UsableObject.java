package com.yologamer123415.fightforsalvation.object;

import com.yologamer123415.fightforsalvation.helpers.Vector;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public abstract class UsableObject extends SpriteObject {
	private final String name;

	public UsableObject(String name, Sprite sprite) {
		super(sprite);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract void use(Vector mousePos);
}
