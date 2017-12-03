package com.ludumdare40.com.entities;

import java.util.Comparator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public abstract class Entity {
	protected float x;
	protected float y;
	protected Image img;
	protected SpriteSheet s;
	protected Collider collider;
	protected float width;
	protected float height;
	
	public	Entity(float x, float y, Image img) {
		this.x = x;
		this.y = y;
		this.img = img;
		this.width = img.getWidth();
		this.height = img.getHeight();
		this.collider = new Collider(this, this.width, this.height);
	}
	
	public Entity(float x, float y, SpriteSheet s) {
		this.x = x;
		this.y = y;
		this.s = s;
		//TODO: Generalize this code.... For some reason it's always broken
		System.out.println(s.getTextureHeight());
		this.width = 50;
		this.height = 100;
		this.collider = new Collider(this, this.width, this.height);
	}

	public Entity(float x, float y){

	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public float getHeight() {
		return height;
	}
	
	public float getWidth() {
		return width;
	}

	public Image getImg() {
		return img;
	}
	
	public Collider getCollider() {
		return collider;
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

	public float distanceTo(Entity e) {
		return (float) Math.sqrt(Math.pow(e.getX()-x, 2) + Math.pow(e.getY()-y, 2));
	}
	
	public static Comparator<Entity> compareByY() {
		Comparator<Entity> comp = new Comparator<Entity>() {
			public int compare(Entity e1, Entity e2) {
				return (int) ((e1.getY() + e1.getHeight()) - (e2.getY() + e2.getHeight()));
			}
		};
		return comp;
	}


}
