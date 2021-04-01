package com.yologamer123415.fightforsalvation.object;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.UUID;

public abstract class FlammableSpriteObject extends AnimatedSpriteObject implements IAlarmListener, ICollidableWithGameObjects {
	public static final int FIREDAMAGE = 10;

	private final String burnAlarmName = UUID.randomUUID().toString();
	private final String tickTimeAlarmName = UUID.randomUUID().toString();

	private final Alarm burnAlarm;
	private final Alarm tickTimeAlarm;

	private boolean shouldDoDamage = false;
	private boolean isRunning;

	public FlammableSpriteObject(Sprite sprite, int duration, int tickTime) {
		super(sprite, 2);

		this.burnAlarm = new Alarm(burnAlarmName, duration);
		this.burnAlarm.addTarget(this);

		this.tickTimeAlarm = new Alarm(tickTimeAlarmName, tickTime);
		this.tickTimeAlarm.addTarget(this);
	}

	public final void startBurning() {
		burnAlarm.start();
		isRunning = true;

		setCurrentFrameIndex(1);
	}

	public final void stopBurning() {
		burnAlarm.stop();
		isRunning = false;

		setCurrentFrameIndex(0);
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
		if (s.equals(burnAlarmName)) {
			stopBurning();
		} else if (s.equals(tickTimeAlarmName) && isRunning) {
			handler();
			tickTimeAlarm.start();
		}
	}
}
