package com.yologamer123415.fightforsalvation.monsters;

import com.yologamer123415.fightforsalvation.usables.weapons.normal.Knife;
import nl.han.ica.oopg.objects.Sprite;

public class KnifeMonster extends Monster {
	public KnifeMonster() {
		super(new Sprite("src/main/resources/gameobjects/monsters/KnifeMonster.png"), new Knife());
	}
}
