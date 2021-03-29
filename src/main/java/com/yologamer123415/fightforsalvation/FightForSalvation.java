package com.yologamer123415.fightforsalvation;

import com.yologamer123415.fightforsalvation.generators.MapGenerator;
import com.yologamer123415.fightforsalvation.inventory.Inventory;
import com.yologamer123415.fightforsalvation.player.Player;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;

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
		MapGenerator.load();

		setTileMap(MapGenerator.generateTilemapFromFile(0));

		View view = new View(1000, 600);
//		view.setBackground(loadImage("src/main/java/nl/han/ica/oopd/waterworld/media/background.jpg"));

		setView(view);
		size(1000, 600);
	}

	@Override
	public void update() {

	}

	protected void closedInventory() {

	}
}
