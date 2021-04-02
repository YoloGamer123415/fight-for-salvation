package com.yologamer123415.fightforsalvation.generators;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.lang.reflect.InvocationTargetException;

public class GameObjectType {
	private final Class<? extends GameObject> type;
	private final Sprite sprite;

	/**
	 * Construct a new GameObjectType.
	 *
	 * @param type The class of the GameObject.
	 * @param sprite The sprite of the GameObject.
	 */
	public GameObjectType(Class<? extends GameObject> type, Sprite sprite) {
		this.type = type;
		this.sprite = sprite;
	}

	/**
	 * Construct a new GameObject by it's type.
	 *
	 * @return The GameObject to generate.
	 */
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
