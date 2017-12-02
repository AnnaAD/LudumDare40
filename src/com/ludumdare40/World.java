package com.ludumdare40;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;

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
	}

	public void update(GameContainer gc, int delta) {
		for(Entity e: entities) {
			e.update(gc, delta);
		}
	}
	
	public Player getPlayer() {
		return player;
	}
}
