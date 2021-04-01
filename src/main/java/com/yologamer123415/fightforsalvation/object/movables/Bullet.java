package com.yologamer123415.fightforsalvation.object.movables;

import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.monsters.Monster;
import com.yologamer123415.fightforsalvation.player.Player;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class Bullet extends Movable {
	private static final int HP = 10;

	public Bullet(Vector path, GameObject shooter) {
		super( new Sprite("src/main/resources/usables/weapons/ranged/arrow.png"), path, shooter );
	}

	@Override
	public void collidedWithGameObjects(List<GameObject> gameObjects) {
		for (GameObject ob : gameObjects) {
			if (ob instanceof Monster) {
				Monster monster = (Monster) ob;
				monster.damage(HP);
			} else if (ob instanceof Player) {
				Player player = (Player) ob;
				player.damage(HP);
			}
		}
	}
}