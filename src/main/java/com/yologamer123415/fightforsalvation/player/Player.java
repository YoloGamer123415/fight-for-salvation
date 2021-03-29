package com.yologamer123415.fightforsalvation.player;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.inventory.Inventory;
import com.yologamer123415.fightforsalvation.object.FlammableSpriteObject;
import com.yologamer123415.fightforsalvation.object.UsableObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class Player extends FlammableSpriteObject {
	private static final float SPEED_STOPPED = 0;
	private static final float SPEED_NORMAL = 3.5f;

	private int totalEssence;

	public Player(Sprite sprite) {
		super(sprite, 10, 2);

		this.totalEssence = 0;
	}

	public void addEssence(int amount) {
		if (amount >= 0) totalEssence += amount;
	}

	@Override
	public void update() {
		super.update();

		this.setSpeed(0);
	}

	@Override
	public void mousePressed(int x, int y, int button) {
		super.mousePressed(x, y, button);

		// TODO: Check if these numbers are correct
		if (button == 0 || button == 1) {
			Inventory inventory = FightForSalvation.getInstance().getInventory();
			UsableObject usable = button == 0
					? inventory.getSelectedWeapon()
					: inventory.getSelectedRangedAbility();

			if (usable != null) {
				Vector vector = Vector.generateVector(this.x, this.y, x, y);

				usable.use(vector);
			}
		}
	}

	@Override
	public void keyPressed(int keyCode, char key) {
		super.keyPressed(keyCode, key);

		switch (key) {
			case 'w':
				this.setxSpeed(SPEED_STOPPED);
				this.setySpeed(-SPEED_NORMAL);
				break;
			case 'a':
				this.setxSpeed(-SPEED_NORMAL);
				this.setySpeed(SPEED_STOPPED);
				break;
			case 's':
				this.setxSpeed(SPEED_STOPPED);
				this.setySpeed(SPEED_NORMAL);
				break;
			case 'd':
				this.setxSpeed(SPEED_NORMAL);
				this.setySpeed(SPEED_STOPPED);
				break;
			case ' ':
				FightForSalvation instance = FightForSalvation.getInstance();
				Inventory inventory = instance.getInventory();
				UsableObject usable = inventory.getSelectedNormalAbility();

				if (usable != null) {
					Vector vector = Vector.generateVector(
							this.x, this.y,
							instance.mouseX, instance.mouseY
					);

					usable.use(vector);
				}
				break;
		}
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> list) {

	}
}
