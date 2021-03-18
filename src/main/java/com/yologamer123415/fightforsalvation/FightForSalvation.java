package com.yologamer123415.fightforsalvation;

import com.yologamer123415.fightforsalvation.rarity.Rarity;
import nl.han.ica.oopg.engine.GameEngine;

public class FightForSalvation extends GameEngine {
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Rarity rar = Rarity.getRarity();
			System.out.println(rar.name());
			System.out.println("------------");
		}

//		FightForSalvation ffs = new FightForSalvation();
//
//		ffs.runSketch();
	}

	@Override
	public void setupGame() {

	}

	@Override
	public void update() {

	}
}
