package com.ludumdare40.com.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public class Monster extends Creature{
    //TODO: Add movement and attacking
    /** from 0 to maxHealth*/
    int health;

    public final float STARTING_HEALTH = 40;
    public final float SPEED = 0.7f;
    public final float DAMAGE = 0.1f;
    
    private float vx;
    private float vy;
    private Creature target;

    public Monster(float f, float g, Image img, float health) {
        super(f, g, img, health);
    }

    public Entity getTarget() {
		return target;
	}

	public void setTarget(Creature target) {
		this.target = target;
	}

	@Override
    public void update(GameContainer gc, int delta) {
		x += vx*delta;
		y += vy*delta;
		//vx *= 0.8*delta;
		//vy *= 0.8*delta;
    	if(target.getX() > x  && Math.abs(vx) < SPEED) {
    		vx += .01f;
    	} else {
    		vx += -.01f;
    	}
    	
    	if(target.getY() > y && Math.abs(vy) < SPEED) {
    		vy += .01f;
    	} else {
    		vy += -.01f;
    	}
    	
    	if(collider.collidesWith(target.collider)) {
    		while(collider.collidesWith(target.collider)) {
    			y-=vy*delta;
    			x-=vx*delta;
    		}
    		target.hurt(DAMAGE);
    	}
    }
    
    private float distanceTo(Entity e) {
    	return (float) Math.sqrt(Math.pow(e.getX()-x, 2) + Math.pow(e.getY()-y, 2));
    }
}

