package com.ludumdare40;

import java.util.ArrayList;

import com.ludumdare40.com.entities.Entity;
import com.ludumdare40.com.entities.Food;
import com.ludumdare40.com.entities.Player;
import com.ludumdare40.com.entities.StaticEntity;
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

	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public World(float width, float height, Player player) {
		this.width = width;
		this.height = height;
		entities = new ArrayList<Entity>();
		this.player = player;
		generateTerrain();
	}

	public void update(GameContainer gc, int delta) {
		System.out.println("Player x:"+player.getX() + "Player y:"+player.getY());
		handlePlayerMovementAndCollisions(gc, delta);
	}
	
	public Player getPlayer() {
		return player;
	}

	private void generateTerrain() {
		for(int i = 0; i < (int)(Math.random() * 20 + 20); i++) {
			float x = (float)(Math.random()) * 3000f - 1500f;
			float y = (float)(Math.random()) * 3000f - 1500f;
			try{
				entities.add(new StaticEntity(x, y, StaticEntity.Type.TREE, new Image("res/tree.png")));
			} catch(SlickException exception) {
				System.out.println("ERROR: UNABLE TO LOAD TREE IMAGE");
			}
		}
		for(int i = 0; i < (int)(Math.random() * 20 + 20); i++) {
			float x = (float)(Math.random()) * 3000f - 1500f;
			float y = (float)(Math.random()) * 3000f - 1500f;
			try{
				entities.add(new Food(x, y, Food.Type.BERRY, new Image("res/berry.png")));
			} catch(SlickException exception) {
				System.out.println("ERROR: UNABLE TO LOAD BERRY IMAGE");
			}
		}
		
		for(int i = 0; i < (int)(Math.random() * 20 + 20); i++) {
			float x = (float)(Math.random()) * 3000f - 1500f;
			float y = (float)(Math.random()) * 3000f - 1500f;
			try{
				entities.add(new StaticEntity(x, y, StaticEntity.Type.ROCK, new Image("res/rock.png")));
			} catch(SlickException exception) {
				System.out.println("ERROR: UNABLE TO LOAD ROCK IMAGE");
			}
		}
	}

	private void handlePlayerMovementAndCollisions(GameContainer gc, int delta) {
		float intendedDeltaX = 0;
		float intendedDeltaY = 0;

		if(gc.getInput().isKeyDown(Input.KEY_LEFT)) {
			intendedDeltaX = -0.1f;
		} else if(gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
			intendedDeltaX = 0.1f;
		}

		if(gc.getInput().isKeyDown(Input.KEY_UP)) {
			intendedDeltaY = -0.1f;
		} else if(gc.getInput().isKeyDown(Input.KEY_DOWN)){
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
}
