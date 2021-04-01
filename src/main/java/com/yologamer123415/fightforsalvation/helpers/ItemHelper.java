package com.yologamer123415.fightforsalvation.helpers;

import com.yologamer123415.fightforsalvation.object.UsableObject;
import com.yologamer123415.fightforsalvation.usables.weapons.normal.Knife;
import com.yologamer123415.fightforsalvation.usables.weapons.normal.Machete;
import com.yologamer123415.fightforsalvation.usables.weapons.ranged.BowAndArrow;
import com.yologamer123415.fightforsalvation.usables.weapons.ranged.Gun;
import nl.han.ica.oopg.objects.GameObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class ItemHelper {
	private static final Class<?>[] ITEMS_LIST = new Class<?>[] {
			Gun.class,
			BowAndArrow.class,

			Knife.class,
			Machete.class,
	};

	public static UsableObject getRandomItem(GameObject holder) {
		return getRandomItem(Rarity.COMMON, holder);
	}

	public static UsableObject getRandomItem(Rarity from, GameObject holder) {
		try {
			int randomNum = new Random().nextInt( ITEMS_LIST.length );

			return (UsableObject) ITEMS_LIST[randomNum].getDeclaredConstructor(GameObject.class, Rarity.class)
					.newInstance(holder, from);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();

			return null;
		}
	}
}
