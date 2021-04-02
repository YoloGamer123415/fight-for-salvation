package com.yologamer123415.fightforsalvation.usables.abilities.normal;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.helpers.LocationHelper;
import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.player.Player;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public class DashAbility extends NormalAbility {
	private static final int MAX_DISTANCE = 1;

	public DashAbility(GameObject holder, Rarity chestRarity) {
		super("Dash", new Sprite("src/main/resources/usables/abilities/normal/dash.png"), holder, chestRarity, 5f);
	}

	@Override
	public void use(Vector mousePos) {
		final Player player = FightForSalvation.getInstance().getPlayer();
		final float distance = Math.min(
				mousePos.getLength(),
				LocationHelper.tileToScreenPixel(MAX_DISTANCE) * this.rarity.getCalculationValue()
		);

		float newX = 0, newY = 0;

		newX = (float) ( player.getX() + Math.cos( Math.toRadians( mousePos.getAngle() - 90 ) ) * distance );
		newY = (float) ( player.getY() + Math.sin( Math.toRadians( mousePos.getAngle() - 90 ) ) * distance );

		if ( mousePos.getPointX() == player.getX() ) {
			newX = player.getX();
		}
		if ( mousePos.getPointY() == player.getY() ) {
			newY = player.getY();
		}

		player.setX(newX);
		player.setY(newY);
	}

	@Override
	public void update() {

	}
}
