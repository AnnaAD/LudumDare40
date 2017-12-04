package com.ludumdare40;

import java.util.ArrayList;
import java.util.Collections;

import com.ludumdare40.com.entities.Creature;
import com.ludumdare40.com.entities.Entity;
import com.ludumdare40.com.entities.Person;
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
		bg = new Background((int)world.WIDTH, (int)world.HEIGHT, ImageRes.grassImg);
	}
	
	public void render(GameContainer gc, Graphics g, Player p) {
		width = gc.getWidth();
		height = gc.getHeight();
		ArrayList<Entity> eList = new ArrayList<>(world.getEntities());
		eList.add(p);
		eList.addAll(creatureManager.getCreatures());
		Collections.sort(eList, Entity.compareByY());
		eList.addAll(creatureManager.getBullets());
		
		gc.setVSync(true);

		ArrayList<Tiles> bTiles = bg.getTiles();
		for(Tiles t: bTiles) {
			if (canSee(t)) {
				t.render(g,t.getX()-x,t.getY() - y);
			}
		}
		
		for(Entity e : eList) {
			if(canSee(e)) {
				if(e instanceof Person)
					((Person)e).render(gc, g,e.getX()-x,e.getY() - y);
				else
					e.render(g,e.getX()-x,e.getY() - y);
			}
		}
		
		/*if(creatureManager.getSelectedPerson() != null) {
			//render in camera?
		}*/
		
		
	}
	
	public void centerOnEntity(Entity e) {
		if(e.getX() - width / 2 < 0)
			x = 0;
		else if( e.getX() + width/2 >= world.WIDTH)
			x = world.WIDTH-width;
		else
			x = e.getX() - width/2;
		if(e.getY() - height / 2 < 0)
			y = 0;
		else if( e.getY() + height/2 >= world.HEIGHT)
			y = world.HEIGHT-height;
		else
			y	 = e.getY() - height/2;
	}
	
	public boolean canSee(Entity e) {
		return (e.getX() + e.getWidth() > x) && (e.getX() <  x+ width) && (e.getY() + e.getHeight() > y) && (e.getY() < y + height);
	}
	
	public boolean canSee(Tiles e) {
		return (e.getX() + e.getWidth() > x) && (e.getX() <  x+ width) && (e.getY() + e.getHeight() > y) && (e.getY() < y + height);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	
}
