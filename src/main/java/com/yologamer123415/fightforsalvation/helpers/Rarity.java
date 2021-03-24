package com.yologamer123415.fightforsalvation.helpers;

public enum Rarity {
	COMMON(50),
	NORMAL(35),
	EPIC(15);

	/**
	 * The chance to get this rarity
	 */
	private final int chance;

	Rarity(int chance) {
		this.chance = chance;
	}

	public int getCalculationValue() {
		Rarity[] rarities = Rarity.values();
		for (int i = 0; i < rarities.length; i++) {
			if (this.equals(rarities[i])) {
				return ++i;
			}
		}
		return 1;
	}

	public static Rarity getRandomRarity() {
		return getRandomRarity(Rarity.COMMON);
	}

	public static Rarity getRandomRarity(Rarity from) {
		final Rarity[] values = Rarity.values();
		final double modifier = from.getCalculationValue() / 2.0 + 0.5;
		final int random = (int) (Math.random() * 100 + 100 * (modifier - 1));

		for (int i = 0; i < values.length; i++) {
			int boundary = 0;

			for (int j = 0; j <= i; j++) {
				boundary += values[j].chance;
			}

			boundary *= modifier;

			if (random <= boundary) return values[i];
		}

		return Rarity.COMMON;
	}
}
