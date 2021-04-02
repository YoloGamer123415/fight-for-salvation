package com.yologamer123415.fightforsalvation.inventory;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.generators.MapGenerator;
import com.yologamer123415.fightforsalvation.helpers.ItemHelper;
import com.yologamer123415.fightforsalvation.helpers.LocationHelper;
import com.yologamer123415.fightforsalvation.object.UsableObject;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PGraphics;

import java.awt.*;
import java.util.LinkedList;

public class Inventory extends Dashboard {
	private static final int ITEM_Y_OFFSET = MapGenerator.TILESIZE;
	private static final int MAX_ITEMS_PER_ROW = (FightForSalvation.SCREEN_WIDTH - 2 * MapGenerator.TILESIZE) / MapGenerator.TILESIZE;
	public static final int ITEM_STROKE_WIDTH = 2;

	private static final int DONE_BUTTON_TOP_LEFT_X = 728;
	private static final int DONE_BUTTON_TOP_LEFT_Y = 523;
	private static final int DONE_BUTTON_WIDTH = 232;
	private static final int DONE_BUTTON_HEIGHT = 53;

	public final LinkedList<UsableObject> items = new LinkedList<>();
	private UsableObject selectedNormalAbility = null;
	private UsableObject selectedRangedAbility = null;
	private UsableObject selectedWeapon = null;

	private int selectedMoveItem = -1;

	private boolean isFirstMousePress = true;

	/**
	 * Construct a new Inventory.
	 */
	public Inventory() {
		super(800, 400, FightForSalvation.SCREEN_WIDTH, FightForSalvation.SCREEN_HEIGHT);

		this.setVisible(false);
		this.setX(0);
		this.setY(0);
		this.setZ(100);
		this.setBackgroundImage( new Sprite("src/main/resources/background_inventory.jpg") );
	}

	/**
	 * Get the selected normal ability.
	 *
	 * @return The selected normal ability.
	 */
	public UsableObject getSelectedNormalAbility() {
		return this.selectedNormalAbility;
	}

	/**
	 * Set the selected normal ability by it's index.
	 *
	 * @param selectedNormalAbility The index of the item to select.
	 */
	public void setSelectedNormalAbility(int selectedNormalAbility) {
		if ( selectedNormalAbility >= this.items.size() ) return;

		this.items.add(this.selectedNormalAbility);
		this.selectedNormalAbility = this.items.remove(selectedNormalAbility);
		ItemHelper.cleanList(this.items);
	}

	/**
	 * Get the selected ranged ability.
	 *
	 * @return The selected ranged ability.
	 */
	public UsableObject getSelectedRangedAbility() {
		return this.selectedRangedAbility;
	}

	/**
	 * Set the selected ranged ability by it's index.
	 *
	 * @param selectedRangedAbility The index of the item to select.
	 */
	public void setSelectedRangedAbility(int selectedRangedAbility) {
		if ( selectedRangedAbility >= this.items.size() ) return;

		this.items.add(this.selectedRangedAbility);
		this.selectedRangedAbility = this.items.remove(selectedRangedAbility);
		ItemHelper.cleanList(this.items);
	}

	/**
	 * Get the selected weapon.
	 *
	 * @return The selected ranged ability.
	 */
	public UsableObject getSelectedWeapon() {
		return this.selectedWeapon;
	}

	/**
	 * Set the selected weapon by it's index.
	 *
	 * @param selectedWeapon The index of the weapon to select.
	 */
	public void setSelectedWeapon(int selectedWeapon) {
		if ( selectedWeapon >= this.items.size() ) return;

		this.items.add(this.selectedWeapon);
		this.selectedWeapon = this.items.remove(selectedWeapon);
		ItemHelper.cleanList(this.items);
	}

	/**
	 * Add an item to the inventory.
	 *
	 * @param item The item to add.
	 * @return The index of the added item.
	 */
	public int addItem(UsableObject item) {
		this.items.add(item);

		if ( this.isVisible() ) this.redrawShownItems();

		return this.items.indexOf(item);
	}

	/**
	 * Reset the shown items, by removing them from the screen.
	 */
	private void resetShownItems() {
		this.deleteAllGameObjectsOfType(UsableObject.class);
	}

