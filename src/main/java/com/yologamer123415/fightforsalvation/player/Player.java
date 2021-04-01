package com.yologamer123415.fightforsalvation.player;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.generators.MapGenerator;
import com.yologamer123415.fightforsalvation.helpers.CollidingHelper;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.inventory.Inventory;
import com.yologamer123415.fightforsalvation.object.FlammableSpriteObject;
import com.yologamer123415.fightforsalvation.object.UsableObject;
import com.yologamer123415.fightforsalvation.tyles.Border;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;

import java.util.List;

public class Player extends FlammableSpriteObject implements ICollidableWithTiles {
	private static final float SPEED_STOPPED = 0;
	private static final float SPEED_NORMAL = 3.5f;

	private int totalEssence;

	/**
	 * Construct a new Player object.
	 * This method will be called from the MapGenerator!
	 *
	 * @param sprite The sprite to use.
	 */
	public Player(Sprite sprite) {
		super(sprite, 10, 2);

		FightForSalvation.getInstance().setPlayer(this);

		this.totalEssence = 0;
	}

	public int getTotalEssence() {
		return totalEssence;
	}

	public void addEssence(int amount) {
		if (amount >= 0) totalEssence += amount;
	}

	@Override
	public void update() {
		super.update();

		setxSpeed(0);
		setySpeed(0);
	}

	@Override
	public void mousePressed(int x, int y, int button) {
		if (button == FightForSalvation.LEFT || button == FightForSalvation.RIGHT) {
			float playerX = this.x + ( MapGenerator.TILESIZE / 2f );
			float playerY = this.y + ( MapGenerator.TILESIZE / 2f );

			Inventory inventory = FightForSalvation.getInstance().getInventory();
			UsableObject usable = button == FightForSalvation.LEFT
					? inventory.getSelectedWeapon()
					: inventory.getSelectedRangedAbility();

			if (usable != null) {
				Vector vector = Vector.generateVector(playerX, playerY, x, y);

				usable.use(vector);
			}
		}
	}

	@Override
	public void keyPressed(int keyCode, char key) {
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
		for (GameObject go : list) {
			if (go.getClass().getPackage().getName().endsWith("obstacles")) {
				CollisionSide side = CollidingHelper.calculateCollidedTileSide((int) go.getAngleFrom(this));
				if (side == null) continue;
				CollidingHelper.handleCollisionStop(this, side, go.getX(), go.getY());
			}
		}
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> list) {
		for (CollidedTile ct : list) {
			if (ct.getTile() instanceof Border) {
				PVector vector = FightForSalvation.getInstance().getTileMap().getTilePixelLocation(ct.getTile());
				CollidingHelper.handleCollisionStop(this, ct.getCollisionSide(), vector.x, vector.y);
			}
		}
	}

	@Override
	public String toString() {
		return "Player{" +
				"totalEssence=" + totalEssence +
				", x=" + x +
				", y=" + y +
				'}';
	}
}
