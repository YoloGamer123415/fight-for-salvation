package com.yologamer123415.fightforsalvation;

import com.yologamer123415.fightforsalvation.generators.MapGenerator;
import com.yologamer123415.fightforsalvation.inventory.Inventory;
import com.yologamer123415.fightforsalvation.player.Player;
import com.yologamer123415.fightforsalvation.usables.weapons.normal.Knife;
import com.yologamer123415.fightforsalvation.usables.weapons.ranged.BowAndArrow;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.view.View;

public class FightForSalvation extends GameEngine {
	private static FightForSalvation instance;
	public static final int SCREEN_WIDTH = MapGenerator.TILESIZE * 20;
	public static final int SCREEN_HEIGHT = MapGenerator.TILESIZE * 12;

	private int level = 0;
	private boolean isInInventory;
	private Player player;
	private Inventory inventory;

	public static void main(String[] args) {
		instance = new FightForSalvation();
		runSketch(new String[]{"Fight for Salvation"}, instance);
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

	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Override of the addGameObject()
	 * We force it to add multiple instance of the same object
	 *
	 * @param gameObject The GameObject to add
	 */
	@Override
	public void addGameObject(GameObject gameObject) {
		this.getGameObjectItems().add(gameObject);
	}

	@Override
	public void addGameObject(GameObject gameObject, float x, float y) {
		this.addGameObject(gameObject);
		gameObject.setX(x);
		gameObject.setY(y);
	}

	@Override
	public void setupGame() {
		MapGenerator.load();

		setTileMap(MapGenerator.generateTilemapFromFile(level));

		View view = new View(SCREEN_WIDTH, SCREEN_HEIGHT);
//		view.setBackground(loadImage("src/main/java/nl/han/ica/oopd/waterworld/media/background.jpg"));

		setView(view);
		size(SCREEN_WIDTH, SCREEN_HEIGHT);

		this.inventory = new Inventory(800, 400, 200, 200);
		this.inventory.show();

		int index = this.inventory.addItem( new BowAndArrow(this.player) );
		this.inventory.setSelectedWeapon(index);
	}

	@Override
	public void update() {

	}

	public void closedInventory() {

	}
}
