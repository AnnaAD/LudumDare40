package com.ludumdare40;

import java.util.ArrayList;
import java.util.Collection;

import org.newdawn.slick.Image;

import com.ludumdare40.com.entities.Entity;

public class Background {
	
	private ArrayList<Tiles> tiles;
	
	public Background(int width, int height, Image img) {
		int numAcross = width/(img.getWidth());
		int numUp = height/(img.getHeight());
		tiles = new ArrayList<Tiles>();
		for(int i = 0; i < width; i += img.getWidth()) {
			for(int j = 0; j < height; j+= img.getHeight()) {
				tiles.add(new Tiles(i,j,img));
			}
		}
	}
	
	public ArrayList<Tiles> getTiles() {
		return tiles;
	}
	
	
}
