package com.ludumdare40;

import org.newdawn.slick.GameContainer;
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

	/**
	 * Renders the entity
	 * @param g The graphics object
	 * @param x The absolute x position relative to the screen
	 * @param y The absolute y position
	 */
	public void render(Graphics g, float x, float y) {
		img.draw(x,y);
	}

	/**
	 *
	 * @param delta The time in ms since the last update.
	 */
	public void update(GameContainer gc, int delta) {
	}
}
