package com.yologamer123415.fightforsalvation.generators;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.lang.reflect.InvocationTargetException;

public class GameObjectType {
	private final Class<? extends GameObject> type;
	private final Sprite sprite;

	public GameObjectType(Class<? extends GameObject> type, Sprite sprite) {
		this.type = type;
		this.sprite = sprite;
	}

	public GameObject getGameObject() {
		GameObject object;
		try {
			object = type.getDeclaredConstructor(Sprite.class).newInstance(sprite);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
			return null;
		}
		return object;
	}
}
