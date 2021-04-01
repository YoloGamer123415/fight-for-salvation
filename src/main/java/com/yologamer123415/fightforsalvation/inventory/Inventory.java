package com.yologamer123415.fightforsalvation.inventory;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.generators.MapGenerator;
import com.yologamer123415.fightforsalvation.helpers.LocationHelper;
import com.yologamer123415.fightforsalvation.object.UsableObject;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.Sprite;

import java.util.LinkedList;
import java.util.List;

public class Inventory extends Dashboard {
	private static final int ITEM_Y_OFFSET = MapGenerator.TILESIZE;
	private static final int MAX_ITEMS_PER_ROW = FightForSalvation.SCREEN_WIDTH / MapGenerator.TILESIZE;

	private final List<UsableObject> items = new LinkedList<>();
	private int selectedNormalAbility = -1;
	private int selectedRangedAbility = -1;
	private int selectedWeapon = -1;

	private int selectedMoveItem = -1;

	public Inventory(float x, float y, float width, float height) {
		super(x, y, width, height);

		this.setVisible(false);
		this.setX(0);
		this.setY(0);
		this.setZ(100);
		this.setBackgroundImage( new Sprite("src/main/resources/background.jpg") );
	}

	public UsableObject getSelectedNormalAbility() {
		if (this.selectedNormalAbility == -1) return null;

		return this.items.get(this.selectedNormalAbility);
	}

	public void setSelectedNormalAbility(int selectedNormalAbility) {
		this.selectedNormalAbility = selectedNormalAbility;
	}

	public UsableObject getSelectedRangedAbility() {
		if (this.selectedRangedAbility == -1) return null;

		return this.items.get(this.selectedRangedAbility);
	}

	public void setSelectedRangedAbility(int selectedRangedAbility) {
		this.selectedRangedAbility = selectedRangedAbility;
	}

	public UsableObject getSelectedWeapon() {
		if (this.selectedWeapon == -1) return null;

		return this.items.get(this.selectedWeapon);
	}

	public void setSelectedWeapon(int selectedWeapon) {
		this.selectedWeapon = selectedWeapon;
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
		int itemCount = 0;
		for (UsableObject item : this.items) {
			if (
					!item.equals( this.getSelectedWeapon() )
							&& !item.equals( this.getSelectedNormalAbility() )
							&& !item.equals( this.getSelectedRangedAbility() )
			) {
				int y = (int) LocationHelper.tileToScreenPixel( (float) Math.floor( (float) itemCount / MAX_ITEMS_PER_ROW ) )
						+ ITEM_Y_OFFSET;
				int x = (int) LocationHelper.tileToScreenPixel(itemCount % MAX_ITEMS_PER_ROW);

				this.addGameObject(item, x, y);

				itemCount++;
			}
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
		FightForSalvation instance = FightForSalvation.getInstance();

		this.placeItems();

		this.setVisible(true);
	}

	private void close() {
		FightForSalvation instance = FightForSalvation.getInstance();

		this.resetShownItems();
		this.setVisible(false);
		instance.closedInventory();
	}

	@Override
	public void mousePressed(int x, int y, int button) {
		System.out.println("inventory click!");

		// TODO: Check real position of "done" button
		if (x >= this.width * 0.90 && y >= this.height * 0.90) {
			this.close();
		} else { // click was on an item
			int itemX = x / MapGenerator.TILESIZE;
			int itemY = y / MapGenerator.TILESIZE;
			int itemIndex = itemY * MAX_ITEMS_PER_ROW + itemX;

			System.out.println(itemIndex);

			if ( this.items.get(itemIndex) != null ) {
				System.out.println("yeet");
				if (this.selectedMoveItem == -1) { // first time selecting an UsableObject
					this.selectedMoveItem = itemIndex;
				} else { // second time selecting an UsableObject
					// TODO: Check if click is on one of the selected items
					if (true) {
						int topOffset = 0; // TODO: find correct top offset
						int itemPos = (y - topOffset) / MapGenerator.TILESIZE;

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
					} else { // wasn't on one of the selected items, so make this.selectedMoveItem the new itemIndex
						this.selectedMoveItem = itemIndex;
					}
				}
			}
		}
	}
}
