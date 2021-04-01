package com.yologamer123415.fightforsalvation.usables.abilities.ranged;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.object.UsableObject;
import com.yologamer123415.fightforsalvation.object.movables.Movable;
import nl.han.ica.oopg.objects.Sprite;

import java.lang.reflect.InvocationTargetException;

public abstract class RangedAbility extends UsableObject {
	public RangedAbility(String name, Sprite sprite) {
		super(name, sprite);
	}

	public abstract Class<? extends Movable> getMovable();

	@Override
	public void use(Vector mousePos) {
		FightForSalvation instance = FightForSalvation.getInstance();
		Class<? extends Movable> movableClass = this.getMovable();

		try {
			Movable movableInstance = movableClass.getDeclaredConstructor(Vector.class)
					.newInstance(mousePos);
			instance.getGameObjectItems().add(movableInstance);
			movableInstance.startMoving();
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}
