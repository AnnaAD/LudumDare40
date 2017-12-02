package com.ludumdare40.com.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public class Monster extends Creature{
    //TODO: Add movement and attacking
    /** from 0 to maxHealth*/
    int health;

    public final float STARTING_HEALTH = 40;
    public final float SPEED = 1;
    public final int DAMAGE = 1;
    private Creature target;

    public Monster(int x, int y, Image img, int health) {
        super(x, y, img, health);
    }

    public Entity getTarget() {
		return target;
	}

	public void setTarget(Creature target) {
		this.target = target;
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
    	
    	if(collider.collidesWith(target.collider)) {
    		while(collider.collidesWith(target.collider)) {
    			x--;
    		}
    		target.hurt(DAMAGE);
    	}
    }
    
    private float distanceTo(Entity e) {
    	return (float) Math.sqrt(Math.pow(e.getX()-x, 2) + Math.pow(e.getY()-y, 2));
    }
}

