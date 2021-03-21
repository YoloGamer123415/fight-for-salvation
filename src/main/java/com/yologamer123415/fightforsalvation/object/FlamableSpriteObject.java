package com.yologamer123415.fightforsalvation.object;

import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

public abstract class FlamableSpriteObject extends AnimatedSpriteObject implements IAlarmListener, ICollidableWithGameObjects {
	private final int tickTime;
	private boolean shouldDoDamage = false;

	public FlamableSpriteObject(Sprite sprite, int duration, int tickTime) {
		super(sprite, 2);
		this.tickTime = tickTime;
	}

	public final void startBurning(int duration) {

	}

	public final void stopBurning() {

	}

	@Override
	public void update() {
		shouldDoDamage = false;
	}

	private void handler() {

	}

	public boolean shouldDoDamage() {
		return shouldDoDamage;
	}

	@Override
	public final void triggerAlarm(String s) {

	}
}
