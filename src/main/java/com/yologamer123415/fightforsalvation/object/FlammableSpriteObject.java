package com.yologamer123415.fightforsalvation.object;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

public abstract class FlammableSpriteObject extends AnimatedSpriteObject implements IAlarmListener, ICollidableWithGameObjects {
	private final int duration;
	private final int tickTime;

	private Alarm alarm;
	private boolean shouldDoDamage = false;

	public FlammableSpriteObject(Sprite sprite, int duration, int tickTime) {
		super(sprite, 2);
		this.duration = duration;
		this.tickTime = tickTime;
	}

	public final void startBurning() {
		alarm = new Alarm(this.getClass().getName(), duration);
		alarm.addTarget(this);
		alarm.start();
	}

	public final void stopBurning() {
		alarm.stop();
	}

	@Override
	public void update() {
		shouldDoDamage = false;
	}

	private void handler() {
		shouldDoDamage = true;
	}

	public boolean shouldDoDamage() {
		return shouldDoDamage;
	}

	@Override
	public final void triggerAlarm(String s) {
		if (!s.equals(this.getClass().getName())) return;
		handler();
		alarm.start();
	}
}
