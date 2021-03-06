package com.ludumdare40.com.entities;

import com.ludumdare40.ImageRes;
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
	private String dirGun;

	//The amount of food a player has
	private int food;
	
	public Player(float x, float y, SpriteSheet pS, float health) {
		super(x, y, pS, health);
		/*try {
			animRight = new Animation(new SpriteSheet(new Image("res/playerright.png"), 35,64), 500);
			animLeft = new Animation(new SpriteSheet(new Image("res/playerleft.png"),35 ,64), 500);
			animUp = new Animation(new SpriteSheet(new Image("res/playerfront.png"), 35,64), 500);
			animDown = new Animation(new SpriteSheet(new Image("res/playerback.png"), 35, 64), 500);

		} catch (SlickException e) {

			e.printStackTrace();
		}*/
		
		animRight = new Animation(pS,0,1, 3,1,true, 200,false);
		animUp = new Animation(pS,0,2,3,2,true, 200,false);
		animLeft = new Animation(pS,0,3,3,3,true, 200,false);
		animDown = new Animation(pS,0,0,3,0,true, 200,false);
		
		collider.setHeight(25);
		collider.setOffsetY(animRight.getHeight()-25);
	}
	
	public void update(GameContainer gc, int delta) {
		if(Math.abs(moveX) > 0 || Math.abs(moveY) > 0) {
			animRight.update(delta);
			animLeft.update(delta);
			animUp.update(delta);
			animDown.update(delta);
		}
	}
	
	public void renderGun(Graphics g, float x, float y) {
		if(dirGun.equals("right")) {
			g.drawImage(ImageRes.rifleImg,x + (width - ImageRes.leftrifleImg.getWidth())/2, y + 45);
		} else {
			g.drawImage(ImageRes.leftrifleImg, x + (width - ImageRes.leftrifleImg.getWidth())/2, y + 45);
		}
	}
	
	public void render(Graphics g, float x, float y) {
		if(moveY < 0) {
			renderGun(g, x,y);
			g.drawAnimation(animUp, x, y);
		} else if (moveY > 0) {
			g.drawAnimation(animDown, x, y);
			renderGun(g,x,y);
		} else if(moveX > 0) {
			g.drawAnimation(animRight, x, y);
			renderGun(g,x,y);
		} else if (moveX < 0) {
			g.drawAnimation(animLeft, x, y);
			renderGun(g,x,y);
		} else {
			animDown.setCurrentFrame(0);
			g.drawAnimation(animDown, x, y);
			renderGun(g,x,y);
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
	
	public void setDirGun(String s) {
		dirGun = s;
	}
	
	public int getFood() {
		return food;
	}

	public void incrementFood(int amount) {
		food += amount;
	}

	
}
