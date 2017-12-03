package com.ludumdare40.com.entities;

import com.ludumdare40.ImageRes;
import com.ludumdare40.World;
import com.ludumdare40.ui.Button;
import com.ludumdare40.ui.Text;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;

public class Person extends Creature{
    //TODO: Think of a better name
    public enum States {IDLE, FLEEING, TRAVELING, STARVING};
    private States state;
    private int food;
    private long hunger;
    private int idleMovementTime;
    public static StaticEntity campfire;
    public static ArrayList<Monster> monsters = new ArrayList<Monster>();
    private Button feedButton;
    private Text hungerText;
    private Text healthText;
    private Animation animLeft;
	private Animation animRight;
	private Animation animUp;
	private Animation animDown;

    private final float IDLE_SPEED = .05f;
    private final float TRAVELLING_SPEED = .05f;
    private float campfireAreaBoundary;
    private final float TIME_BETWEEN_FOOD = 50000f;
    private final float TIME_BEFORE_STARVING = 50000f;
    private final float FLEEING_SPEED = .05f;

    public Person(float x, float y, SpriteSheet pS, float health) {
        super(x, y, pS, health);
        animRight = new Animation(pS,0,1, 3,1,true, 200,false);
		animUp = new Animation(pS,0,2,3,2,true, 200,false);
		animLeft = new Animation(pS,0,3,3,3,true, 200,false);
		animDown = new Animation(pS,0,0,3,0,true, 200,false);

        velocity = new Vector2f(0f,0f);
        feedButton = new Button(0,0,50,20,"Feed");
        hungerText = new Text(0,0,"Food: " + food);
        healthText = new Text(0,0,"Health: " + (int) health);
        food = 3;
        campfireAreaBoundary = (float)Math.random() *100f + 200f;
        setState();
    }
    
    public void update(GameContainer gc, int delta) {
    	if(Math.abs(velocity.length()) > 0) {
			animRight.update(delta);
			animLeft.update(delta);
			animUp.update(delta);
			animDown.update(delta);
		}
    	
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
    	if(Math.abs(velocity.getX()) > Math.abs(velocity.getY())) {
	    	if(velocity.getX() < 0) {
				g.drawAnimation(animLeft, x, y);
			} else if (velocity.getX() > 0) {
				g.drawAnimation(animRight, x, y);
			}
    	} else {
    		
    		if(velocity.getY() < 0) {
				g.drawAnimation(animUp, x, y);
			} else if (velocity.getY() > 0) {
				g.drawAnimation(animDown, x, y);
			} else {
				animDown.setCurrentFrame(0);
				g.drawAnimation(animDown, x, y);
			}
	    	
    	}
    	
    	//Render UI Stuff
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

    private void setState() {
        Monster closestMonster = null;
        if(monsters.size()!=0){
            closestMonster = monsters.get(0);

            for(int i = 0;i<monsters.size();i++) {
                if (this.distanceTo(monsters.get(i)) < this.distanceTo(closestMonster))
                    closestMonster = monsters.get(i);
            }
        }
        if(closestMonster != null && distanceTo(closestMonster) < 200) {
            velocity = new Vector2f(-closestMonster.getX() + x,  -closestMonster.getY() + y);
            velocity.scale( FLEEING_SPEED/velocity.length());
            //System.out.println("velocity");
            state = States.FLEEING;
        } else if (hunger > TIME_BEFORE_STARVING) {
            state = States.STARVING;
        }else if(this.distanceTo(campfire) > campfireAreaBoundary) {
            state = States.TRAVELING;
        } else {
            state = States.IDLE;
            velocity = new Vector2f(0f, 0f);
        }
    }


    public void setState(States state){

    }
}
