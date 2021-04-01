package com.yologamer123415.fightforsalvation.usables.abilities.ranged;

import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.object.movables.Movable;
import javafx.scene.shape.MoveTo;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public class WeaknessPotion extends Potion {
	public WeaknessPotion(GameObject holder) {
		super("Weakness Potion", new Sprite("src/main/resources/usables/abilities/ranged/paars.png"), holder);
	}

	@Override
	public void update() {

	}

	@Override
	public Movable getMovable(Vector mousePos) {
		return new com.yologamer123415.fightforsalvation.object.movables.WeaknessPotion(mousePos, holder);
	}
}
