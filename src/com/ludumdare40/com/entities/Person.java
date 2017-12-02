package com.ludumdare40.com.entities;

import com.ludumdare40.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class Person extends Creature{
    //TODO: Think of a better name
    public enum States {IDLE, FLEEING, TRAVELING, STARVING};
    private States state;
    private int food;
    private long hunger;
    private int idleMovementTime;
    public static StaticEntity campfire;

    private final float IDLE_SPEED = .2f;
    private final float TRAVELLING_SPEED = .1f;
    private final float CAMPFIRE_AREA_BOUNDARY = 400;

    public Person(float x, float y, Image img, float health) {
        super(x, y, img, health);
        state = States.IDLE;
        velocity = new Vector2f(0f,0f);

    }
    
    public void update(GameContainer gc, int delta) {
        hunger += delta;
        if(hunger > 30000 && food > 0){
            hunger = 0;
            food--;
        }
        setState();

        if(state == States.TRAVELING) {
            velocity = new Vector2f(campfire.getX() - this.getX(), campfire.getY()-this.getY());
            velocity.scale(TRAVELLING_SPEED/velocity.length());
        } else if(state == States.IDLE) {
            //System.out.println("Velocity x: " + velocity.getX() + "Velocity y: " + velocity.getY() +"Random: "+ Math.random()/delta);
            if( Math.abs(velocity.getX()) <= .01f && Math.abs(velocity.getY()) <= 0.01f && Math.random() / delta < .0006 ) {
                float XComponent = (float)Math.random() * 2 *IDLE_SPEED - IDLE_SPEED;
                float YComponent = (float)Math.sqrt(IDLE_SPEED * IDLE_SPEED - XComponent * XComponent);
                if(Math.random()<.5) {YComponent *= -1;}
                idleMovementTime = (int)(Math.random() * 3000 + 2000);
                System.out.println("new x:"+XComponent * idleMovementTime + "new y: "+YComponent * idleMovementTime + "Current: " +x +"Current y" + y);
                if(Math.abs(XComponent * idleMovementTime + World.WIDTH/2) < CAMPFIRE_AREA_BOUNDARY + World.WIDTH && Math.abs(YComponent * idleMovementTime + World.HEIGHT/2) < CAMPFIRE_AREA_BOUNDARY + World.HEIGHT/2)
                    velocity = new Vector2f(XComponent, YComponent);
            } else {
                idleMovementTime -= delta;
                if(idleMovementTime <= 0) {
                    velocity = new Vector2f(0f, 0f);
                }
            }
        } else if(state == States.STARVING) {
            hurt(.5f * delta);
        }

        x += velocity.getX() * delta;
        y += velocity.getY() * delta;
    }

    public static void setCampfire(StaticEntity campfire){
        Person.campfire = campfire;
    }

    private void setState(){
        if(hunger > 40000) {
            state = States.STARVING;
        } else if(this.distanceTo(campfire) > CAMPFIRE_AREA_BOUNDARY) {
            state = States.TRAVELING;
        } else {
            state = States.IDLE;
            velocity = new Vector2f (0f, 0f);
        }
    }
}
