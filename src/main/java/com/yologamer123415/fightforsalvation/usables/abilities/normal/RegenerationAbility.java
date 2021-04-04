package com.yologamer123415.fightforsalvation.usables.abilities.normal;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.UUID;

public class RegenerationAbility extends NormalAbility implements IAlarmListener {
	private static final int HEAL_AMOUNT = 15;
	private static final int HEAL_TIMES = 2;
	private static final float TIME_PER_HEAL = 1.5f;

	private final String alarmName = UUID.randomUUID().toString();

	private final int healTimes;
	private int healedTimes = 0;

	private final Alarm alarm;

	public RegenerationAbility(GameObject holder, Rarity chestRarity) {
		super("Regeneration", new Sprite("src/main/resources/usables/abilities/normal/regeneration.png"), holder, chestRarity, 15f);

		this.healTimes = HEAL_TIMES * this.rarity.getCalculationValue();
		this.alarm = new Alarm(this.alarmName, TIME_PER_HEAL);
		this.alarm.addTarget(this);
	}

	private void heal() {
		FightForSalvation.getInstance().getPlayer().heal(HEAL_AMOUNT);
		this.healedTimes++;
		this.alarm.start();
	}

	@Override
	public void use(Vector mousePos) {
		this.heal();
	}

	@Override
	public void update() {

	}

	@Override
	public void triggerAlarm(String s) {
		if ( s.equals(this.alarmName) ) {
			if (this.healedTimes >= this.healTimes) {
				this.alarm.stop();
				this.healedTimes = 0;
			} else {
				FightForSalvation.getInstance().getPlayer().heal(HEAL_AMOUNT);
				this.healedTimes++;
				this.alarm.start();
			}
		}
	}
}
