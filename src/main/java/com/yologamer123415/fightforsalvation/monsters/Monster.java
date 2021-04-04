package com.yologamer123415.fightforsalvation.monsters;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.chests.Chest;
import com.yologamer123415.fightforsalvation.generators.MapGenerator;
import com.yologamer123415.fightforsalvation.helpers.CollidingHelper;
import com.yologamer123415.fightforsalvation.helpers.Rarity;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.object.Damageable;
import com.yologamer123415.fightforsalvation.object.FlammableSpriteObject;
import com.yologamer123415.fightforsalvation.player.Player;
import com.yologamer123415.fightforsalvation.tyles.Border;
import com.yologamer123415.fightforsalvation.usables.weapons.Weapon;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PGraphics;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Monster extends FlammableSpriteObject implements Damageable, ICollidableWithTiles {
	protected static final int COLOR_HEALTH_BAR = Color.decode("#00ff00").getRGB();
	protected static final int COLOR_HEALTH_BAR_HP = Color.decode("#ff0000").getRGB();

	protected final Rarity rarity;
	protected Weapon weapon;

	private final boolean movingX;
	private boolean movingUp;
	private boolean movingRight;

	private final int startHp;
	private int hp;

	/**
	 * Construct a new Monster.
	 *
	 * @param sprite The sprite of the monster.
	 * @param hp The default HP of the monster.
	 */
	public Monster(Sprite sprite, int hp) {
		super(sprite, 10, 2);
		this.hp = this.startHp = hp;
		this.rarity = Rarity.getRandomRarity();

		final Random rand = new Random();

		this.movingX = rand.nextBoolean();
		if (this.movingX) {
			this.movingRight = rand.nextBoolean();
		} else {
			this.movingUp = rand.nextBoolean();
		}

		setSpeed( rand.nextInt(movingX ? 4 : 3) + 1 );
	}

	/**
	 * Calculate the total essence randomly.
	 *
	 * @return The total essence.
	 */
	protected int calculateTotalEssence() {
		int min = this.rarity.getCalculationValue() - 1;
		int max = this.rarity.getCalculationValue() * 2;

		return (int) ( Math.random() * (max - min + 1) + min );
	}

	@Override
	public void damage(int damage) {
		this.hp = Math.max(this.hp - damage, 0);

		if (this.hp == 0) {
			int essenceToGive = this.calculateTotalEssence();
			FightForSalvation instance = FightForSalvation.getInstance();

			instance.getPlayer().addEssence(essenceToGive);
			instance.deleteGameObject(this);
			instance.decreaseMonstersAlive();
		}
	}

	@Override
	public void update() {
		if ( this.shouldDoDamage() ) {
			this.damage(FIREDAMAGE);
		}

		super.update();

		setDirection(
				movingX
						? movingRight ? 90 : 270
						: movingUp ? 0 : 180
		);

		Player player = FightForSalvation.getInstance().getPlayer();

		if ( getDistanceFrom(player) <= 40 && !this.weapon.isInCooldown() ) {
			float monsterX = this.x + ( MapGenerator.TILESIZE / 2f );
			float monsterY = this.y + ( MapGenerator.TILESIZE / 2f );

			this.weapon.use( Vector.generateVector( monsterX, monsterY, player.getX(), player.getY() ) );
		}
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> list) {
		for (GameObject go : list) {
			if ( go instanceof FlammableSpriteObject && this.isRunning() ) {
				((FlammableSpriteObject) go).startBurning();

				if (go instanceof Damageable) {
					((Damageable) go).damage(FIREDAMAGE);
				}

				CollisionSide side = CollidingHelper.calculateCollidedTileSide( (int) go.getAngleFrom(this) );
				if (side == null) continue;
				this.handleBounce(side);
			} else if ( go.getClass().getPackage().getName().endsWith("obstacles") || go instanceof Player ) {
				CollisionSide side = CollidingHelper.calculateCollidedTileSide( (int) go.getAngleFrom(this) );
				if (side == null) continue;
				this.handleBounce(side);
			}
		}
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> list) {
		for (CollidedTile ct : list) {
			if (ct.getTile() instanceof Border || ct.getTile() instanceof Chest) {
				handleBounce(ct.getCollisionSide());
			}
		}
	}

	@Override
	public void draw(PGraphics g) {
		super.draw(g);

		// draw a hp bar above the monster
		if (this.hp < this.startHp) {
			final float height = MapGenerator.TILESIZE / 10f;
			final float width = this.getWidth();
			final float hpWidth = width * (this.hp / (float) this.startHp);

			g.noStroke();
			g.fill(COLOR_HEALTH_BAR);
			g.rect(
					this.getX(), this.getY(),
					width, height
			);

			g.fill(COLOR_HEALTH_BAR_HP);
			g.rect(
					this.getX(), this.getY(),
					hpWidth, height
			);
		}
	}

	/**
	 * Handle movement of the monster, on collision.
	 *
	 * @param side The side of the collision.
	 */
	private void handleBounce(CollisionSide side) {
		if (movingX) {
			if (movingRight && side == CollisionSide.LEFT) {
				movingRight = false;
			} else if (!movingRight && side == CollisionSide.RIGHT) {
				movingRight = true;
			}
		} else {
			if (movingUp && side == CollisionSide.BOTTOM) {
				movingUp = false;
			} else if (!movingUp && side == CollisionSide.TOP) {
				movingUp = true;
			}
		}
	}
}
