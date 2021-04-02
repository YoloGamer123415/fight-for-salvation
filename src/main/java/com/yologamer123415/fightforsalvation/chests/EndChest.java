package com.yologamer123415.fightforsalvation.chests;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.object.UsableObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public class EndChest extends Chest {
	public EndChest(Sprite sprite) {
		super(sprite);
	}

	@Override
	public boolean canBeOpened() {
		return FightForSalvation.getInstance().getMonstersAlive() <= 0; //TODO Implement (false should be replaced by level ended)
	}

	@Override
	public UsableObject[] open(GameObject opener) {
		FightForSalvation.getInstance().getPlayer().getInventory().show();

		return super.open(opener);
	}
}
