package com.yologamer123415.fightforsalvation.usables.weapons;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;

import java.util.UUID;

public class Cooldown implements IAlarmListener {
	private final String alarmName = UUID.randomUUID().toString();
	private final Alarm alarm;

	private boolean inCooldown;

	public Cooldown(float sec) {
		this.alarm = new Alarm(alarmName, sec);
		this.alarm.addTarget(this);
	}

	public void start() {
		this.alarm.start();
		this.inCooldown = true;
	}

	@Override
	public void triggerAlarm(String s) {
		if (!this.alarmName.equals(s)) return;

		this.inCooldown = false;
	}

	public boolean isInCooldown() {
		return inCooldown;
	}
}
