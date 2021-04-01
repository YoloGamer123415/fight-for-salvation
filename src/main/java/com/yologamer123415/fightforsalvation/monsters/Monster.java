package com.yologamer123415.fightforsalvation.monsters;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.helpers.CollidingHelper;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.object.FlammableSpriteObject;
import com.yologamer123415.fightforsalvation.player.Player;
import com.yologamer123415.fightforsalvation.tyles.Border;
import com.yologamer123415.fightforsalvation.usables.weapons.Weapon;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;

import java.util.List;
import java.util.Random;

public class Monster extends FlammableSpriteObject implements ICollidableWithTiles {
	protected Weapon weapon;

	private final boolean movingX;
	private boolean movingUp;
	private boolean movingRight;

	private int hp;

	public Monster(Sprite sprite, int hp) {
		super(sprite, 10, 2);
		this.hp = hp;

		final Random rand = new Random();

		this.movingX = rand.nextBoolean();
		if (this.movingX) {
			this.movingRight = rand.nextBoolean();
		} else {
			this.movingUp = rand.nextBoolean();
		}

		setSpeed(rand.nextInt(movingX ? 4 : 3) + 1);
	}

	public void damage(int damage) {
		hp = Math.max(hp - damage, 0);
		if (hp == 0) FightForSalvation.getInstance().deleteGameObject(this);
	}

	@Override
	public void update() {
		super.update();

		setDirection(
				movingX
						? movingRight ? 90 : 270
						: movingUp ? 0 : 180
		);

		Player player = FightForSalvation.getInstance().getPlayer();
		if (getDistanceFrom(player) <= 4) {
			weapon.use(Vector.generateVector(x, y, player.getX(), player.getY()));
		}
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> list) {
		for (GameObject go : list) {
			if (go instanceof FlammableSpriteObject && !go.equals(FightForSalvation.getInstance().getPlayer())) {
				FlammableSpriteObject fso = (FlammableSpriteObject) go;
				if (fso.shouldDoDamage()) {
					//TODO Handle fire damage
				} else {
					CollisionSide side = CollidingHelper.calculateCollidedTileSide((int) go.getAngleFrom(this));
					if (side == null) continue;
					handleMove(side);
				}
			}
		}
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> list) {
		for (CollidedTile ct : list) {
			if (ct.getTile() instanceof Border) {
				handleMove(ct.getCollisionSide());
			}
		}
	}

	private void handleMove(CollisionSide side) {
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
