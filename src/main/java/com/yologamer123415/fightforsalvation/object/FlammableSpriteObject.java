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

	/**
	 * Construct a new Flammable SpriteObject.
	 * The left part of the image is default, the right part of the image is fire.
	 *
	 * @param sprite The sprite to use.
	 * @param duration The duration of the fire.
	 * @param tickTime The ticktime of the fire.
	 *                    Firstly damage is off, after tickTime damage is on, then off, ...
	 */
	public FlammableSpriteObject(Sprite sprite, int duration, int tickTime) {
		super(sprite, 2);

		this.burnAlarm = new Alarm(burnAlarmName, duration);
		this.burnAlarm.addTarget(this);

		this.tickTimeAlarm = new Alarm(tickTimeAlarmName, tickTime);
		this.tickTimeAlarm.addTarget(this);
	}

	/**
	 * Start burning of the object.
	 */
	public final void startBurning() {
		burnAlarm.start();
		isRunning = true;

		setCurrentFrameIndex(1);
	}

	/**
	 * Forcly stop burning of the object.
	 */
	public final void stopBurning() {
		burnAlarm.stop();
		isRunning = false;

		setCurrentFrameIndex(0);
	}

	@Override
	public void update() {
		shouldDoDamage = false;
	}

	/**
	 * Called after ticktime.
	 */
	private void handler() {
		shouldDoDamage = true;
	}

	/**
	 * Check if the object should take damage.
	 *
	 * @return True if it should, false if not.
	 */
	public boolean shouldDoDamage() {
		return shouldDoDamage;
	}

	public boolean isRunning() {
		return isRunning;
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
