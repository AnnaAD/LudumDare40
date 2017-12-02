package com.ludumdare40;

import java.util.ArrayList;

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
	}

	public void update(int delta) {
		for(Entity e: entities) {
			e.update(delta);
		}
	}
}
