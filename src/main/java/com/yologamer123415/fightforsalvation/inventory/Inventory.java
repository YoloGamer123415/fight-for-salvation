package com.yologamer123415.fightforsalvation.inventory;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.generators.MapGenerator;
import com.yologamer123415.fightforsalvation.helpers.LocationHelper;
import com.yologamer123415.fightforsalvation.object.UsableObject;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PGraphics;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Inventory extends Dashboard {
	private static final int ITEM_Y_OFFSET = MapGenerator.TILESIZE;
	private static final int MAX_ITEMS_PER_ROW = (FightForSalvation.SCREEN_WIDTH - 2 * MapGenerator.TILESIZE) / MapGenerator.TILESIZE;
	public static final int ITEM_STROKE_WIDTH = 2;
	public static final int DONE_BUTTON_TOP_LEFT_X = 728;
	public static final int DONE_BUTTON_TOP_LEFT_Y = 523;

	public final List<UsableObject> items = new LinkedList<>();
	private UsableObject selectedNormalAbility = null;
	private UsableObject selectedRangedAbility = null;
	private UsableObject selectedWeapon = null;

	private int selectedMoveItem = -1;

	private boolean isFirstMousePress = true;

	public Inventory(float x, float y, float width, float height) {
		super(x, y, width, height);

		this.setVisible(false);
		this.setX(0);
		this.setY(0);
		this.setZ(100);
		this.setBackgroundImage( new Sprite("src/main/resources/background_inventory.jpg") );
	}

	public UsableObject getSelectedNormalAbility() {
		return this.selectedNormalAbility;
	}

	public void setSelectedNormalAbility(int selectedNormalAbility) {
		if ( selectedNormalAbility >= this.items.size() ) return;

		this.items.add(this.selectedNormalAbility);
		this.selectedNormalAbility = this.items.remove(selectedNormalAbility); // for some reason, .remove(int) doesn't
		// remove the item, but just makes it null. We don't want that, so we do this:
		this.items.removeAll( Collections.singleton(null) ); // https://www.baeldung.com/java-remove-nulls-from-list#java
	}

	public UsableObject getSelectedRangedAbility() {
		return this.selectedRangedAbility;
	}

	public void setSelectedRangedAbility(int selectedRangedAbility) {
		if ( selectedRangedAbility >= this.items.size() ) return;

		this.items.add(this.selectedRangedAbility);
		this.selectedRangedAbility = this.items.remove(selectedRangedAbility); // for some reason, .remove(int) doesn't
		// remove the item, but just makes it null. We don't want that, so we do this:
		this.items.removeAll( Collections.singleton(null) ); // https://www.baeldung.com/java-remove-nulls-from-list#java
	}

	public UsableObject getSelectedWeapon() {
		return this.selectedWeapon;
	}

	public void setSelectedWeapon(int selectedWeapon) {
		if ( selectedWeapon >= this.items.size() ) return;

		this.items.add(this.selectedWeapon);
		this.selectedWeapon = this.items.remove(selectedWeapon); // for some reason, .remove(int) doesn't remove the
		// item, but just makes it null. We don't want that, so we do this:
		this.items.removeAll( Collections.singleton(null) ); // https://www.baeldung.com/java-remove-nulls-from-list#java
	}

	public int addItem(UsableObject item) {
		this.items.add(item);

		if ( this.isVisible() ) this.redrawShownItems();

		return this.items.indexOf(item);
	}

	private void resetShownItems() {
		this.deleteAllGameObjectsOfType(UsableObject.class);
	}

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
			this.addGameObject( this.getSelectedNormalAbility(), (int) startX, (int) startY + height);
		if ( this.getSelectedRangedAbility() != null )
			this.addGameObject( this.getSelectedRangedAbility(), (int) startX, (int) startY + 2 * height );
	}

	private void redrawShownItems() {
		this.resetShownItems();
		this.placeItems();
	}

	public void show() {
		this.placeItems();
		this.isFirstMousePress = true;

		this.setVisible(true);
	}

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

			// TODO: Check real position of "done" button
			if (x >= DONE_BUTTON_TOP_LEFT_X && y >= DONE_BUTTON_TOP_LEFT_Y) {
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
