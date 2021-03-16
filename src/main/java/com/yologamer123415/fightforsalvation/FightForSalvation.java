package com.yologamer123415.fightforsalvation;

import com.yologamer123415.fightforsalvation.api.Rarity;
import nl.han.ica.oopg.engine.GameEngine;

public class FightForSalvation extends GameEngine {
	public static void main(String[] args) {
		Rarity rar = Rarity.getRarity();
		System.out.println(rar.name());

		FightForSalvation ffs = new FightForSalvation();

		ffs.runSketch();
	}

	@Override
	public void setupGame() {
		//TODO Implement...
	}

	@Override
	public void update() {
		//TODO Implement...
	}
}
