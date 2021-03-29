package com.yologamer123415.fightforsalvation.inventory;

import com.yologamer123415.fightforsalvation.object.UsableObject;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	private List<UsableObject> items = new ArrayList<>();
	private int selectedNormalAbility = -1;
	private int selectedRangedAbility = -1;
	private int selectedWeapon = -1;

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
}
