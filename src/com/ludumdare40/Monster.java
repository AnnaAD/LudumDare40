package com.ludumdare40;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public class Monster extends Entity {
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
