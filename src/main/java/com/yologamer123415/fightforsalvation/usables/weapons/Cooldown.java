package com.yologamer123415.fightforsalvation.usables.weapons;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;

import java.util.UUID;

public class Cooldown implements IAlarmListener {
	private final String alarmName = UUID.randomUUID().toString();
	private final Alarm alarm;

	private boolean inCooldown;

	/**
	 * Construct a new Cooldown.
	 *
	 * @param sec The seconds of the cooldown.
	 */
	public Cooldown(float sec) {
		this.alarm = new Alarm(alarmName, sec);
		this.alarm.addTarget(this);
	}

	/**
	 * Start the cooldown.
	 */
	public void start() {
		this.alarm.start();
		this.inCooldown = true;
	}

	/**
	 * Check if the cooldown is active.
	 *
	 * @return True if active, false if not.
	 */
	public boolean isInCooldown() {
		return this.inCooldown;
	}

	@Override
	public void triggerAlarm(String s) {
		if (!this.alarmName.equals(s)) return;

		this.inCooldown = false;
	}
}
