package com.yologamer123415.fightforsalvation.inventory;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.generators.MapGenerator;
import com.yologamer123415.fightforsalvation.object.UsableObject;
import nl.han.ica.oopg.dashboard.Dashboard;

import java.util.LinkedList;
import java.util.List;

public class Inventory extends Dashboard {
	private static final int MAX_ITEMS_PER_ROW = 5;

	private final List<UsableObject> items = new LinkedList<>();
	private int selectedNormalAbility = -1;
	private int selectedRangedAbility = -1;
	private int selectedWeapon = -1;

	private int selectedMoveItem = -1;

	public Inventory(float x, float y, float width, float height) {
		super(x, y, width, height);
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

	private void resetShownItems() {
		this.deleteAllGameObjectsOfType(UsableObject.class);
	}

	private void placeItems() {
		int itemCount = 0;
		for (UsableObject item : this.items) {
			if (
					!item.equals( this.getSelectedWeapon() )
							&& !item.equals( this.getSelectedNormalAbility() )
							&& ! item.equals( this.getSelectedRangedAbility() )
			) {
				int y = itemCount / MAX_ITEMS_PER_ROW;
				int x = itemCount % MAX_ITEMS_PER_ROW;

				this.addGameObject(this.items.get(itemCount), x * MapGenerator.TILESIZE, y * MapGenerator.TILESIZE);

				itemCount++;
			}
		}

		// TODO: Place selected Usables in the right place
	}

	private void redrawShownItems() {
		this.resetShownItems();
		this.placeItems();
	}

	public void show() {
		FightForSalvation instance = FightForSalvation.getInstance();

		this.placeItems();

		instance.addDashboard(this);
	}

	private void close() {
		FightForSalvation instance = FightForSalvation.getInstance();
		instance.deleteDashboard(this);
		this.resetShownItems();
		instance.closedInventory();
	}

	@Override
	public void mousePressed(int x, int y, int button) {
		// TODO: Check real position of "done" button
		if (x >= this.width * 0.90 && y >= this.height * 0.90) {
			this.close();
		} else { // click was on an item
			int itemX = x / MapGenerator.TILESIZE;
			int itemY = y / MapGenerator.TILESIZE;
			int itemIndex = itemY * MAX_ITEMS_PER_ROW + itemX;

			if ( this.items.get(itemIndex) != null ) {
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
