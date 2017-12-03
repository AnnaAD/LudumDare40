package com.ludumdare40;

import java.util.ArrayList;
import java.util.Collections;

import com.ludumdare40.com.entities.Creature;
import com.ludumdare40.com.entities.Entity;
import com.ludumdare40.com.entities.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Camera {
	private float x;
	private float y;
	private float width;
	private float height;
	private World world;
	private CreatureManager creatureManager;
	private Background bg;
	
	public Camera(World world, CreatureManager creaturemanager) {
		this.world = world;
		this.creatureManager = creaturemanager;
		bg = new Background((int)world.WIDTH, (int)world.HEIGHT, ImageRes.snowImg);
	}
	
	public void render(GameContainer gc, Graphics g, Player p) {
		width = gc.getWidth();
		height = gc.getHeight();
		ArrayList<Entity> eList = new ArrayList<>(world.getEntities());
		eList.add(p);
		Collections.sort(eList, Entity.compareByY());
		eList.addAll(creatureManager.getBullets());
		eList.addAll(creatureManager.getCreatures());

		ArrayList<Tiles> bTiles = bg.getTiles();
		for(Tiles t: bTiles) {
			if (canSee(t)) {
				t.render(g,(int)t.getX()-x,(int)t.getY() - y);
			}
		}
		
		for(Entity e : eList) {
			if(canSee(e)) {
				e.render(g,e.getX()-x,e.getY() - y);
			}
		}
		
		
	}
	
	public void centerOnEntity(Entity e) {
		//TODO: add clamp
		x = e.getX() - width/2;
		y = e.getY() - height/2;
	}
	
	public boolean canSee(Entity e) {
		return (e.getX() + e.getWidth() > x) && (e.getX() <  x+ width) && (e.getY() + e.getHeight() > y) && (e.getY() < y + height);
	}
	
	public boolean canSee(Tiles e) {
		return (e.getX() + e.getWidth() > x) && (e.getX() <  x+ width) && (e.getY() + e.getHeight() > y) && (e.getY() < y + height);
	}
}
