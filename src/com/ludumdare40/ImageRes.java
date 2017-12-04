package com.ludumdare40;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class ImageRes {
	//So... This is a bad way to do this BUT otherwise it's really messy...
	//TODO: Make this a better file format?
	public static Image personImg; 
	public static SpriteSheet personSpriteSheet;
	public static String[] pathsToPeople = {"res/person.png","res/person1.png", "res/person2.png", "res/person3.png","res/person4.png", "res/person5.png", "res/person6.png", "res/person7.png","res/person8.png"};
	public static Image[] peopleImg;
	public static SpriteSheet[] peopleSpriteSheet;
	public static Image campfireImg;
	public static Image berryImg;
	public static Image treeImg;
	public static Image rockImg;
	public static Image monsterImg;
	public static Image bulletImg;
	public static Image textbubbleImg;
	public static Image grassImg;
	public static Image mushroomImg;
	public static Image meatImg;
	
	public static void init() throws SlickException {
		personImg = new Image("res/person.png");
		personSpriteSheet = new SpriteSheet(personImg,50 ,100);
		peopleSpriteSheet = new SpriteSheet[pathsToPeople.length];
		for(int i = 0; i < pathsToPeople.length; i++) {
			peopleSpriteSheet[i] = new SpriteSheet(new Image(pathsToPeople[i]),50,100);
		}
		campfireImg = new Image("res/campfire.png");
		berryImg = new Image("res/berry.png");
		bulletImg = new Image("res/bullet.png");
		monsterImg = new Image("res/monster.png");
		treeImg = new Image("res/tree.png");
		rockImg = new Image("res/rock.png");
		textbubbleImg = new Image("res/textbubble.png");
		grassImg = new Image("res/grass.png");
		mushroomImg = new Image("res/mushroom.png");
		meatImg = new Image("res/meat.png");
	}
	
	public static SpriteSheet getRandomPerson() {
		int i = (int)(Math.random() * (peopleSpriteSheet.length));
		return peopleSpriteSheet[i];
	}
}
