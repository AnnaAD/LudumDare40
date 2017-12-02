package com.ludumdare40;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;

public class World {
	private ArrayList<Entity> entities;
	float width;
	float height;

	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public World(float width, float height) {
		this.width = width;
		this.height = height;
		entities = new ArrayList<Entity>();
	}

	public void update(GameContainer gc, int delta) {
		for(Entity e: entities) {
			e.update(gc, delta);
		}
	}
}
