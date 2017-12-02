package com.ludumdare40;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Entity {
	protected float x;
	protected float y;
	protected Image img;
	protected Collider collider;
	
	public	Entity(float x, float y, Image img) {
		this.x = x;
		this.y = y;
		this.img = img;
	}

	public Entity(float x, float y){

	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Image getImg() {
		return img;
	}


	public void render(Graphics g) {

	}
	
	public void update(int delta) {
		
	}
}
