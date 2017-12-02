package com.ludumdare40;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Camera {
	private float x;
	private float y;
	private float width;
	private float height;
	
	public void render(GameContainer gc, Graphics g, World world) {
		width = gc.getWidth();
		height = gc.getHeight();
		ArrayList<Entity> eList = world.getEntities();
		
		for(Entity e : eList) {
			if(canSee(e)) {
				e.render(g);
			}
		}
	}
	
	public boolean canSee(Entity e) {
		return (e.getX() > x) && (e.getX() <  x+ width) && (e.getY() > y) && (e.getY() < y + height);
	}
}
