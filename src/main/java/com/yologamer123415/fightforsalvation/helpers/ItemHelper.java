package com.yologamer123415.fightforsalvation.helpers;

import com.yologamer123415.fightforsalvation.object.UsableObject;
import com.yologamer123415.fightforsalvation.usables.abilities.normal.DashAbility;
import com.yologamer123415.fightforsalvation.usables.abilities.normal.InvicibleAbility;
import com.yologamer123415.fightforsalvation.usables.abilities.normal.RegenerationAbility;
import com.yologamer123415.fightforsalvation.usables.abilities.normal.StrengthAbility;
import com.yologamer123415.fightforsalvation.usables.abilities.ranged.FirePotion;
import com.yologamer123415.fightforsalvation.usables.abilities.ranged.LightningBolt;
import com.yologamer123415.fightforsalvation.usables.abilities.ranged.PoisonPotion;
import com.yologamer123415.fightforsalvation.usables.abilities.ranged.WeaknessPotion;
import com.yologamer123415.fightforsalvation.usables.weapons.normal.Knife;
import com.yologamer123415.fightforsalvation.usables.weapons.normal.Machete;
import com.yologamer123415.fightforsalvation.usables.weapons.ranged.BowAndArrow;
import com.yologamer123415.fightforsalvation.usables.weapons.ranged.Gun;
import nl.han.ica.oopg.objects.GameObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ItemHelper {
	private static final Class<?>[] ITEMS_LIST = new Class<?>[] {
			// ranged abilities
			FirePotion.class,
			LightningBolt.class,
//			PoisonPotion.class,
//			WeaknessPotion.class,
			// normal abilities
			DashAbility.class,
//			InvicibleAbility.class,
//			RegenerationAbility.class,
//			StrengthAbility.class,
			// ranged weapons
			Gun.class,
			BowAndArrow.class,
//			 normal weapons
			Knife.class,
			Machete.class,
	};

	/**
	 * Get a random item with COMMON rarity.
	 *
	 * @param holder The holder of the item.
	 * @return The UsableObject.
	 */
	public static UsableObject getRandomItem(GameObject holder) {
		return getRandomItem(Rarity.COMMON, holder);
	}

	/**
	 * Get a random item with a from rarity.
	 *
	 * @param from The from rarity.
	 * @param holder The holder of the item.
	 * @return The UsableObject.
	 */
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

	/**
	 * Clean a list, by removing all the null entries.
	 * A {@link LinkedList} sometimes leaves null values at remove.
	 *
	 * @param toClean The list to clean.
	 */
	public static void cleanList(List<?> toClean) {
		toClean.removeAll( Collections.singleton(null) );
	}
}
