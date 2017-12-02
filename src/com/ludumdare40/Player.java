package com.ludumdare40;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

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
	
	public void setMoveX(float x) {
		moveX = x;
	}
	
	public void setMoveY(float y) {
		moveY = y;
	}
	
	public int getFood() {
		return food;
	}

	public void incrementFood(int amount) {
		food += amount;
	}
}
