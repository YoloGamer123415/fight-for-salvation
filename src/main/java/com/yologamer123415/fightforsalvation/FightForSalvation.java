package com.yologamer123415.fightforsalvation;

import com.yologamer123415.fightforsalvation.generators.MapGenerator;
import com.yologamer123415.fightforsalvation.player.Player;
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

	public static final int FONT_SIZE = 16;
	public static final int SCREEN_WIDTH = MapGenerator.TILESIZE * 20;
	public static final int SCREEN_HEIGHT = MapGenerator.TILESIZE * 12;

	private TextObject essenceText;
	private TextObject hpText;
	private int level = 0;
	private Player player;
	private int monstersAlive = 0;

	public static void main(String[] args) {
		instance = new FightForSalvation();
		runSketch(new String[]{"Fight for Salvation"}, instance);
	}

	public static FightForSalvation getInstance() {
		return instance;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		if (this.player == null) this.player = player;
	}

	public int getMonstersAlive() {
		return this.monstersAlive;
	}

	public void setMonstersAlive(int monstersAlive) {
		this.monstersAlive = monstersAlive;
	}

	public void decreaseMonstersAlive() {
		if (this.monstersAlive > 0) this.monstersAlive--;
	}

	private void setupView() {
		View view = new View(SCREEN_WIDTH, SCREEN_HEIGHT);
		view.setBackground( this.loadImage("src/main/resources/background.jpg") );

		this.setView(view);
		this.size(SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	private void setupTextObjects() {
		this.essenceText = new TextObject("Aantal essence: X", FONT_SIZE);
		this.addGameObject(this.essenceText, 0, 0);
		this.hpText = new TextObject("HP: XXX", FONT_SIZE);
		this.addGameObject( this.hpText, 0, (float) (SCREEN_HEIGHT - FONT_SIZE * 1.25) );
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
		PVector location = this.calculateRelativeMouseLocation(this.mouseX, this.mouseY);
		for ( Dashboard db : this.getDashboards() ) {
			( (IMouseInput) db ).mousePressed( (int) location.x, (int) location.y, this.mouseButton );
		}
	}

	@Override
	public void setupGame() {
		MapGenerator.load();

		this.setTileMap(MapGenerator.generateTilemapFromFile(this.level));
		this.setupView();
		this.setupTextObjects();
	}

	@Override
	public void update() {
		this.essenceText.setText( "Aantal essence: " + this.getPlayer().getTotalEssence() );
		this.hpText.setText( "HP: " + this.getPlayer().getHp() );
	}

	public void closedInventory() {
		this.level++;

		Iterator<GameObject> iter = this.getGameObjectItems().iterator();
		while ( iter.hasNext() ) {
			GameObject go = iter.next();
			if (go instanceof Player || go instanceof TextObject) continue;
			iter.remove();
		}

		TileMap newTileMap = MapGenerator.generateTilemapFromFile(this.level);
		this.setTileMap(newTileMap);
	}
}
