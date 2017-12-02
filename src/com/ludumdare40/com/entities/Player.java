package com.ludumdare40.com.entities;

import com.ludumdare40.com.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public class Player extends Entity {
	private float moveX;
	private float moveY;
	//The amount of food a player has
	private int food;
	
	public Player(float x, float y, Image img) {
		super(x, y, img);
	}
	
	public void update(GameContainer gc, int delta) {
		x += moveX*delta;
		y += moveY*delta;
		
	}

	/**
	 *  Moves the player x amount, - is left, + is right
	 * @param x Amount it will move (include delta).
	 */
	public void moveX(float x) {
		moveX = x;
	}

	/**
	 *  Moves the player t amount, - is up, + is down
	 * @param y Amount it will move (include delta).
	 */
	public void moveY(float y) {
		moveY = y;
	}
	
	public int getFood() {
		return food;
	}

	public void incrementFood(int amount) {
		food += amount;
	}
}
