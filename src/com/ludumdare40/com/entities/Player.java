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
	private Animation animLeft;
	private Animation animRight;
	private Animation animUp;
	private Animation animDown;

	//The amount of food a player has
	private int food;
	
	public Player(float x, float y, Image img, float health) {
		super(x, y, img, health);
		try {
			animRight = new Animation(new SpriteSheet(new Image("res/playerright.png"), 35,64), 500);
			animLeft = new Animation(new SpriteSheet(new Image("res/playerleft.png"),35 ,64), 500);
			animUp = new Animation(new SpriteSheet(new Image("res/playerfront.png"), 35,64), 500);
			animDown = new Animation(new SpriteSheet(new Image("res/playerback.png"), 35, 64), 500);



		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		collider.setHeight(25);
		collider.setOffsetY(animRight.getHeight()-25);
	}
	
	public void update(GameContainer gc, int delta) {
		animRight.update(delta);
		animLeft.update(delta);
		animUp.update(delta);
		animDown.update(delta);
	}
	
	public void render(Graphics g, float x, float y) {
		if(moveY < 0) {
			g.drawAnimation(animDown, x, y);
		} else if (moveY > 0) {
			g.drawAnimation(animUp, x, y);
		} else if(moveX > 0) {
			g.drawAnimation(animRight, x, y);
		} else if (moveX < 0) {
			g.drawAnimation(animLeft, x, y);
		} else {
			g.drawImage(img, x, y);
		}
	}

	/**
	 *  Moves the player x amount, - is left, + is right
	 * @param x Amount it will move (include delta).
	 */
	public void moveX(float x) {
		moveX = x;
		this.x += x;
	}

	/**
	 *  Moves the player t amount, - is up, + is down
	 * @param y Amount it will move (include delta).
	 */
	public void moveY(float y) {
		moveY = y;
		this.y += y;
	}
	
	public int getFood() {
		return food;
	}

	public void incrementFood(int amount) {
		food += amount;
	}
}
