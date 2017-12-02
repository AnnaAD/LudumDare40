package com.ludumdare40;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class Player extends Entity {
	
	public Player(float x, float y, Image img) {
		super(x, y, img);
	}
	
	public void update(GameContainer gc, int delta) {
		if(gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
			x += .1*delta;
		} else if (gc.getInput().isKeyDown(Input.KEY_LEFT)) {
			x -= .1*delta;
		}
		
		if(gc.getInput().isKeyDown(Input.KEY_UP)) {
			y += .1*delta;
		} else if (gc.getInput().isKeyDown(Input.KEY_DOWN)) {
			y -= .1*delta;
		}
		
		System.out.println(x + " " + y);
		
	}
	

}
