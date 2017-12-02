package com.ludumdare40.com.entities;

import org.newdawn.slick.Image;

public abstract class Creature extends Entity {
	private int health;
	
	public Creature(float x, float y, Image img, int health) {
		super(x, y, img);
		this.health = health;
	}

	public void hurt(int damage) {
		health -= damage;
	}

}
