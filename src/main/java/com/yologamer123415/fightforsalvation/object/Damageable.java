package com.yologamer123415.fightforsalvation.object;

/**
 * A GameObject with this interface can take damage.
 */
public interface Damageable {
	/**
	 * Give the GameObject damage.
	 * @param damage The damage to give.
	 */
	void damage(int damage);
}
