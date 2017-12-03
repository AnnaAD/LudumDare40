package com.ludumdare40.com.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

public abstract class Creature extends Entity {
	protected float health;
	protected Vector2f velocity;
	
	public Creature(float x, float y, Image img, float health) {
		super(x, y, img);
		this.health = health;
	}
	public Creature(float x, float y, SpriteSheet pS, float health) {
		super(x, y, pS);
		this.health = health;
	}

	public float getHealth() {
		return health;
	}

	public void hurt(float damage) {
		health -= damage;
	}

	public boolean isDead(){
		return health <= 0;
	}

}
