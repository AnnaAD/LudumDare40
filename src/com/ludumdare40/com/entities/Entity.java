package com.ludumdare40.com.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Entity {
	protected float x;
	protected float y;
	protected Image img;
	protected Collider collider;
	protected float width;
	protected float height;
	
	public	Entity(float x, float y, Image img) {
		this.x = x;
		this.y = y;
		this.img = img;
		this.width = img.getWidth();
		this.height = img.getHeight();
		this.collider = new Collider(this, img.getWidth(), img.getHeight());
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

	public static class Monster extends Entity {
        //TODO: Add movement and attacking
        /** from 0 to maxHealth*/
        int health;

        public final float STARTING_HEALTH = 40;
        public final float SPEED = 1;
        public final float DAMAGE = 1;
        private Entity target;

        public Monster(int x, int y, Image img) {
            super(x, y, img);
        }

        @Override
        public void update(GameContainer gc, int delta) {
            if(target.getX() > x) {
                x += .07*delta;
            } else {
                x -= .07*delta;
            }

            if(target.getY() > y) {
                y += .07*delta;
            } else {
                y -= .07*delta;
            }
        }

        private void die() {

        }

        private float distanceTo(Entity e) {
            return (float) Math.sqrt(Math.pow(e.getX()-x, 2) + Math.pow(e.getY()-y, 2));
        }
    }
}
