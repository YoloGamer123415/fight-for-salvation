package com.yologamer123415.fightforsalvation.monsters;

import com.yologamer123415.fightforsalvation.usables.weapons.ranged.BowAndArrow;
import nl.han.ica.oopg.objects.Sprite;

public class BowMonster extends Monster {
	private static final int hp = 120;

	public BowMonster() {
		super(new Sprite("src/main/resources/gameobjects/monsters/BowMonster.png"), hp);
		this.weapon = new BowAndArrow(this);
	}
}
