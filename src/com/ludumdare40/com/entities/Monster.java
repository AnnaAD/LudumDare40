package com.ludumdare40.com.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public class Monster extends Creature{
    //TODO: Add movement and attacking
    /** from 0 to maxHealth*/
    int health;

    public final float STARTING_HEALTH = 40;
    public final float SPEED = 0.08f;
    public final float DAMAGE = 1;
    
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
		//System.out.println(Math.abs(target.getX() - x));
		if(Math.abs(target.getX() - x) > 20) {
	    	if(target.getX() > x  && vx < SPEED) {
	    		vx += .01f;
	    	} else if (vx > -SPEED) {
	    		vx -= .01f;
	    	}
		}
		
		
	    if(Math.abs(target.getY() - y) > 20) {	
	    	if(target.getY() > y && vy < SPEED) {
	    		vy += .01f;
	    	} else if (vy > -SPEED){
	    		vy -= .01f;
	    	}
	    }
    	
    	if(collider.collidesWith(target.collider)) {
    		target.hurt(DAMAGE);
    		while(collider.collidesWith(target.collider)) {
    			y-=vy*delta;
    			x-=vx*delta;
    			vx *= -2;
    			vy *= -2;
    		}
    	}
    }
    

}

