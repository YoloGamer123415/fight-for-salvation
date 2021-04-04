package com.yologamer123415.fightforsalvation.usables.abilities.normal;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.UUID;

public class StrengthAbility extends NormalAbility implements IAlarmListener {
	private static final int DURATION = 5;

	private final String alarmName = UUID.randomUUID().toString();
	private final Alarm alarm;

	public StrengthAbility(GameObject holder, Rarity chestRarity) {
		super("Strength", new Sprite("src/main/resources/usables/abilities/normal/strength.png"), holder, chestRarity, 15f);

		this.alarm = new Alarm( this.alarmName, DURATION * this.rarity.getCalculationValue() );
		this.alarm.addTarget(this);
	}

	@Override
	public void use(Vector mousePos) {
		this.alarm.start();
		FightForSalvation.getInstance().getPlayer().setHasStrength(true);
	}

	@Override
	public void update() {

	}

	@Override
	public void triggerAlarm(String s) {
		if ( s.equals(this.alarmName) ) {
			FightForSalvation.getInstance().getPlayer().setHasStrength(false);
		}
	}
}