	/**
	 * Place the items on the screen.
	 */
	private void placeItems() {
		for (int i = 0; i < this.items.size(); i++) {
			int y = (int) LocationHelper.tileToScreenPixel( (float) Math.floor( (float) i / MAX_ITEMS_PER_ROW ) )
					+ ITEM_Y_OFFSET;
			int x = (int) LocationHelper.tileToScreenPixel(i % MAX_ITEMS_PER_ROW);

			this.addGameObject( this.items.get(i), x, y );
		}

		final int height = MapGenerator.TILESIZE * 3;
		final double startY = this.height / 2 - height / 2d;
		final double startX = this.width - MapGenerator.TILESIZE;

		if ( this.getSelectedWeapon() != null )
			this.addGameObject( this.getSelectedWeapon(), (int) startX, (int) startY);
		if ( this.getSelectedNormalAbility() != null )
			this.addGameObject( this.getSelectedNormalAbility(), (int) startX, (int) startY + MapGenerator.TILESIZE );
		if ( this.getSelectedRangedAbility() != null )
			this.addGameObject( this.getSelectedRangedAbility(), (int) startX, (int) startY + 2 * MapGenerator.TILESIZE );
	}

	/**
	 * Redraw the shown items on the screen.
	 */
	private void redrawShownItems() {
		this.resetShownItems();
		this.placeItems();
	}

	/**
	 * Show the inventory.
	 */
	public void show() {
		this.placeItems();
		this.isFirstMousePress = true;

		this.setVisible(true);
	}

	/**
	 * Close the inventory.
	 */
	private void close() {
		FightForSalvation instance = FightForSalvation.getInstance();

		this.resetShownItems();
		this.setVisible(false);
		instance.closedInventory();
	}

	@Override
	public void draw(PGraphics g) {
		super.draw(g);

		if (this.selectedMoveItem > -1) {
			int itemY = (int) LocationHelper.tileToScreenPixel( (float) Math.floor( (float) this.selectedMoveItem / MAX_ITEMS_PER_ROW ) )
					+ ITEM_Y_OFFSET;
			int itemX = (int) LocationHelper.tileToScreenPixel(this.selectedMoveItem % MAX_ITEMS_PER_ROW);

			g.fill(0x000000, 0);
			g.strokeWeight(ITEM_STROKE_WIDTH);
			g.stroke( Color.decode("#000000").getRGB() );
			g.rect(
					itemX, itemY,
					MapGenerator.TILESIZE - Inventory.ITEM_STROKE_WIDTH, MapGenerator.TILESIZE - Inventory.ITEM_STROKE_WIDTH
			);
		}
	}

	/**
	 * Check if the mouse is above the done button.
	 *
	 * @param x The x-position of the mouse.
	 * @param y The y-position of the mouse.
	 * @return True if above, false if not.
	 */
	private boolean isMouseAboveButton(int x, int y) {
		return x > DONE_BUTTON_TOP_LEFT_X - DONE_BUTTON_WIDTH / 2 &&
				x < DONE_BUTTON_TOP_LEFT_X + DONE_BUTTON_WIDTH / 2 &&
				y > DONE_BUTTON_TOP_LEFT_Y - DONE_BUTTON_HEIGHT / 2 &&
				y < DONE_BUTTON_TOP_LEFT_Y + DONE_BUTTON_HEIGHT / 2;
	}

	@Override
	public void mousePressed(int x, int y, int button) {
		if ( this.isVisible() ) {
			if (this.isFirstMousePress) {
				this.isFirstMousePress = false;
				return;
			}

			final int height = MapGenerator.TILESIZE * 3;
			final double startY = this.height / 2 - height / 2d;
			final double startX = this.width - MapGenerator.TILESIZE;
			final int itemX = (int) LocationHelper.screenToTilePixel(x);
			final int itemY = (int) LocationHelper.screenToTilePixel(y - ITEM_Y_OFFSET);
			final int itemIndex = itemY * MAX_ITEMS_PER_ROW + itemX;

			if ( this.isMouseAboveButton(x, y) ) {
				this.close();
			} else if ( this.selectedMoveItem > -1 && x >= startX && y >= startY && y <= startY + height * 3 ) { // click was on one of the selected items
				int itemPos = (int) LocationHelper.screenToTilePixel( (float) (y - startY) );

				System.out.println(itemPos);

				// 0 is the first item, 1 is the second, etc
				switch (itemPos) {
					case 0:
						this.setSelectedWeapon(this.selectedMoveItem);
						break;
					case 1:
						this.setSelectedNormalAbility(this.selectedMoveItem);
						break;
					case 2:
						this.setSelectedRangedAbility(this.selectedMoveItem);
						break;
				}

				this.selectedMoveItem = -1;

				this.redrawShownItems();
			} else { // click was on an item
				if ( itemIndex < this.items.size() && this.items.get(itemIndex) != null ) {
					this.selectedMoveItem = itemIndex;
				} else {
					this.selectedMoveItem = -1;
				}
			}
		}
	}
}
