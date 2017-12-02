package com.ludumdare40;

import java.util.ArrayList;

import com.ludumdare40.com.entities.*;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class World {
	private ArrayList<Entity> entities;
	/** width and height of map*/
	private float width;
	private float height;
	private Player player;
	private StaticEntity campfire;
	private ArrayList<Bullet> bullets;

	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public World(float width, float height, Player player) {
		this.width = width;
		this.height = height;
		entities = new ArrayList<Entity>();
		bullets = new ArrayList<> ();
		this.player = player;
		generateTerrain();
	}

	public void update(GameContainer gc, int delta) {
		//System.out.println("Player x:"+player.getX() + "Player y:"+player.getY());
		handlePlayerMovementAndCollisions(gc, delta);
		handleBullets(gc, delta);
		removeDeadCreatures();
	}
	
	public Player getPlayer() {
		return player;
	}

	private void generateTerrain() {
		Monster m = null;
		try{
			m = new Monster(width/2, height/2, new Image("res/monster.png"), 10);
		} catch(SlickException exception) {
			System.out.println("ERROR: UNABLE TO LOAD Monster IMAGE");
		}		
		m.setTarget(player);
		entities.add(m);
		
		for(int i = 0; i < (int)(Math.random() * 40 + 200); i++) {
			float x = (float)(Math.random()) * width;
			float y = (float)(Math.random()) * height;
			try{
				entities.add(new StaticEntity(x, y, StaticEntity.Type.TREE, new Image("res/tree.png")));
			} catch(SlickException exception) {
				System.out.println("ERROR: UNABLE TO LOAD TREE IMAGE");
			}
		}
		for(int i = 0; i < (int)(Math.random() * 40 + 500); i++) {
			float x = (float)(Math.random()) * width;
			float y = (float)(Math.random()) * height;
			try{
				entities.add(new Food(x, y, Food.Type.BERRY, new Image("res/berry.png")));
			} catch(SlickException exception) {
				System.out.println("ERROR: UNABLE TO LOAD BERRY IMAGE");
			}
		}
		
		for(int i = 0; i < (int)(Math.random() * 40 + 200); i++) {
			float x = (float)(Math.random()) * width;
			float y = (float)(Math.random()) * height;
			try{
				entities.add(new StaticEntity(x, y, StaticEntity.Type.ROCK, new Image("res/rock.png")));
			} catch(SlickException exception) {
				System.out.println("ERROR: UNABLE TO LOAD ROCK IMAGE");
			}
		}
		try {
			this.campfire = new StaticEntity(width/2, height/2, StaticEntity.Type.CAMPFIRE, new Image("res/campfire.png"));
			entities.add(campfire);
		} catch( Exception e) {
			System.out.println("UNABLE TO LOAD CAMPFIRE IMAGE");
		}
	}


	private void handlePlayerMovementAndCollisions(GameContainer gc, int delta) {
		float intendedDeltaX = 0;
		float intendedDeltaY = 0;

		if(gc.getInput().isKeyDown(Input.KEY_A)) {
			intendedDeltaX = -0.1f;
		} else if(gc.getInput().isKeyDown(Input.KEY_D)) {
			intendedDeltaX = 0.1f;
		}

		if(gc.getInput().isKeyDown(Input.KEY_W)) {
			intendedDeltaY = -0.1f;
		} else if(gc.getInput().isKeyDown(Input.KEY_S)){
			intendedDeltaY = 0.1f;
		}
		intendedDeltaY = intendedDeltaY*delta;
		intendedDeltaX = intendedDeltaX*delta;

		for(int i = entities.size() - 1; i>=0; i--) {
			Entity e = entities.get(i);
			e.update(gc, delta);

			if(player.getCollider().willCollideWith(e.getCollider(), 0, intendedDeltaY)) {
				if (e instanceof Food){
					entities.remove(e);
					player.incrementFood(((Food) e).getFoodValue());
				} else {
					intendedDeltaY = 0;
				}
			}
			if(player.getCollider().willCollideWith(e.getCollider(), intendedDeltaX, 0)) {
				if (e instanceof Food){
					entities.remove(e);
					player.incrementFood(((Food) e).getFoodValue());
				} else {
					intendedDeltaX = 0;
				}
			}
		}
		player.moveX(intendedDeltaX);
		player.moveY(intendedDeltaY);
	}

	private void handleBullets(GameContainer gc, int delta) {
		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			float xComponent = gc.getInput().getMouseX() - gc.getWidth()/2;
			float yComponent = gc.getInput().getMouseY() - gc.getHeight()/2;
			Vector2f velocity = new Vector2f(xComponent, yComponent);

			try{
				bullets.add(new Bullet(player.getX() + player.getWidth()/2, player.getY() + player.getHeight()/2, new Image("res/bullet.png"),velocity));
			}catch( Exception e){
				System.out.println("COULDN'T LOAD BULLET IMAGE FROM FILE");
			}
		}

		for(int i = bullets.size() - 1; i>=0; i--){
			Bullet b = bullets.get(i);
			b.update(gc, delta);
			if(Math.abs(b.getX() - player.getX()) > gc.getWidth() * 2 || Math.abs(b.getX() - player.getX()) > gc.getWidth() * 2){
				bullets.remove(b);
			} else {
				for(Entity e : entities) {

					if (b.getCollider().collidesWith(e.getCollider())) {
						if (e instanceof StaticEntity)
							bullets.remove(b);
						else if (e instanceof Creature) {
							((Creature) (e)).hurt(Bullet.DAMAGE);
							bullets.remove(b);
						}
					}
				}

			}
		}
	}

	public ArrayList<Bullet> getBullets(){
		return bullets;
	}

	private void removeDeadCreatures(){
		for(int i = entities.size() - 1; i>=0; i--) {
			if(entities.get(i) instanceof Creature && ((Creature) entities.get(i)).isDead())
				entities.remove(i);
		}
	}
}
