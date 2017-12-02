package com.ludumdare40.com.entities;

import org.newdawn.slick.Image;

public abstract class Creature extends Entity {
	private float health;
	
	public Creature(float x, float y, Image img, float health) {
		super(x, y, img);
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
