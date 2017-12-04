package com.ludumdare40.com.entities;

import com.ludumdare40.ImageRes;
import com.ludumdare40.ui.Button;
import com.ludumdare40.ui.Text;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.RoundedRectangle;
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
	private String thought;
    private String name;
    private boolean selected;

    private final float IDLE_SPEED = .05f;
    private final float TRAVELLING_SPEED = .03f;
    private float campfireAreaBoundary;
    private final float TIME_BETWEEN_FOOD = 20000f;
    private final float TIME_BEFORE_STARVING = 250000f;
    private final float FLEEING_SPEED = .05f;

    public Person(float x, float y, SpriteSheet pS, float health, String name) {
        super(x, y, pS, health);
        animRight = new Animation(pS,0,1, 3,1,true, 200,false);
		animUp = new Animation(pS,0,2,3,2,true, 200,false);
		animLeft = new Animation(pS,0,3,3,3,true, 200,false);
		animDown = new Animation(pS,0,0,3,0,true, 200,false);
        this.name = name;
        velocity = new Vector2f(0f,0f);
        feedButton = new Button(0,0,50,20,"Feed");
        hungerText = new Text(0,0,"Food: " + food);
        healthText = new Text(0,0,"Health: " + (int) health);
        food = 3;
        campfireAreaBoundary = (float)Math.random() *325f + 75f;
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
        if(state == States.IDLE && hunger > TIME_BETWEEN_FOOD)
            thought = "I'm feeling a little peckish.";

        if (state == States.TRAVELING) {
            velocity = new Vector2f(campfire.getX() - this.getX(), campfire.getY() - this.getY());
            velocity.scale(TRAVELLING_SPEED / velocity.length());
        } else if (state == States.IDLE) {
        } else if(state == States.STARVING) {
            hurt(.0005f * delta);
        }

        x += velocity.getX() * delta;
        y += velocity.getY() * delta;
    }
    
    public void render(GameContainer gc, Graphics g, float x, float y) {
    	
    	
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
    	if(selected) {
    		renderUI(g, x, y, gc);
    	}
    	

    	
    	if(thought != null) {
    		g.drawImage(ImageRes.textbubbleImg, x + 50, y);
    		g.setColor(Color.black);
    		g.drawString(thought, x+50 + ImageRes.textbubbleImg.getWidth()/2 - g.getFont().getWidth(thought)/2, y + 2);
    	}
    }
    
    public boolean checkClicked(float clickX, float clickY) {
		return (clickX > this.x) && (clickX <  this.x+ width) && (clickY > this.y) && (clickY < this.y + height);
	}
    
    public void setSelect(boolean selected) {
    	this.selected = selected;
    }
    
    private void renderUI(Graphics g, float x, float y, GameContainer gc) {

        g.setColor(new Color(0xc0f7b9));
        RoundedRectangle panel = new RoundedRectangle(x-20,y-85,80f,85f,7);
        g.fill(panel);
        g.setColor(Color.black);
        g.draw(panel);
        g.drawLine(panel.getX(),panel.getY() + 20, panel.getX() + panel.getWidth(), panel.getY() + 20);
        g.drawString(name, panel.getX() + (panel.getWidth() - g.getFont().getWidth(name))/2, panel.getY() + 2);

    	healthText.setText( (health < 10 ? " " : "")+(int) health + " X");
    	healthText.render(g, panel.getCenterX() - (g.getFont().getWidth(healthText.getText() + 8)) /2 - 3,panel.getY() + 40);
    	g.drawImage(ImageRes.healthImg.getScaledCopy(.5f), panel.getCenterX() + (g.getFont().getWidth(healthText.getText())) /2 -4 +3, panel.getY() + 40);

        hungerText.setText( (food < 10 ? " " : "")+(int) food + " X");
        hungerText.render(g, panel.getCenterX() - (g.getFont().getWidth(hungerText.getText() + 8)) /2 -3,panel.getY() + 22);
        g.drawImage(ImageRes.foodImg.getScaledCopy(.5f), panel.getCenterX() + (g.getFont().getWidth(hungerText.getText())) /2 -4+3, panel.getY() + 22);
        feedButton.render( gc,g,panel.getCenterX() - feedButton.getWidth()/2,panel.getY() + 58);
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
            state = States.FLEEING;
            thought = "AAH!!!";
        } else if (hunger > TIME_BEFORE_STARVING) {
        	thought = "I need food!";
            state = States.STARVING;
        }else if(this.distanceTo(campfire) > campfireAreaBoundary) {
            state = States.TRAVELING;
            thought = "Ooh.. Fire?";
        } else {
            state = States.IDLE;
            thought = null;
            velocity = new Vector2f(0f, 0f);
        }
    }


    public void setState(States state){

    }
}
