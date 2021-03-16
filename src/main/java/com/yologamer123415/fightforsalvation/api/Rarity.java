package com.yologamer123415.fightforsalvation.api;

public enum Rarity {
	COMMON(50),
	NORMAL(35),
	EPIC(15);

	/**
	 * The chance to get this rarity
	 */
	private final int chance;

	Rarity(int rarity) {
		this.chance = rarity;
	}

	public static Rarity getRarity() {
		int random = (int) (Math.random() * 100);

		//TODO Fix dit...

		Rarity[] rarities = values();
		int start = 0;
		for (int i = rarities.length - 1; i >= 0; i--) {
			int currentStart = start == 0 ? 0 : start + 1;
			int end = start + rarities[i].chance;

			if (random > currentStart && random < end) {
				return rarities[i];
			}

			start += rarities[i].chance;
		}

		return Rarity.COMMON;
	}
}
