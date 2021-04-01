package com.yologamer123415.fightforsalvation.helpers;

public enum Rarity {
	COMMON(50, 0xa9a9a9),
	NORMAL(35, 0x6495ed),
	EPIC(15, 0x9932cc);

	/**
	 * The chance to get this rarity
	 */
	private final int chance;
	private final int color;

	Rarity(int chance, int color) {
		this.chance = chance;
		this.color = color;
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

	public int getColor() {
		return this.color;
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
}
