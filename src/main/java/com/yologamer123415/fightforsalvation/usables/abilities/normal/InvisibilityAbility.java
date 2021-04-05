package com.yologamer123415.fightforsalvation.usables.abilities.normal;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.UUID;

public class InvisibilityAbility extends NormalAbility implements IAlarmListener {
	private static final float DURATION = 2.5f;

	private final String alarmName = UUID.randomUUID().toString();
	private final Alarm alarm;

	public InvisibilityAbility(GameObject holder, Rarity chestRarity) {
		super("Invisibility", new Sprite("src/main/resources/usables/abilities/normal/invisibility.png"), holder, chestRarity, 15f);

		this.alarm = new Alarm( this.alarmName, DURATION * this.rarity.getCalculationValue() );
		this.alarm.addTarget(this);
	}

	@Override
	public void use(Vector mousePos) {
		this.alarm.start();
		FightForSalvation.getInstance().getPlayer().setIsInvisible(true);
	}

	@Override
	public void update() {

	}

	@Override
	public void triggerAlarm(String s) {
		if ( s.equals(this.alarmName) ) {
			FightForSalvation.getInstance().getPlayer().setIsInvisible(false);
		}
	}
}
