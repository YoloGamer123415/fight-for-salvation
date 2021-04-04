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
	private Alarm tickTimeAlarm;
	private final int tickTime;

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

		this.tickTime = tickTime;

		this.burnAlarm = new Alarm(this.burnAlarmName, duration);
		this.burnAlarm.addTarget(this);

		this.tickTimeAlarm = new Alarm(this.tickTimeAlarmName, tickTime);
		this.tickTimeAlarm.addTarget(this);
	}

	/**
	 * Start burning of the object.
	 */
	public final void startBurning() {
		this.burnAlarm.start();
		this.tickTimeAlarm.start();
		this.isRunning = true;

		this.setCurrentFrameIndex(1);
	}

	/**
	 * Forcibly stop burning of the object.
	 */
	public final void stopBurning() {
		this.burnAlarm.stop();
		this.tickTimeAlarm.stop();
		this.isRunning = false;

		this.setCurrentFrameIndex(0);
	}

	@Override
	public void update() {
		this.shouldDoDamage = false;
	}

	/**
	 * Called after ticktime.
	 */
	private void handler() {
		this.shouldDoDamage = true;
	}

	/**
	 * Check if the object should take damage.
	 *
	 * @return True if it should, false if not.
	 */
	public boolean shouldDoDamage() {
		return this.shouldDoDamage;
	}

	public boolean isRunning() {
		return this.isRunning;
	}

	@Override
	public final void triggerAlarm(String s) {
		if ( s.equals(this.burnAlarmName) ) {
			this.stopBurning();
		} else if ( s.equals(this.tickTimeAlarmName) && this.isRunning ) {
			this.handler();

			this.tickTimeAlarm = new Alarm(this.tickTimeAlarmName, this.tickTime);
			this.tickTimeAlarm.addTarget(this);
			this.tickTimeAlarm.start();
		}
	}
}
