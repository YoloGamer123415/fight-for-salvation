package com.yologamer123415.fightforsalvation.object.movables;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.monsters.Monster;
import com.yologamer123415.fightforsalvation.object.ObjectClip;
import com.yologamer123415.fightforsalvation.player.Player;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

import java.util.List;

public abstract class Movable extends SpriteObject implements ICollidableWithGameObjects {
	private static final float SPEED = 20;

	private final Vector path;
	protected final GameObject shooter;

	/**
	 * Construct a new Movable.
	 *
	 * @param sprite The sprite of the Movable.
	 * @param path The path of the Movable.
	 * @param shooter The shooter of the Movable.
	 */
	public Movable(Sprite sprite, Vector path, GameObject shooter) {
		super(sprite);

		this.path = path;
		this.shooter = shooter;

		this.setX( this.path.getX() );
		this.setY( this.path.getY() );
		this.setZ(10f);
		this.setVisible(false);
	}

	/**
	 * Called when a collision is detected.
	 *
	 * @param gameObjects The object(s) it detects with.
	 */
	public abstract void collidedWithGameObjects(List<GameObject> gameObjects);

	/**
	 * Start movement of the movable.
	 */
	public final void startMoving() {
		this.setVisible(true);
		this.setDirectionSpeed( this.path.getAngle(), SPEED );
	}

	@Override
	public final void update() {
		float dx = this.x - this.path.getX();
		float dy = this.y - this.path.getY();

		double distanceFromStart = Math.sqrt(
				Math.pow( Math.abs(dx), 2 )
				+ Math.pow( Math.abs(dy), 2 )
		);

		if ( distanceFromStart >= this.path.getLength() )
			FightForSalvation.getInstance().deleteGameObject(this);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> list) {
		boolean filterMatch = list.stream().anyMatch(o -> !o.equals(shooter) && (o instanceof ObjectClip || o instanceof Monster || o instanceof Player));
		if (filterMatch) {
			this.collidedWithGameObjects(list);
			FightForSalvation.getInstance().deleteGameObject(this);
		}
	}
}
