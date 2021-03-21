package com.yologamer123415.fightforsalvation.helpers;

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

	public static Rarity getRandomRarity() {
		return getRandomRarity(Rarity.COMMON);
	}

	public static Rarity getRandomRarity(Rarity from) {
		Rarity[] values = Rarity.values();
		int random = (int) (Math.random() * 100);

		for (int i = 0; i < values.length; i++) {
			int boundry = 0;
			for (int j = 0; j <= i; j++) {
				boundry += values[j].chance;
			}

			if (random <= boundry) return values[i];
		}

		return Rarity.COMMON;
	}
}
