package com.ludumdare40;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Entity {
	private float x;

	public float getY() {
		return y;
	}

	public Image getImg() {
		return img;
	}

	private float y;
	private Image img;
	
	public	Entity(float x, float y, Image img) {
		this.x = x;
		this.y = y;
		this.img = img;
	}

	public float getX() {
		return x;
	}

	public void render(Graphics g) {

	}
	
	public void update(int delta) {
		
	}
}
