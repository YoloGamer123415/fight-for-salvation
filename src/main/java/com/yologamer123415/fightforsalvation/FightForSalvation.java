package com.yologamer123415.fightforsalvation;

import com.yologamer123415.fightforsalvation.generators.MapGenerator;
import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.inventory.Inventory;
import com.yologamer123415.fightforsalvation.player.Player;
import com.yologamer123415.fightforsalvation.usables.weapons.ranged.Gun;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.userinput.IMouseInput;
import nl.han.ica.oopg.view.View;
import processing.core.PVector;

import java.util.Iterator;

public class FightForSalvation extends GameEngine {
	private static FightForSalvation instance;
	public static final int SCREEN_WIDTH = MapGenerator.TILESIZE * 20;
	public static final int SCREEN_HEIGHT = MapGenerator.TILESIZE * 12;

	private TextObject essenceText;
	private int level = 0;
	private boolean isInInventory;
	private Player player;
	private Inventory inventory;
	private int monstersAlive = 0;

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
		if (this.player == null) this.player = player;
	}

	public int getMonstersAlive() {
		return monstersAlive;
	}

	public void setMonstersAlive(int monstersAlive) {
		this.monstersAlive = monstersAlive;
	}

	public void decreaseMonstersAlive() {
		if (this.monstersAlive > 0) this.monstersAlive--;
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

	/**
	 * Override of the mousePressed()
	 * We fixed clicking on Dashboards
	 */
	@Override
	public void mousePressed() {
		super.mousePressed();

		//Implemented mouseclick for Dashboards
		PVector location = calculateRelativeMouseLocation(this.mouseX, this.mouseY);
		for ( Dashboard db : this.getDashboards() ) {
			( (IMouseInput) db ).mousePressed( (int) location.x, (int) location.y, this.mouseButton );
		}
	}

	@Override
	public void setupGame() {
		MapGenerator.load();

		this.setTileMap(MapGenerator.generateTilemapFromFile(this.level));

		View view = new View(SCREEN_WIDTH, SCREEN_HEIGHT);
		view.setBackground(loadImage("src/main/resources/background.jpg"));

		setView(view);
		size(SCREEN_WIDTH, SCREEN_HEIGHT);

		this.essenceText = new TextObject("", 16);
		this.addGameObject(this.essenceText, 0, 0);

		this.inventory = new Inventory(800, 400, SCREEN_WIDTH, SCREEN_HEIGHT);
		this.addDashboard(this.inventory);

		int index = this.inventory.addItem( new Gun(this.player, Rarity.EPIC) );
		this.inventory.setSelectedWeapon(index);
	}

	@Override
	public void update() {
		this.essenceText.setText( "Aantal essence: " + this.getPlayer().getTotalEssence() );
	}

	public void closedInventory() {
		this.level++;

		Iterator<GameObject> iter = this.getGameObjectItems().iterator();
		while (iter.hasNext()) {
			GameObject go = iter.next();
			if (go instanceof Player || go instanceof TextObject) continue;
			iter.remove();
		}

		TileMap newTileMap = MapGenerator.generateTilemapFromFile(this.level);
		this.setTileMap(newTileMap);
	}
}
