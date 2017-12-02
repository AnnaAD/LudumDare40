package com.ludumdare40.com.entities;

import com.ludumdare40.com.entities.Entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player extends Creature {
	private float moveX;
	private float moveY;
	private Animation anim;
	//The amount of food a player has
	private int food;
	
	public Player(float x, float y, Image img, float health) {
		super(x, y, img, health);
		try {
			anim = new Animation(new SpriteSheet(new Image("res/player.png"), 32, 58), 100);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(GameContainer gc, int delta) {
		anim.setAutoUpdate(true);
	}
	
	public void render(Graphics g, float x, float y) {
		g.drawAnimation(anim, x, y);
	}

	/**
	 *  Moves the player x amount, - is left, + is right
	 * @param x Amount it will move (include delta).
	 */
	public void moveX(float x) {
		this.x += x;
	}

	/**
	 *  Moves the player t amount, - is up, + is down
	 * @param y Amount it will move (include delta).
	 */
	public void moveY(float y) {
		this.y += y;
	}
	
	public int getFood() {
		return food;
	}

	public void incrementFood(int amount) {
		food += amount;
	}
}
