package com.ludumdare40.com.entities;

import com.ludumdare40.World;
import com.ludumdare40.ui.Button;
import com.ludumdare40.ui.Text;

import org.newdawn.slick.Color;
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
    private Button feedButton;
    private Text hungerText;
    private Text healthText;

    private final float IDLE_SPEED = .01f;
    private final float TRAVELLING_SPEED = .1f;
    private final float CAMPFIRE_AREA_BOUNDARY = 400;
    private final float TIME_BETWEEN_FOOD = 50000f;
    private final float TIME_BEFORE_STARVING = 50000f;

    public Person(float x, float y, Image img, float health) {
        super(x, y, img, health);
        state = States.IDLE;
        velocity = new Vector2f(0f,0f);
        feedButton = new Button(0,0,50,20,"Feed");
        hungerText = new Text(0,0,"Food: " + food);
        healthText = new Text(0,0,"Health: " + (int) health);
        food = 3;
    }
    
    public void update(GameContainer gc, int delta) {
        hunger += delta;
        if(hunger > TIME_BETWEEN_FOOD && food > 0){
            food --;
            hunger =0;
        }
        setState();

        if (state == States.TRAVELING) {
            velocity = new Vector2f(campfire.getX() - this.getX(), campfire.getY() - this.getY());
            velocity.scale(TRAVELLING_SPEED / velocity.length());
        } else if (state == States.IDLE) {
          /*  //System.out.println("Velocity x: " + velocity.getX() + "Velocity y: " + velocity.getY() +"Random: "+ Math.random()/delta);
            if( Math.abs(velocity.getX()) <= .00001f && Math.abs(velocity.getY()) <= 0.00001f && Math.random() / delta < .1 ) {
                float XComponent = (float)Math.random()  *IDLE_SPEED - 2 *IDLE_SPEED;
                float YComponent = (float)Math.sqrt(IDLE_SPEED * IDLE_SPEED - XComponent * XComponent);
                if(Math.random()<.5) {YComponent *= -1;}
                idleMovementTime = (int)(Math.random() * 2000 );
                System.out.println(World.WIDTH);
                float newX = XComponent * idleMovementTime + World.WIDTH/2;
                float newY = YComponent * idleMovementTime + World.HEIGHT/2;
                System.out.println("new x:"+(newX) + "new y: "+(newY) + "Current: " +x +"Current y" + y);

               if(newX < CAMPFIRE_AREA_BOUNDARY + World.WIDTH /2 && newX > World.WIDTH/2 - CAMPFIRE_AREA_BOUNDARY && newY < CAMPFIRE_AREA_BOUNDARY + World.HEIGHT/2 && newY < -CAMPFIRE_AREA_BOUNDARY + World.HEIGHT/2) {

                    velocity = new Vector2f(XComponent, YComponent);
                }
            } else {
                idleMovementTime -= delta;
                if(idleMovementTime <= 0) {
                    velocity = new Vector2f(0f, 0f);
                } */

        } else if(state == States.STARVING) {
            hurt(.0005f * delta);
        }

        x += velocity.getX() * delta;
        y += velocity.getY() * delta;
    }
    
    public void render(Graphics g, float x, float y) {
    	super.render(g,x,y);
    	feedButton.setX(x);
    	feedButton.setY(y - height/2 - 25);
    	feedButton.render(g);
    	hungerText.setX(x - width/2);
    	hungerText.setY(y - height/2 - 5);
    	healthText.setX(x - width/2);
    	healthText.setY(y-height/2 + 10);
    	healthText.setText("Health: " + (int) health);
    	healthText.render(g);
    	if(state == States.STARVING) {
    	    hungerText.setText("STARVING!");
        }
        else {
            hungerText.setText(food + " food");
        }
    	hungerText.render(g);
    }
    
    public boolean checkToFeed(int x, int y) {
    	return feedButton.checkClicked(x, y);
    }
    
    public void feed() {
    	food += 1;
    }

    public static void setCampfire(StaticEntity campfire){
        Person.campfire = campfire;
    }

    private void setState(){
        if(hunger > TIME_BEFORE_STARVING) {
            state = States.STARVING;
        } else if(this.distanceTo(campfire) > CAMPFIRE_AREA_BOUNDARY) {
            state = States.TRAVELING;
        } else {
            state = States.IDLE;
            velocity = new Vector2f(0f, 0f);
        }
    }
}
