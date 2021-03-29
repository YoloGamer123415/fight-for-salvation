package com.yologamer123415.fightforsalvation;

import com.yologamer123415.fightforsalvation.generators.MapGenerator;
import com.yologamer123415.fightforsalvation.inventory.Inventory;
import com.yologamer123415.fightforsalvation.player.Player;
import nl.han.ica.oopg.engine.GameEngine;

public class FightForSalvation extends GameEngine {
	private static FightForSalvation instance;

	private int level = 1;
	private boolean isInInventory;
	private Player player;
	private Inventory inventory;

	public static void main(String[] args) {
		instance = new FightForSalvation();
		runSketch(new String[]{ FightForSalvation.class.getCanonicalName() }, instance);
	}

	public static FightForSalvation getInstance() {
		return instance;
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public Player getPlayer() {
		return this.player;
	}

	@Override
	public void setupGame() {
		MapGenerator.loadTileTypes();
	}

	@Override
	public void update() {

	}

	public void closedInventory() {

	}
}
