package com.ludumdare40;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;

public class World {
	private ArrayList<Entity> entities;
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
		for(Entity e: entities) {
			e.update(gc, delta);
		}
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
	}
}
