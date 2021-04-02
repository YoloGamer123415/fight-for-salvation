package com.yologamer123415.fightforsalvation.player;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.chests.Chest;
import com.yologamer123415.fightforsalvation.helpers.CollidingHelper;
import com.yologamer123415.fightforsalvation.helpers.LocationHelper;
import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.inventory.Inventory;
import com.yologamer123415.fightforsalvation.object.Damageable;
import com.yologamer123415.fightforsalvation.object.FlammableSpriteObject;
import com.yologamer123415.fightforsalvation.object.UsableObject;
import com.yologamer123415.fightforsalvation.tyles.Border;
import com.yologamer123415.fightforsalvation.usables.weapons.ranged.BowAndArrow;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player extends FlammableSpriteObject implements Damageable, ICollidableWithTiles {
	private static final int DEFAULT_HP = 150;
	private static final float SPEED_STOPPED = 0;
	private static final float SPEED_NORMAL = 3.5f;

	private final Inventory inventory = new Inventory();

	private int hp = DEFAULT_HP;
	private int totalEssence = 0;

	/**
	 * Construct a new Player object.
	 * This method will be called from the MapGenerator!
	 *
	 * @param sprite The sprite to use.
	 */
	public Player(Sprite sprite) {
		super(sprite, 10, 2);

		//Add default item...
		int index = this.inventory.addItem( new BowAndArrow(this, Rarity.NORMAL) );
		this.inventory.setSelectedWeapon(index);

		final FightForSalvation instance = FightForSalvation.getInstance();
		instance.setPlayer(this);
		instance.addDashboard(this.inventory);
	}

	/**
	 * Get the inventory of the Player.
	 *
	 * @return The inventory.
	 */
	public Inventory getInventory() {
		return this.inventory;
	}

	/**
	 * Get the essence of the Player.
	 *
	 * @return The essence.
	 */
	public int getEssence() {
		return this.totalEssence;
	}

	/**
	 * Add essence to the player.
	 *
	 * @param amount The essence to add.
	 */
	public void addEssence(int amount) {
		if (amount >= 0) this.totalEssence += amount;
	}

	/**
	 * Remove essence from the player.
	 *
	 * @param amount The essence to remove.
	 */
	public void removeEssence(int amount) {
		if (amount >= 0 && this.totalEssence >= amount) this.totalEssence -= amount;
	}

	/**
	 * Get the HP of the player.
	 *
	 * @return The HP.
	 */
	public int getHP() {
		return this.hp;
	}

	/**
	 * Reset the HP of the player to the default value.
	 */
	public void resetHP() {
		this.hp = DEFAULT_HP;
	}

	@Override
	public void damage(int damage) {
		this.hp = Math.max(this.hp - damage, 0);

		if (this.hp == 0) {
			//TODO Move to end screen
			FightForSalvation.getInstance().stop();
		}
	}

	@Override
	public void update() {
		if (this.shouldDoDamage()) {
			this.damage(FIREDAMAGE);
		}

		super.update();

		this.setxSpeed(0);
		this.setySpeed(0);
	}

	@Override
	public void mousePressed(int x, int y, int button) {
		if (button == FightForSalvation.LEFT || button == FightForSalvation.RIGHT) {
			float playerX = this.x + LocationHelper.getHalfTileSize();
			float playerY = this.y + LocationHelper.getHalfTileSize();

			UsableObject usable = button == FightForSalvation.LEFT
					? this.inventory.getSelectedWeapon()
					: this.inventory.getSelectedRangedAbility();

			if ( usable != null && !usable.isInCooldown() ) {
				Vector vector = Vector.generateVector(playerX, playerY, x, y);

				usable.use(vector);
			}
		} else if (button == FightForSalvation.CENTER) {
			Tile tile = FightForSalvation.getInstance().getTileMap().getTileOnPosition(x, y);

			if (
					LocationHelper.calculateDistanceBetweenTwoPoints(
							x, y,
							this.x + LocationHelper.getHalfTileSize(), this.y + LocationHelper.getHalfTileSize()
					) <= LocationHelper.getHalfTileSize() * 3
					&& tile instanceof Chest
					&& ( (Chest) tile ).canBeOpened()
			) {
				UsableObject[] items = ( (Chest) tile ).open(this);

				if (items != null) {
					for (UsableObject item : items) this.inventory.addItem(item);
				}
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
				UsableObject usable = this.inventory.getSelectedNormalAbility();

				if ( usable != null && !usable.isInCooldown() ) {
					FightForSalvation instance = FightForSalvation.getInstance();

					Vector vector = Vector.generateVector(
							this.x, this.y,
							instance.mouseX, instance.mouseY
					);

					usable.use(vector);
				}
				break;
			case 'k':
				Iterator<GameObject> iterator = new ArrayList<>( FightForSalvation.getInstance().getGameObjectItems() ).iterator();

				while ( iterator.hasNext() ) {
					GameObject go = iterator.next();

					if (go instanceof Damageable && !go.equals(this)) {
						((Damageable) go).damage(10000);
					}
				}
				break;
		}
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> list) {
		for (GameObject go : list) {
			if (go instanceof FlammableSpriteObject && this.isRunning()) {
				((FlammableSpriteObject) go).startBurning();

				if (go instanceof Damageable) {
					((Damageable) go).damage(FIREDAMAGE);
				}

				CollisionSide side = CollidingHelper.calculateCollidedTileSide((int) go.getAngleFrom(this));
				if (side == null) continue;
				CollidingHelper.handleCollisionStop(this, side, go.getX(), go.getY());
			} else if (go.getClass().getPackage().getName().endsWith("obstacles")) {
				CollisionSide side = CollidingHelper.calculateCollidedTileSide((int) go.getAngleFrom(this));
				if (side == null) continue;
				CollidingHelper.handleCollisionStop(this, side, go.getX(), go.getY());
			}
		}
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> list) {
		for (CollidedTile ct : list) {
			if (ct.getTile() instanceof Border || ct.getTile() instanceof Chest) {
				PVector vector = FightForSalvation.getInstance().getTileMap().getTilePixelLocation(ct.getTile());
				CollidingHelper.handleCollisionStop(this, ct.getCollisionSide(), vector.x, vector.y);
			}
		}
	}
}
