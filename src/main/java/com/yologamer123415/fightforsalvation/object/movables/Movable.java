package com.yologamer123415.fightforsalvation.object.movables;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.helpers.LocationHelper;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.object.ObjectClip;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Movable extends SpriteObject implements ICollidableWithGameObjects {
	private static final float SPEED = 20;
	private final Vector path;

	public Movable(Sprite sprite, Vector path) {
		super(sprite);

		this.path = path;
		System.out.println(path);

		this.setX( this.path.getX() );
		this.setY( this.path.getY() );
		this.setZ(10f);
		this.setVisible(false);
	}

	public abstract void collidedWithGameObjects(List<GameObject> gameObjects);

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
		List<GameObject> objectClipList = list.stream().filter(o -> o instanceof ObjectClip).collect( Collectors.toList() );

		if ( !objectClipList.isEmpty() ) {
			this.collidedWithGameObjects(objectClipList);

			FightForSalvation.getInstance().deleteGameObject(this);
		}
	}
}
