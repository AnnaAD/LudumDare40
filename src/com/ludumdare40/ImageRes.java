package com.ludumdare40;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class ImageRes {
	//So... This is a bad way to do this BUT otherwise it's really messy...
	public static Image personImg; 
	public static SpriteSheet personSpriteSheet;
	public static Image campfireImg;
	public static Image berryImg;
	public static Image treeImg;
	public static Image rockImg;
	public static Image monsterImg;
	public static Image bulletImg;
	
	public static void init() throws SlickException {
		personImg = new Image("res/person.png");
		personSpriteSheet = new SpriteSheet(personImg,50 ,100);
		campfireImg = new Image("res/campfire.png");
		berryImg = new Image("res/berry.png");
		bulletImg = new Image("res/bullet.png");
		monsterImg = new Image("res/monster.png");
		treeImg = new Image("res/tree.png");
		rockImg = new Image("res/rock.png");
	}
	
	public static SpriteSheet getRandomPerson() {
		return personSpriteSheet;
		
	}
}
