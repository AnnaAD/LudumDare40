package com.ludumdare40;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Camera {
	private float x;
	private float y;
	private float width;
	private float height;
	private World world;
	
	public Camera(World world) {
		this.world = world;
	}
	
	public void render(GameContainer gc, Graphics g) {
		width = gc.getWidth();
		height = gc.getHeight();
		ArrayList<Entity> eList = world.getEntities();
		
		for(Entity e : eList) {
			if(canSee(e)) {
				e.render(g,e.getX()-x,e.getY() - y);
			}
		}
		Player p = world.getPlayer(); 
		p.render(g, p.getX()-x,p.getY() - y);
	}
	
	public void centerOnEntity(Entity e) {
		//TODO: add clamp
		x = e.getX() - width/2;
		y = e.getY() - height/2;
	}
	
	public boolean canSee(Entity e) {
		return (e.getX() + e.getWidth() > x) && (e.getX() <  x+ width) && (e.getY() + e.getHeight() > y) && (e.getY() < y + height);
	}
}
