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
	/** WIDTH and height of map*/
	public static float WIDTH;
	public static float HEIGHT;
	public StaticEntity campfire;
	public Player player;

	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public World(float width, float height, Player player) {
		this.WIDTH = width;
		this.HEIGHT = height;
		entities = new ArrayList<Entity>();
		generateTerrain();
		this.player = player;
	}

	public void update(GameContainer gc, int delta) {
		//System.out.println("Player x:"+player.getX() + "Player y:"+player.getY());
		handlePlayerMovementAndCollisions(gc, delta);

		if(Math.random()/delta < .0001) {
			float x = (float) (Math.random()) * WIDTH;
			float y = (float) (Math.random()) * HEIGHT;
			entities.add(new Food(x, y, Food.Type.BERRY, ImageRes.berryImg));

		}


	}
	
	/*public Player getPlayer() {
		return player;
	}*/

	private void generateTerrain() {
		for(int i = 0; i < (int)(Math.random() * 40 + 100); i++) {
			float x = (float)(Math.random()) * WIDTH;
			float y = (float)(Math.random()) * HEIGHT;
			entities.add(new StaticEntity(x, y, StaticEntity.Type.TREE, ImageRes.treeImg));
			
		}
		for(int i = 0; i < (int)(Math.random() * 40 + 300); i++) {
			float x = (float)(Math.random()) * WIDTH;
			float y = (float)(Math.random()) * HEIGHT;
			if(Math.random() > .3)
				entities.add(new Food(x, y, Food.Type.BERRY, ImageRes.berryImg));
			else {
				entities.add(new Food(x, y, Food.Type.MUSHROOM, ImageRes.mushroomImg));

			}
			
		}
		
		for(int i = 0; i < (int)(Math.random() * 40 + 100); i++) {
			float x = (float)(Math.random()) * WIDTH;
			float y = (float)(Math.random()) * HEIGHT;
			entities.add(new StaticEntity(x, y, StaticEntity.Type.ROCK, ImageRes.rockImg));
			
		}

		this.campfire = new StaticEntity(WIDTH /2, HEIGHT /2, StaticEntity.Type.CAMPFIRE,ImageRes.campfireImg);
		entities.add(campfire);
		
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

		if(player.getX() + intendedDeltaX < 0 || player.getCollider().getX() + player.getCollider().getWidth() + intendedDeltaX > World.WIDTH)
			intendedDeltaX = 0;
		if(player.getY() + intendedDeltaY < 0 || player.getCollider().getY() + player.getCollider().getHeight() + intendedDeltaY > World.HEIGHT)
			intendedDeltaY = 0;
		player.moveX(intendedDeltaX);
		player.moveY(intendedDeltaY);
		

	}
	//TODO: Make this generic to reduce code
	/*private <T extends Entity>void GenerateEntities  (int minAmount, int maxAmount, Image img){
		int number = (int)(Math.random() * (maxAmount - minAmount) + minAmount);
		for(int i = 0; i < number; i++) {
			if
		}
	}*/


}
